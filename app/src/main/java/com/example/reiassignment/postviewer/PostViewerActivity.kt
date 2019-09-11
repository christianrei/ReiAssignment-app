package com.example.reiassignment.postviewer

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.reiassignment.R
import com.example.reiassignment.extensions.hide
import com.example.reiassignment.extensions.show
import com.example.reiassignment.models.PostData
import kotlinx.android.synthetic.main.activity_post_viewer.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class PostViewerActivity : AppCompatActivity(), PostViewerViewController {

    companion object {
        private const val POST_DATA_BUNDLE_KEY = "POST_DATA_BUNDLE_KEY"

        fun getIntent(context: Context, postData: PostData): Intent {
            return Intent(context, PostViewerActivity::class.java).apply {
                putExtra(POST_DATA_BUNDLE_KEY, postData)
            }
        }
    }

    private val presenter: PostViewerPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_viewer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        presenter.handleData(intent.getParcelableExtra(POST_DATA_BUNDLE_KEY))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setupTitle(title: String) {
        this.title = title
    }

    override fun showThumbnail(thumbnail: String) {
        iv_post_viewer_image.show()
        Glide.with(this).load(thumbnail).into(iv_post_viewer_image)
    }

    override fun hideThumbnail() {
        iv_post_viewer_image.hide()
    }

    override fun showWebView(url: String) {
        wv_post_url.show()
        wv_post_url.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pb_url_progress.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pb_url_progress.hide()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                tv_error_text.show()
                tv_error_text.text = getString(R.string.url_problem)
                pb_url_progress.hide()
            }
        }
        wv_post_url.loadUrl(url)
    }

    override fun showEmptyView() {
        tv_error_text.show()
        tv_error_text.text = getString(R.string.no_body_to_show)
    }

    override fun setupBody(selfText: String) {
        tv_post_viewer_body.text = selfText
    }

}