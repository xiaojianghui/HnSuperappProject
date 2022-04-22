package com.xiangqian.hnhjt.basemodule.retrofit

import android.content.Context
import android.util.Log
import com.xiangqian.hnhjt.basemodule.util.CommonValue
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

class RxExceptionUtil {

    companion object {
        fun exceptionHandler(context: Context?, e: Throwable?): String? {
            if (context == null) {
                Log.d("RxExceptionUtil", "context == null")
                return ""
            }
            var errorMsg: String? =
                CommonValue.sys_server_processing_request_exception
            if (e is ConnectException || e is UnknownHostException) {
                errorMsg = CommonValue.sys_network_offline
            } else if (e is SocketTimeoutException) {
                errorMsg = CommonValue.sys_network_timeout
            } else if (e is HttpException) {
                errorMsg = convertStatusCode(context, e)
            } else if (e is ParseException || e is JSONException) {
                errorMsg = CommonValue.sys_data_error
            }
            return errorMsg
        }

        private fun convertStatusCode(context: Context, httpException: HttpException): String? {
            val msg: String = if (httpException.code() in 500..599) {
                CommonValue.sys_server_processing_request_exception
            } else if (httpException.code() in 400..499) {
                CommonValue.sys_server_processing_request_exception
            } else if (httpException.code() in 300..399) {
                CommonValue.sys_server_processing_request_exception
            } else {
                httpException.message()
            }
            return msg
        }
    }

}