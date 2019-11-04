package com.javiermarsicano.sportsfixture.views.results

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
import com.javiermarsicano.sportsfixture.views.fixtureslist.ResultsPresenter
import com.javiermarsicano.sportsfixture.views.fixtureslist.ResultsView
import com.javiermarsicano.sportsfixture.views.models.Fixture
import kotlinx.android.synthetic.main.fragment_items_list.*
import kotlinx.android.synthetic.main.fragment_items_list.view.*
import timber.log.Timber
import javax.inject.Inject

class ResultsFragment : BaseMVPFragment<ResultsView, ResultsPresenter>(), ResultsView {

    @Inject
    lateinit var mPresenter: ResultsPresenterImpl

    private lateinit var mAdapter: MyItemRecyclerViewAdapter

    companion object {
        fun newInstance() = ResultsFragment()
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            val previousItems = getPresenter().getCache()
            mAdapter.setItemsList(previousItems.toMutableList())
        } else {
            getPresenter().getResults()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filter.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text.isNullOrBlank()) {
                    mPresenter.getResults()
                } else {
                    mAdapter.filter(text ?: "")
                }

                return false
            }

            override fun onQueryTextChange(text: String?): Boolean = false
        })
    }

    override fun getPresenter() = mPresenter

    override fun layoutId() = R.layout.fragment_items_list

    override fun showResults(fixtures: List<Fixture>) {
        Timber.i("repo count received  ${fixtures?.size}")
        mAdapter.setItemsList(fixtures.toMutableList())
    }
}