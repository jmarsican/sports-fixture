package com.javiermarsicano.sportsfixture.views.main

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.javiermarsicano.sportsfixture.views.fixtureslist.FixturesFragment

class TabsAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int) =
        when (position) {
            0 -> FixturesFragment.newInstance()
            else -> FixturesFragment()
        }
}