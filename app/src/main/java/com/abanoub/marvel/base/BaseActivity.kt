package com.abanoub.marvel.base

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.lifecycle.Observer


abstract class BaseActivity<T : BaseViewModel> : ParentActivity() {
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().loadingProgress.observe(
            this, Observer { load -> if (load != null && load) showDialog() else hideDialog() })
        getViewModel().showMessage.observe(this, Observer { msg -> msg?.let { showMessage(it) } })
        getViewModel().showMessageRes.observe(this, Observer { msg -> msg?.let { showMessage(it) } })
    }

    abstract fun getViewModel(): T
}