package com.javiermarsicano.sportsfixture.views.fixtureslist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.javiermarsicano.sportsfixture.R
import com.javiermarsicano.sportsfixture.common.mvp.BaseMVPFragment
import com.javiermarsicano.sportsfixture.data.models.Fixture
import kotlinx.android.synthetic.main.fragment_items_list.*
import timber.log.Timber

class FixturesFragment: BaseMVPFragment<FixtureView, FixturesListPresenter>(), FixtureView {

    private lateinit var mAdapter: MyItemRecyclerViewAdapter

    companion object {
        fun newInstance() = FixturesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId(), container, false)

        items_list.layoutManager = LinearLayoutManager(context)
        mAdapter = MyItemRecyclerViewAdapter(mutableListOf())

        items_list.adapter = mAdapter

        return view
    }

    override fun getPresenter(): FixturesListPresenter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun layoutId() = R.layout.fragment_items_list

    override fun showFixtures(fixtures: List<Fixture>?) {
        Timber.i("repo count received  ${fixtures?.size}")
        if (fixtures != null) mAdapter.setItemsList(fixtures.toMutableList())
    }
}