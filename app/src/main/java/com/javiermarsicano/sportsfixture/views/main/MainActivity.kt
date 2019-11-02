package com.javiermarsicano.sportsfixture.views.main

import android.os.Bundle
import com.javiermarsicano.sportsfixture.R
import com.javiermarsicano.sportsfixture.common.mvp.BaseMVPActivity
import com.javiermarsicano.sportsfixture.views.fixtureslist.FixturesFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : BaseMVPActivity<MainView, MainPresenter>(), MainView {
    override fun getPresenter() = MainPresenterImpl(WeakReference(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add list fragment if this is first creation
        if (savedInstanceState == null) {
            val fragment = FixturesFragment.newInstance()

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }

        setLoaderView(loaderView)
    }

}
