package com.abanoub.marvel.base

import com.abanoub.marvel.R
import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.abanoub.marvel.data.remote.BaseCallBack


open class BaseViewModel(application: Application) : AndroidViewModel(application), BaseCallBack {
    var paginationLoadingProgress = MutableLiveData<Boolean>()
    var loadingProgress = MutableLiveData<Boolean>()
    var showMessage = MutableLiveData<String>()
    var showMessageRes = MutableLiveData<Int>()

    fun showHideLoadingProgress(shouldClearList: Boolean, value: Boolean, enableLoadingProgress: Boolean) {
        if (shouldClearList) {
            if (enableLoadingProgress) loadingProgress.setValue(value)
        } else paginationLoadingProgress.setValue(value)
    }

    override fun onDataNotAvailable(errorMsg: String?, code: String?) {
        loadingProgress.value = false
        printErrorMsg(errorMsg)
    }

    protected fun printErrorMsg(errorMsg: String?) {
        if (errorMsg == null || TextUtils.isEmpty(errorMsg)) {
            showMessageRes.setValue(R.string.serverError)
        } else {
            showMessage.setValue(errorMsg)
        }
    }
}