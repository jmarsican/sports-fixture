package com.javiermarsicano.sportsfixture.data.services

import com.javiermarsicano.sportsfixture.data.models.Fixture
import io.reactivex.Single
import retrofit2.http.GET

interface SportsApiServices {
    @GET("/fixtures.json")
    fun getFixtures(): Single<List<Fixture>>
}