package com.angelgomezsalazar.kotlinexample.components;

import com.angelgomezsalazar.kotlinexample.activities.MainActivity;
import com.angelgomezsalazar.kotlinexample.modules.AppModule;
import com.angelgomezsalazar.kotlinexample.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by angelgomez on 9/13/16.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class })
public interface NetComponent {

    void inject(MainActivity mainActivity);

}
