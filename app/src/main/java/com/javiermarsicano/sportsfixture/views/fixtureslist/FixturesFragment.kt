package com.javiermarsicano.sportsfixture.views.fixtureslist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.javiermarsicano.sportsfixture.MyApplication
import com.javiermarsicano.sportsfixture.R
import com.javiermarsicano.sportsfixture.common.mvp.BaseMVPFragment
import com.javiermarsicano.sportsfixture.views.adapters.MyItemRecyclerViewAdapter
import com.javiermarsicano.sportsfixture.views.models.Fixture
import kotlinx.android.synthetic.main.fragment_items_list.*
import kotlinx.android.synthetic.main.fragment_items_list.view.*
import timber.log.Timber
import javax.inject.Inject

class FixturesFragment: BaseMVPFragment<FixtureView, FixturesListPresenter>(), FixtureView {

    @Inject
    lateinit var mPresenter: FixturesListPresenterImpl

    private lateinit var mAdapter: MyItemRecyclerViewAdapter

    companion object {
        fun newInstance() = FixturesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId(), container, false)

        view.items_list.layoutManager = LinearLayoutManager(context)
        mAdapter = MyItemRecyclerViewAdapter(mutableListOf())

        view.items_list.items_list.adapter = mAdapter

        (activity.application as MyApplication).component.inject(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filter.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text.isNullOrBlank()) {
                    mPresenter.getFixtures()
                } else {
                    mAdapter.filter(text ?: "")
                }

                return false
            }

            override fun onQueryTextChange(text: String?): Boolean = false
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            val previousItems = getPresenter().getCache()
            mAdapter.setItemsList(previousItems.toMutableList())
        } else {
            getPresenter().getFixtures()
        }
    }

    override fun getPresenter() = mPresenter

    override fun layoutId() = R.layout.fragment_items_list

    override fun showFixtures(fixtures: List<Fixture>?) {
        Timber.i("repo count received  ${fixtures?.size}")
        if (fixtures != null) mAdapter.setItemsList(fixtures.toMutableList())
    }
}