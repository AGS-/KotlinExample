package com.angelgomezsalazar.kotlinexample.modules

import android.app.Application

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by angelgomez on 9/13/16.
 */
@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    internal fun providesApplication(): Application {
        return application
    }

}
