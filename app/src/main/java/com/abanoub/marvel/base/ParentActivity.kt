package com.abanoub.marvel.base

import com.abanoub.marvel.R
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


open class ParentActivity : AppCompatActivity() {
    private var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    protected open fun removeFragments() {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment != null) {
                if (fragment.tag == null || !fragment.tag!!.contains("glide")) {
                    supportFragmentManager.beginTransaction().remove(fragment)
                        .commitAllowingStateLoss()
                }
            }
        }
    }

    open fun addFragment(containerId: Int, fragment: Fragment, tag: String?) {
        supportFragmentManager
            .beginTransaction()
            .add(containerId, fragment, tag)
            .commit()
    }

    open fun addFragmentWithBackStack(
        containerId: Int,
        fragment: Fragment,
        tag: String?
    ) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(containerId, fragment, tag)
            .commit()
    }

    open fun replaceWithBackStack(
        containerId: Int,
        fragment: Fragment,
        tag: String?
    ) {
        supportFragmentManager
            .beginTransaction() //  .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
            .addToBackStack(null)
            .replace(containerId, fragment, tag)
            .commit()
    }

    open fun replaceFragment(
        containerId: Int,
        fragment: Fragment,
        tag: String?
    ) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragment, tag)
            .commit()
    }

    open fun isConnected(): Boolean {
        val conManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo = conManager.activeNetworkInfo
        var conn = internetInfo != null && internetInfo.isConnected
        if (!conn) {
            showMessage(R.string.offline)
        }
        return conn
    }

    open fun showDialog() {
        if (dialog != null && dialog!!.isShowing) return
        if (dialog != null && !dialog!!.isShowing) {
            dialog!!.show()
        } else {
            dialog = ProgressDialog.show(this, "", getString(R.string.loading))
            dialog!!.setCancelable(true)
        }
    }

    open fun showMessage(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    open fun showMessage(msg: Int) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun hideSoftKeyboard() {
        try {
            val inputMethodManager: InputMethodManager =
                getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(
                    this.currentFocus!!.windowToken,
                    0
                )
            } //if
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    open fun hideDialog() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }
}

