package net.cocooncreations.dimvparchitecture.ui.base

abstract class BasePresenter<out V:BaseView>(protected  val view:V) {

//    private val injector: PresenterInjector = DaggerPresenterInjector
//        .builder()
//        .baseView(view)
//        .contextModule(ContextModule)
//        .networkModule(NetworkModule)
//        .build()

    init {
       //inject()
    }



    open fun onViewCreated(){}
    open fun onViewDestroyed(){}


    private fun inject(){
//        when(this){
//            is PostPresenter->injector.inject(this)
//        }
    }

}