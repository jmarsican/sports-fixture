package com.javiermarsicano.sportsfixture

import android.app.Application
import com.javiermarsicano.sportsfixture.common.di.DaggerDependencyInjectionComponent
import com.javiermarsicano.sportsfixture.common.di.DependencyInjectionComponent
import com.javiermarsicano.sportsfixture.common.di.RepositoriesModule
import timber.log.Timber

class MyApplication: Application() {

    lateinit var component: DependencyInjectionComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        component = DaggerDependencyInjectionComponent.builder().repositoriesModule(RepositoriesModule(this)).build()

    }
}
