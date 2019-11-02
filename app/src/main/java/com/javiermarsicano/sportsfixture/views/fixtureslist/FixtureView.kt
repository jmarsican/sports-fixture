package com.javiermarsicano.sportsfixture.views.fixtureslist

import com.javiermarsicano.sportsfixture.common.mvp.MVPView
import com.javiermarsicano.sportsfixture.data.models.Fixture

interface FixtureView: MVPView {
    fun showFixtures(fixtures: List<Fixture>)

}
