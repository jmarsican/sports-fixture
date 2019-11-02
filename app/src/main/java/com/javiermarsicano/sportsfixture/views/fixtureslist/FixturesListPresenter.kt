package com.javiermarsicano.sportsfixture.views.fixtureslist

import com.javiermarsicano.sportsfixture.common.mvp.MVPPresenter

interface FixturesListPresenter: MVPPresenter<FixtureView> {
    fun getFixtures()
}