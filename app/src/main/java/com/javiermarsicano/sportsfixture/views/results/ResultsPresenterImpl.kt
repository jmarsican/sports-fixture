package com.javiermarsicano.sportsfixture.views.results

import com.javiermarsicano.sportsfixture.common.mvp.BaseMVPPresenter
import com.javiermarsicano.sportsfixture.data.repository.ResultsRepository
import com.javiermarsicano.sportsfixture.views.fixtureslist.ResultsPresenter
import com.javiermarsicano.sportsfixture.views.fixtureslist.ResultsView
import com.javiermarsicano.sportsfixture.views.models.Fixture
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ResultsPresenterImpl @Inject constructor(private val repository: ResultsRepository): BaseMVPPresenter<ResultsView>(), ResultsPresenter {

    fun getCache() = listOf<Fixture>()

    override fun getResults() {
        viewReference.get()?.showLoading()
        repository.getResults()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewReference.get()?.hideLoading()
                    viewReference.get()?.showResults(it)
                },{
                    viewReference.get()?.onError(it.localizedMessage)
                    viewReference.get()?.hideLoading()
                })
                .bindToLifecycle()
    }

}
