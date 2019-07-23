package net.cocooncreations.dimvparchitecture.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var application:Application) {

    lateinit var applicationInstance:Application

    init{
        this.applicationInstance = application
    }


    @Provides
    @Singleton
    fun provideApplication():Application{
        return this.applicationInstance
    }

}