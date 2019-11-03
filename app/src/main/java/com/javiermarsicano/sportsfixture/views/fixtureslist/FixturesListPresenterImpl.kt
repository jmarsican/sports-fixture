package com.javiermarsicano.sportsfixture.views.fixtureslist

import com.javiermarsicano.sportsfixture.common.mvp.BaseMVPPresenter
import com.javiermarsicano.sportsfixture.data.repository.FixtureRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FixturesListPresenterImpl @Inject constructor(private val repository: FixtureRepository): BaseMVPPresenter<FixtureView>(), FixturesListPresenter {

    fun getCache() = repository.getCache()

    override fun getFixtures() {
        viewReference.get()?.showLoading()
        repository.getFixtures()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewReference.get()?.hideLoading()
                    viewReference.get()?.showFixtures(it)
                },{
                    viewReference.get()?.onError(it.localizedMessage)
                    viewReference.get()?.hideLoading()
                })
                .bindToLifecycle()
    }

}
