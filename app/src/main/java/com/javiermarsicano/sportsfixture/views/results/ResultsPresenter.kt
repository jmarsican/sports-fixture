package com.javiermarsicano.sportsfixture.views.fixtureslist

import com.javiermarsicano.sportsfixture.common.mvp.MVPPresenter

interface ResultsPresenter: MVPPresenter<ResultsView> {
    fun getResults()
}