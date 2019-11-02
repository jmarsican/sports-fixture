package com.javiermarsicano.sportsfixture.data

import com.javiermarsicano.sportsfixture.data.models.Fixture
import io.reactivex.Single

interface FixtureRepository {
    fun getFixtures(): Single<List<Fixture>>
}
