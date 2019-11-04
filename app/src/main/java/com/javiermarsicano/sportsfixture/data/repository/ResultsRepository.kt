package com.javiermarsicano.sportsfixture.data.repository

import com.javiermarsicano.sportsfixture.views.models.Fixture
import io.reactivex.Single

interface ResultsRepository {
    fun getResults(): Single<List<Fixture>>
    fun getCache(): List<Fixture>
}
