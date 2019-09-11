package com.example.reiassignment.postviewer

import com.example.reiassignment.models.PostData
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PostViewerPresenterTest {

    private lateinit var presenter: PostViewerPresenter

    @Mock
    private lateinit var mockView: PostViewerViewController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PostViewerPresenter(mockView)
    }

    @Test
    fun `when handleData is called then update view methods`() {
        val mockPostData = PostData("id", "author", 0, "url", "selfText", 52, "thumbnail", "title")
        presenter.handleData(mockPostData)
        Mockito.verify(mockView).setupBody(mockPostData.selfText)
        Mockito.verify(mockView).setupTitle(mockPostData.title)
        Mockito.verify(mockView).showThumbnail(mockPostData.thumbnail)
    }

    @Test
    fun `when handleData is called and thumbnail is empty then hide image`() {
        val mockPostData = PostData("id", "author", 0, "url", "selfText", 52, "", "title")
        presenter.handleData(mockPostData)
        Mockito.verify(mockView).hideThumbnail()
    }

    @Test
    fun `when handleData is called with null data then show empty view`() {
        presenter.handleData(null)
        Mockito.verify(mockView).showEmptyView()
    }

    @Test
    fun `when handleData is called with valid self text then show body text`() {
        val mockPostData = PostData("id", "author", 0, "url", "selfText", 52, "", "title")
        presenter.handleData(mockPostData)
        Mockito.verify(mockView).setupBody(mockPostData.selfText)
    }

    @Test
    fun `when handleData is called with a valid url but no self text then show web view`() {
        val mockPostData = PostData("id", "author", 0, "url", "", 52, "", "title")
        presenter.handleData(mockPostData)
        Mockito.verify(mockView).showWebView(mockPostData.url)
    }

    @Test
    fun `when handleData is called with no valid url and no self text then show empty view`() {
        val mockPostData = PostData("id", "author", 0, "", "", 52, "", "title")
        presenter.handleData(mockPostData)
        Mockito.verify(mockView).showEmptyView()
    }

}