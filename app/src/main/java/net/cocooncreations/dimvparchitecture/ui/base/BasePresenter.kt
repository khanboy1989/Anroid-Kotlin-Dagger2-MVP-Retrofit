package net.cocooncreations.dimvparchitecture.ui.base

import net.cocooncreations.dimvparchitecture.di.components.DaggerPresenterInjector
import net.cocooncreations.dimvparchitecture.di.components.PresenterInjector
import net.cocooncreations.dimvparchitecture.di.modules.ContextModule
import net.cocooncreations.dimvparchitecture.di.modules.NetworkModule
import net.cocooncreations.dimvparchitecture.ui.modules.post.PostPresenter

abstract class BasePresenter<out V:BaseView>(protected  val view:V) {

    private val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()

    init {
       inject()
    }



    open fun onViewCreated(){}
    open fun onViewDestroyed(){}


    private fun inject(){
        when(this){
            is PostPresenter->injector.inject(this)
        }
    }

}