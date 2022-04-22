package com.xiangqian.hnhjt.basemodule.retrofit

import android.text.TextUtils
import android.view.Gravity
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.xiangqian.hnhjt.basemodule.util.CommonValue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallbackImpl<T> : Callback<ServerResult<T>> {

    private var listener: Listener<T>? = null

    fun CallbackImpl(listener: Listener<T>) {
        this.listener = listener
    }

    override fun onResponse(call: Call<ServerResult<T>>, response: Response<ServerResult<T>>) {
        try {
            val serverResult = response.body()

            //获取请求地址，可以特殊处理某个接口
//            var requestUrl = ""
//            if (call?.request() != null && call.request().url() != null) {
//                requestUrl = call.request().url().toString()
//            }
            if (serverResult == null) {
                if (listener != null) {
                    listener!!.onFinish(
                        null,
                        NetWorkStatus.UNKNOWN_ERROR,
                        CommonValue.sys_server_processing_request_exception,
                        null
                    )
                }
                return
            }
            val errorCode = serverResult.getStatus()
            val msg = serverResult.getMsg()
            val success = serverResult.getSuccess()
            //接口错误，统一toast处理
            if (errorCode != NetWorkStatus.SUCCESS) {
                if (!TextUtils.isEmpty(msg))
                    ToastUtils.getDefaultMaker().setGravity(Gravity.CENTER, 0, 0).show(msg)
            }
            if (listener != null) {
                listener!!.onFinish(success, errorCode, msg, serverResult.getData())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ToastUtils.getDefaultMaker().setGravity(Gravity.CENTER, 0, 0)
                .show(CommonValue.sys_server_processing_request_exception)
            if (listener != null) {
                listener!!.onFinish(null, NetWorkStatus.UNKNOWN_ERROR, e.toString(), null)
            }
        }
    }

    override fun onFailure(call: Call<ServerResult<T>>, t: Throwable) {
        //获取请求地址，可以特殊处理某个接口

        //获取请求地址，可以特殊处理某个接口
        var requestUrl = ""
        if (call?.request() != null && call.request().url() != null) {
            requestUrl = call.request().url().toString()
        }
        //TODO  这个地方可以利用requestUrl添加特殊接口处理
        ToastUtils.getDefaultMaker().setGravity(Gravity.CENTER, 0, 0)
            .show(RxExceptionUtil.exceptionHandler(Utils.getApp().applicationContext, t))
        if (listener != null) {
            listener!!.onFinish(
                null,
                NetWorkStatus.UNKNOWN_ERROR,
                RxExceptionUtil.exceptionHandler(Utils.getApp().applicationContext, t),
                null
            )
        }

    }
}