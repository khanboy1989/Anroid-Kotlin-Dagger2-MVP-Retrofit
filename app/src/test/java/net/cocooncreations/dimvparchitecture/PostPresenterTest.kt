package net.cocooncreations.dimvparchitecture

import android.content.Context
import net.cocooncreations.ApplicationClass
import net.cocooncreations.dimvparchitecture.ui.models.Post
import net.cocooncreations.dimvparchitecture.ui.modules.post.PostPresenter
import net.cocooncreations.dimvparchitecture.ui.modules.post.PostView
import net.cocooncreations.dimvparchitecture.utils.getTestContext
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.application
import org.robolectric.annotation.Config

/**
* Class containing all unit test on PostPresenter
* @property view an implementation of PostView which provide us the number of times a method has been called
 * @property presenter the PostPresenter which will be tested in the tests
 * */

@RunWith(RobolectricTestRunner::class)
@Config(application = ApplicationClass::class, manifest = Config.NONE)

class PostPresenterTest {


    private val view = TestPostView()
    private lateinit var presenter:PostPresenter
    //private lateinit var application:ApplicationClass


    /**
     * Actions to be performed before each test
     */
    @Before
    fun setup(){
        val application:ApplicationClass = ApplicationClass()
        presenter = PostPresenter(view,application)
        view.reset()
    }


    @Test
    fun testLoadPosts(){
        presenter.onViewCreated()

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