package com.angelgomezsalazar

import android.app.Application
import com.angelgomezsalazar.kotlinexample.components.DaggerNetComponent
import com.angelgomezsalazar.kotlinexample.components.NetComponent
import com.angelgomezsalazar.kotlinexample.modules.AppModule
import com.angelgomezsalazar.kotlinexample.modules.NetworkModule


/**
 * Created by angelgomez on 9/13/16.
 */
class MyApplication : Application() {

    lateinit var netComponent: NetComponent

    override fun onCreate() {
        super.onCreate()

        // No idea why the builder modules are being marked as deprecated but it's fine
        netComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule("https://api.themoviedb.org/3/"))
                .build()
    }

}
