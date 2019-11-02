package com.javiermarsicano.sportsfixture.views.main

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.javiermarsicano.sportsfixture.R

class MainActivity : FragmentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add list fragment if this is first creation
    }

}
