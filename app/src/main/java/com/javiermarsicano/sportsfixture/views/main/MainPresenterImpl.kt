package com.javiermarsicano.sportsfixture.views.main

import com.javiermarsicano.sportsfixture.common.mvp.BaseMVPPresenter
import java.lang.ref.WeakReference

class MainPresenterImpl (viewReference: WeakReference<MainView>) : BaseMVPPresenter<MainView>(), MainPresenter {
}