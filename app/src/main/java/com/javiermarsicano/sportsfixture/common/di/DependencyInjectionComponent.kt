package com.javiermarsicano.sportsfixture.common.di

import com.javiermarsicano.sportsfixture.views.fixtureslist.FixturesFragment
import com.javiermarsicano.sportsfixture.views.results.ResultsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RepositoriesModule::class))
interface DependencyInjectionComponent {

    fun inject(view: FixturesFragment)
    fun inject(view: ResultsFragment)
}