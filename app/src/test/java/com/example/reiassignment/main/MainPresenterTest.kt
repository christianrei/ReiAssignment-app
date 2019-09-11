package com.example.reiassignment.main

import com.example.reiassignment.main.di.mainModule
import com.example.reiassignment.models.*
import com.example.reiassignment.network.reddit.RedditRepository
import com.example.reiassignment.utils.BaseRxTest
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.mock.declareMock
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MainPresenterTest : KoinTest, BaseRxTest() {

    private lateinit var presenter: MainPresenter

    private lateinit var mockRepo: RedditRepository

    @Mock
    private lateinit var mockView: MainViewController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            modules(mainModule)
        }
        declareMock<RedditRepository>()
        mockRepo = get()
        presenter = MainPresenter(mockView)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `when onPostClicked is called then launchPostViewerScreen`() {
        val mockPostData = PostData("test", "test", 0, "test", "test", 52, "test", "title")
        presenter.onPostClicked(mockPostData)
        verify(mockView).launchPostViewerScreen(mockPostData)
    }

    @Test
    fun `when try again is called then call getSubredditData`() {
        `when`(mockRepo.getSubredditData(ArgumentMatchers.anyString())).thenReturn(
            Single.error(
                Exception()
            )
        )
        val spyPresenter = spy(MainPresenter(mockView))
        spyPresenter.tryAgainClicked()
        verify(spyPresenter).getSubredditData()
    }

    @Test
    fun `when getSubredditData is called and the call succeeds then call setAdapter`() {
        val mockId = "mockid"
        val mockScore = 101
        val mockNumComments = 32
        val mockAuthor = "mockAuthor"
        val mockUrl = "mockUrl"
        val mockThumbnail = "mockThumbnail"
        val mockSelfText = "mockSelfText"
        val mockTitle = "mockTitle"
        val mockRedditData = RedditData(
            Data(
                listOf(
                    Children(
                        ChildData(
                            false,
                            mockAuthor,
                            "mockAuthorFullname",
                            "mockCategory",
                            false,
                            8,
                            true,
                            23,
                            mockId,
                            true,
                            "90",
                            true,
                            "mockName",
                            mockNumComments,
                            false,
                            mockScore,
                            mockSelfText,
                            false,
                            "mockSubreddit",
                            mockThumbnail,
                            mockTitle,
                            723,
                            mockUrl
                        ), "mockKind"
                    )
                ), 0
            ), "mockKind"
        )
        `when`(mockRepo.getSubredditData(ArgumentMatchers.anyString())).thenReturn(
            Single.just(
                mockRedditData
            )
        )
        presenter.getSubredditData()
        verify(mockView).setAdapter(
            listOf(
                PostData(
                    mockId,
                    mockAuthor,
                    mockScore,
                    mockUrl,
                    mockSelfText,
                    mockNumComments,
                    mockThumbnail,
                    mockTitle
                )
            )
        )
    }

    @Test
    fun `when getSubredditData is called and the call fails then showErrorView`() {
        `when`(mockRepo.getSubredditData(ArgumentMatchers.anyString())).thenReturn(
            Single.error(
                Exception()
            )
        )
        presenter.getSubredditData()
        verify(mockView).showErrorView()
    }

}