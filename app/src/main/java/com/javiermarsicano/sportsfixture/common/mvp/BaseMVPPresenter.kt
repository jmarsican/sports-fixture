package com.javiermarsicano.sportsfixture.common.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * BaseMVPPresenter
 *
 * It receives a WeakReference to an implementation of  MVPView to allow the GC to reclaim the resources
 * of that object when is no longer needed.
 *
 * e.g: The GC can reclaim an Activity that implements MVPView and its resources
 *  after the Activity is destroyed for a rotation change or a call of its finish() method
 * (This cannot happen if we keep a strong (default) reference)
 *
 */

abstract class BaseMVPPresenter<V : MVPView> : MVPPresenter<V> {
    protected lateinit var viewReference: WeakReference<V>

    private val disposable = CompositeDisposable()

    override fun onBindView(view : V) {
        viewReference = WeakReference(view)
    }

    override fun onDestroy() {
        viewReference.clear()
        disposable.clear()
    }

    /**
     * Will kill Rx processes to avoid memory leaks when onDestroy|onViewDestroyed is reached
     */
    protected fun Disposable.bindToLifecycle() = apply {
        disposable.add(this)
    }
}
