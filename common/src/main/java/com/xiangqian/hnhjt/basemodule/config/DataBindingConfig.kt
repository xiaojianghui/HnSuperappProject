package com.xiangqian.hnhjt.basemodule.config

import android.util.SparseArray

class DataBindingConfig {
    private val bindingParams: SparseArray<*> = SparseArray<Any>()

    fun getBindingParams(): SparseArray<*> {
        return bindingParams
    }

    fun addBindingParam(variableId: Int, obj: Any): DataBindingConfig {
        if (bindingParams[variableId] == null) {
            bindingParams.put(variableId, obj as Nothing?)
        }
        return this
    }
}