package com.javiermarsicano.sportsfixture.views.fixtureslist

import com.javiermarsicano.sportsfixture.common.mvp.MVPView
import com.javiermarsicano.sportsfixture.views.models.Fixture

interface ResultsView: MVPView {
    fun showResults(data: List<Fixture>)

}
