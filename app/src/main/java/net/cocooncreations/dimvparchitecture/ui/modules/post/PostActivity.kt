package net.cocooncreations.dimvparchitecture.ui.modules.post

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.cocooncreations.dimvparchitecture.R
import net.cocooncreations.dimvparchitecture.ui.base.BaseActivity
import net.cocooncreations.dimvparchitecture.ui.models.Post

class PostActivity : BaseActivity<PostPresenter>(),PostView {

    private var adapter = PostListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViewComponents()
        presenter.onViewCreated()
    }


    private fun initializeViewComponents(){
        postsRecyclerView.adapter = adapter
    }

    override fun instantiatePresenter(): PostPresenter {
        return PostPresenter(this,application)
    }

    override fun updatePosts(posts: List<Post>) {
        adapter.updateData(posts)
        Log.d("Posts",posts.size.toString())
    }

    override fun showError(error: String) {
        showAlertDialog(getString(R.string.error),error,null)
    }

    override fun showError(errorResId: Int) {
        showAlertDialog(getString(R.string.error),getString(errorResId),null)
    }

    override fun showLoading() {
        showProgressDialog(getString(R.string.please_wait))
    }

    override fun hideLoading() {
        hideProgressDialog()
    }
}
