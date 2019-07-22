package net.cocooncreations.dimvparchitecture.ui.modules.post

import android.os.Bundle
import android.util.Log
import net.cocooncreations.dimvparchitecture.R
import net.cocooncreations.dimvparchitecture.ui.base.BaseActivity
import net.cocooncreations.dimvparchitecture.ui.models.Post

class PostActivity : BaseActivity<PostPresenter>(),PostView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onViewCreated()
    }

    override fun instantiatePresenter(): PostPresenter {
        return PostPresenter(this)
    }

    override fun updatePosts(posts: List<Post>) {
        Log.d("Posts",posts.size.toString())
    }

    override fun showError(error: String) {
    }

    override fun showError(errorResId: Int) {
    }

    override fun showLoading() {
        showProgressDialog("Please wait")
    }

    override fun hideLoading() {
        hideProgressDialog()
    }


}
