package net.cocooncreations.dimvparchitecture

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import net.cocooncreations.ApplicationClass
import net.cocooncreations.dimvparchitecture.network.PostApi
import net.cocooncreations.dimvparchitecture.ui.models.Post
import net.cocooncreations.dimvparchitecture.ui.modules.post.PostPresenter
import net.cocooncreations.dimvparchitecture.ui.modules.post.PostView
import net.cocooncreations.dimvparchitecture.utils.getTestContext
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.application
import org.robolectric.annotation.Config
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
* Class containing all unit test on PostPresenter
* @property view an implementation of PostView which provide us the number of times a method has been called
 * @property presenter the PostPresenter which will be tested in the tests
 * */

@RunWith(RobolectricTestRunner::class)
@Config(application = ApplicationClass::class, manifest = Config.NONE)

class PostPresenterTest {


    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val view = TestPostView()
    //private lateinit var application:ApplicationClass
    val application:ApplicationClass = ApplicationClass()

    @Mock
    lateinit var api:PostApi


    @InjectMocks
    private var presenter:PostPresenter = PostPresenter(view,application)


    /**
     * Actions to be performed before each test
     */
    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        view.reset()
    }

    @Before
    fun setUpRxSchedulers(){
        val immidiate = object: Scheduler(){
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }

            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

        }
        RxJavaPlugins.setInitIoSchedulerHandler{ scheduler: Callable<Scheduler>? -> immidiate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler: Callable<Scheduler>? -> immidiate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler: Callable<Scheduler>? ->  immidiate}
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler: Callable<Scheduler>? -> immidiate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler>? -> immidiate }
    }

    @Test
    fun testLoadPostsSuccess(){

        val posts = Post(12,2,"title","body")
        val postList = arrayListOf(posts)

        `when`(api.getPosts()).thenReturn(Observable.just(postList))

        presenter.getPosts()

        Assert.assertEquals(1,view.updatePostsCounter)

    }


    @Test
    fun testLoadPostsFail(){

    }

    /**
     * An implementation of PostView which provides us the number of times a methods has
     * been called and arguments with which it has been called
     * @property updatePostCounter the of times updatePosts() has been called since last reset
     * @property updatePostArgs the parameter of each updatePosts() call since last reset
     * @property showErrorCounter the parameter of the number of times showError() has been called since last reset
     * @property showErrorArgs the parameter of each showError() call since last reset
     * @property showLoadingCounter the number of times showLoading() has been called since last reset
     * @property hideLoadingCounter the number of times hideLoading() has been called since last reset
     */
    class TestPostView:PostView{

        var updatePostsCounter = 0
        var updatePostsArgs:MutableList<List<Post>> = mutableListOf()
        var showErrorCounter = 0
        var showErrorArgs:MutableList<String> = mutableListOf()
        var showLoadingCounter = 0
        var hideLoadingCounter = 0


        /**
         * Sets all counters to 0 and all args to empty list
         */
        fun reset(){
            updatePostsCounter = 0
            updatePostsArgs = mutableListOf()
            showErrorCounter = 0
            showErrorArgs = mutableListOf()
            showLoadingCounter = 0
            hideLoadingCounter = 0
        }

        override fun updatePosts(posts: List<Post>) {
            updatePostsArgs.add(posts)
            updatePostsCounter++
        }

        override fun showError(error: String) {
            showErrorArgs.add(error)
            showErrorCounter++
        }

        override fun showError(errorResId: Int) {
        }

        override fun showLoading() {
            showLoadingCounter++
        }

        override fun hideLoading() {
            hideLoadingCounter++
        }

        override fun getContext(): Context {
            return getTestContext()
        }
    }
}