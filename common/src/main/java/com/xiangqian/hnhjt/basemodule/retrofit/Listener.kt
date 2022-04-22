package com.xiangqian.hnhjt.basemodule.retrofit

interface Listener<T> {
    fun onFinish(success: Boolean?, code: Int, msg: String?, data: T?)
}