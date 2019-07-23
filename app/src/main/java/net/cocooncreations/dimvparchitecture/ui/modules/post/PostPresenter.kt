package net.cocooncreations.dimvparchitecture.ui.modules.post

import android.app.Application
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.cocooncreations.ApplicationClass
import net.cocooncreations.dimvparchitecture.di.components.ApplicationComponent
import net.cocooncreations.dimvparchitecture.network.PostApi
import net.cocooncreations.dimvparchitecture.ui.base.BasePresenter
import javax.inject.Inject

/**
 * The Presenter that will present the Post view.
 * @param postView the Post view to be presented by the presenter
 * @property postApi the API interface implementation
 * @property context the context in which the application is running
 * @property subscription the subscription to the API call
 */
class PostPresenter(postView:PostView, var applicationComponent: Application):BasePresenter<PostView>(postView) {

    @Inject
    lateinit var postApi: PostApi

    private var subscription:Disposable? =  null

    override fun onViewCreated() {
        (applicationComponent as ApplicationClass).applicationComponent.inject(this)
         getPosts()
    }

    /**
     * Loads the posts from the API and presents them in the view when retrieved, or showss error if
     * any.
     */

    fun getPosts(){
        view.showLoading()
        subscription = postApi.getPosts().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate {
                view.hideLoading()
            }.subscribe({
                postList-> view.updatePosts(postList)
            },{
                view.showError(it.localizedMessage)
            })
    }


    override fun onViewDestroyed() {
        subscription?.dispose()
    }

}