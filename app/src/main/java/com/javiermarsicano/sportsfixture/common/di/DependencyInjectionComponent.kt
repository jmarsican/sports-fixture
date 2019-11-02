package com.javiermarsicano.sportsfixture.common.di

import com.javiermarsicano.sportsfixture.views.fixtureslist.FixturesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RepositoriesModule::class))
interface DependencyInjectionComponent {

    fun inject(view: FixturesFragment)
}