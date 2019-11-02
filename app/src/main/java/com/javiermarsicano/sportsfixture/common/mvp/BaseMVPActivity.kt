package com.javiermarsicano.sportsfixture.common.mvp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.javiermarsicano.sportsfixture.R
import java.util.UUID


abstract class BaseMVPActivity<in V : MVPView, T : MVPPresenter<V>> : FragmentActivity(), MVPView  {

    private lateinit var activityId: String
    private var loaderView: View? = null
    protected var activityPresenter: T? = null

    protected abstract fun getPresenter(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityId(savedInstanceState)
    }

    private fun initActivityId(savedInstanceState: Bundle?) {
        savedInstanceState?.getString(KEY_ACTIVITY_ID, null)?.let { id ->
            activityId = id
        } ?: run {
            activityId = UUID.randomUUID().toString()
        }
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        activityPresenter?.onBindView(this as V)
    }

    override fun onResume() {
        super.onResume()
        activityPresenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        activityPresenter?.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_ACTIVITY_ID, activityId)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        activityPresenter?.onDestroy()
    }

    protected fun setLoaderView(view: View) {
        loaderView = view
    }

    override fun showLoading() {
        hideLoading()
        loaderView?.visibility = VISIBLE
    }

    override fun hideLoading() {
        loaderView?.visibility = GONE
    }

    override fun onError(resId: Int) {
        onError(getString(resId))
    }

    override fun onError(message: String?) {
        if (message != null && message.isNotBlank()) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, getString(R.string.generic_error), Toast.LENGTH_LONG).show()
        }
    }

    override fun hideKeyboard() {
        currentFocus?.let {
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    companion object {
        private const val KEY_ACTIVITY_ID = "key_activity_id"
    }
}
