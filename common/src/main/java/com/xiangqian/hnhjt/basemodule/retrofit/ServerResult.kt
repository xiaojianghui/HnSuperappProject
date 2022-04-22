package com.xiangqian.hnhjt.basemodule.retrofit

import com.google.gson.annotations.SerializedName

class ServerResult<T> {

    @SerializedName("code")
    private var status = 0

    @SerializedName("success")
    private var success: Boolean? = null

    @SerializedName(value = "message", alternate = ["msg"])
    private var msg: String? = null

    @SerializedName(value = "result", alternate = ["data"])
    private var data: T? = null

    fun getSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
    }

    fun getStatus(): Int {
        return status
    }

    fun setStatus(status: Int) {
        this.status = status
    }

    fun getMsg(): String? {
        return msg
    }

    fun setMsg(msg: String?) {
        this.msg = msg
    }

    fun getData(): T? {
        return data
    }

    fun setData(data: T) {
        this.data = data
    }
}