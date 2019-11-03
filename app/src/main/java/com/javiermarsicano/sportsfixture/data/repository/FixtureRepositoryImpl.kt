package com.javiermarsicano.sportsfixture.data.repository

import com.javiermarsicano.sportsfixture.data.db.FixturesDatabase
import com.javiermarsicano.sportsfixture.data.services.SportsApiServices
import com.javiermarsicano.sportsfixture.data.services.dto.toVieModel
import com.javiermarsicano.sportsfixture.views.models.Fixture
import com.javiermarsicano.sportsfixture.views.models.toDao
import com.javiermarsicano.sportsfixture.views.models.toViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FixtureRepositoryImpl(val apiServices: SportsApiServices, private val database: FixturesDatabase): FixtureRepository {

    override fun getCache() = cached

    var cached: ArrayList<Fixture> = arrayListOf()

    override fun getFixtures(): Single<List<Fixture>> {
        return apiServices.getFixtures()
                .map {
                    it.map { it.toVieModel() }
                }
                .doOnSuccess {
                    saveToLocal(it)
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .subscribe {
                                Timber.d("Stored all results")
                            }
                }
                .onErrorResumeNext{
                    database.fixtureDao().load().map {
                        it.map { it.toViewModel() }
                    }
                }
    }

    private fun saveToLocal(result: List<Fixture>)  = Observable.fromCallable {
        cached.clear()
        cached.addAll(result)

        database.fixtureDao()
                .save(result.map { it.toDao() })
    }
}