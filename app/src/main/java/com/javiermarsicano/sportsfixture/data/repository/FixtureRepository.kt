package com.javiermarsicano.sportsfixture.data.repository

import com.javiermarsicano.sportsfixture.views.viewmodels.Fixture
import io.reactivex.Single

interface FixtureRepository {
    fun getFixtures(): Single<List<Fixture>>
    fun getCache(): List<Fixture>
}
