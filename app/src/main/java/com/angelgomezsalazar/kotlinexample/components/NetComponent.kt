package com.angelgomezsalazar.kotlinexample.components

import com.angelgomezsalazar.kotlinexample.activities.MainActivity
import com.angelgomezsalazar.kotlinexample.modules.AppModule
import com.angelgomezsalazar.kotlinexample.modules.NetworkModule

import javax.inject.Singleton

import dagger.Component

/**
 * Created by angelgomez on 9/13/16.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface NetComponent {

    fun inject(mainActivity: MainActivity)

}
