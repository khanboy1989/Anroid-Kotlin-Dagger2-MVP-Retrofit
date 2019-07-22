package net.cocooncreations.dimvparchitecture.di.components

import dagger.BindsInstance
import dagger.Component
import net.cocooncreations.dimvparchitecture.di.modules.ContextModule
import net.cocooncreations.dimvparchitecture.di.modules.NetworkModule
import net.cocooncreations.dimvparchitecture.ui.base.BaseView
import net.cocooncreations.dimvparchitecture.ui.modules.post.PostPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [(ContextModule::class),(NetworkModule::class)])
interface PresenterInjector {

    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param postPresenter PostPresenter in which to inject the dependencies
     */

    fun inject(postPresenter:PostPresenter)


    @Component.Builder
    interface Builder{
        fun build():PresenterInjector

        fun networkModule(networkModule:NetworkModule):Builder
        fun contextModule(contextModule:ContextModule):Builder


        @BindsInstance
        fun baseView(baseView: BaseView):Builder

    }


}