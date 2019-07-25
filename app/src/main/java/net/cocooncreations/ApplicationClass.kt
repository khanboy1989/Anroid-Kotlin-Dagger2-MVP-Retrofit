package net.cocooncreations

import android.app.Application
import net.cocooncreations.dimvparchitecture.di.components.ApplicationComponent
import net.cocooncreations.dimvparchitecture.di.components.DaggerApplicationComponent
import net.cocooncreations.dimvparchitecture.di.modules.NetModule

open class ApplicationClass:Application() {

    lateinit var applicationComponent:ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }


    private fun initAppComponent(){
        applicationComponent = DaggerApplicationComponent.builder()
            .netModule(NetModule())
            .build()
        applicationComponent.inject(this)
    }
}