package com.javiermarsicano.sportsfixture.views.fixtureslist

import com.javiermarsicano.sportsfixture.common.mvp.BaseMVPPresenter
import com.javiermarsicano.sportsfixture.data.FixtureRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FixturesListPresenterImpl(val repository: FixtureRepository): BaseMVPPresenter<FixtureView>() {
    fun getFixtures() {
        repository.getFixtures()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewReference.get()?.showFixtures(it)
                },{})
                .bindToLifecycle()
    }

}
