package com.example.reiassignment.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reiassignment.R
import com.example.reiassignment.extensions.hide
import com.example.reiassignment.extensions.show
import com.example.reiassignment.models.PostData
import com.example.reiassignment.postviewer.PostViewerActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainViewController, RedditPostListener {

    private val presenter: MainPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.main_activity_title)

        presenter.getSubredditData()

        tv_try_again.setOnClickListener {
            presenter.tryAgainClicked()
        }
    }

    override fun setAdapter(postData: List<PostData>) {
        tv_try_again.hide()
        rv_reddit.show()
        rv_reddit.layoutManager = LinearLayoutManager(this)
        rv_reddit.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_reddit.adapter = RedditAdapter(postData, this)
    }

    override fun onPostClicked(postData: PostData) {
        presenter.onPostClicked(postData)
    }

    override fun launchPostViewerScreen(postData: PostData) {
        startActivity(PostViewerActivity.getIntent(this, postData))
    }

    override fun showErrorView() {
        tv_try_again.show()
        Toast.makeText(this, getString(R.string.generic_reddit_error), Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

}
