package com.javiermarsicano.sportsfixture.data

import com.javiermarsicano.sportsfixture.data.models.Fixture
import com.javiermarsicano.sportsfixture.data.services.SportsApiServices
import io.reactivex.Single

class FixtureRepositoryImpl(val apiServices: SportsApiServices): FixtureRepository {
    override fun getFixtures(): Single<List<Fixture>> {
        return apiServices.getFixtures()
        //TODO cache
    }
}