package net.cocooncreations.dimvparchitecture.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import net.cocooncreations.dimvparchitecture.R

abstract class BaseActivity<P:BasePresenter<BaseView>>:BaseView,AppCompatActivity() {

    protected lateinit var presenter:P
    protected abstract fun instantiatePresenter():P


    /**
    * A dialog showing a progress indicator and an optional text message or
    * view
    */

    protected var mProgressDialog:android.support.v7.app.AlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeProgressDialog()
        presenter = instantiatePresenter()
    }

    override fun getContext(): Context {
        return this
    }

    fun initializeProgressDialog(){
        if(mProgressDialog == null){
            val view: View = LayoutInflater.from(this).inflate(R.layout.layout_progress_dialog,null)
            mProgressDialog = AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()
        }
    }

    protected fun showProgressDialog(title:String){
        hideProgressDialog()
        mProgressDialog?.setTitle(title)
        mProgressDialog?.show()
    }

    protected fun hideProgressDialog(){
        if(mProgressDialog != null){
            mProgressDialog?.hide()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgressDialog()
        mProgressDialog == null
    }
}