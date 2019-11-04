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

class ResultsRepositoryImpl (val apiServices: SportsApiServices, private val database: FixturesDatabase): ResultsRepository {

    override fun getCache() = cached

    var cached: ArrayList<Fixture> = arrayListOf()

    override fun getResults(): Single<List<Fixture>> {
        return apiServices.getResults()
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
                    database.fixtureDao().loadResults().map {
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