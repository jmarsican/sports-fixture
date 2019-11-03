package com.javiermarsicano.sportsfixture.views.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.javiermarsicano.sportsfixture.R
import com.javiermarsicano.sportsfixture.common.mvp.BaseMVPActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : BaseMVPActivity<MainView, MainPresenter>(), MainView {
    override fun getPresenter() = MainPresenterImpl(WeakReference(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setLoaderView(loaderView)

        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.fixtures)))
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.result)))
        val tabsAdapter = TabsAdapter(supportFragmentManager)
        view_pager.adapter = tabsAdapter
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab) {
                view_pager.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })

    }

}
