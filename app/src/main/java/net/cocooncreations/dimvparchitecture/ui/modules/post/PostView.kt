package net.cocooncreations.dimvparchitecture.ui.modules.post

import android.support.annotation.StringRes
import net.cocooncreations.dimvparchitecture.ui.base.BaseView
import net.cocooncreations.dimvparchitecture.ui.models.Post

/**
 * Interface providing required method for a view displaying posts
 */
interface PostView:BaseView {

    /**
     * Updates the previous posts by the specified ones
     * @param posts the list of posts that will replace existing ones
     */
    fun updatePosts(posts:List<Post>)

    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun showError(error:String)


    /**
     * Displays an error in the view
     * @param errorTesId the resource id of the error to display in the view
     */
     fun showError(@StringRes errorResId:Int)

    /**
     * Displays laoding indicator
     */

    fun showLoading()


    /**
     * Hides loading indicator
     */

    fun hideLoading()
}