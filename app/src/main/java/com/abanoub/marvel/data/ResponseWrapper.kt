package com.abanoub.marvel.data

import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.abanoub.marvel.data.remote.BaseCallBack

abstract class ResponseWrapper<T>(baseBaseCallback: BaseCallBack) :
    Callback<T> {
    private val baseBaseCallback: BaseCallBack
    private fun handleResponse(response: Response<T>) {
        val body = response.body()
        if (response.isSuccessful && body != null) {
            onSuccessCase(body)
        } else {
            handleError(response, baseBaseCallback)
        }
    }

    private fun handleError(
        response: Response<T>,
        callback: BaseCallBack
    ) {
        var errorMessage: String? = null
        var errorCode: String? = null
        try {
            val errorBody = response.errorBody()!!.string()
            val error = JSONObject(errorBody)
            if (errorBody.contains("message")) {
                errorMessage = error.getString("message")
                errorCode = error.getString("code")
                callback.onDataNotAvailable(errorMessage, errorCode)
            }
        } catch (e: Exception) {
            callback.onDataNotAvailable(errorMessage, errorCode)
            e.printStackTrace()
        }
    }

    override fun onResponse(
        call: Call<T>,
        response: Response<T>
    ) {
        handleResponse(response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        t.printStackTrace()
        baseBaseCallback.onDataNotAvailable(null, null)
    }

    abstract fun onSuccessCase(body: T)

    init {
        this.baseBaseCallback = baseBaseCallback
    }
}
