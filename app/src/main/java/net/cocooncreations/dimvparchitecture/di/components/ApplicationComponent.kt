package net.cocooncreations.dimvparchitecture.di.components

import dagger.Component
import net.cocooncreations.ApplicationClass
import net.cocooncreations.dimvparchitecture.di.modules.AppModule
import net.cocooncreations.dimvparchitecture.di.modules.NetModule
import net.cocooncreations.dimvparchitecture.ui.modules.post.PostPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class),(NetModule::class)])

interface ApplicationComponent {

    fun inject(mewApplication: ApplicationClass)
    fun inject(mewPostPresenter:PostPresenter)

}