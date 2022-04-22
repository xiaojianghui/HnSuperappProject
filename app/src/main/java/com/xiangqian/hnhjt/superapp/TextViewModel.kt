package com.xiangqian.hnhjt.superapp

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextViewModel : ViewModel(), LifecycleObserver {
    var mTextContent: MutableLiveData<String> =
        MutableLiveData<String>()


    fun chageTextContent() {
        var randoms = (0..10).random()
        mTextContent.value = "随机$randoms"
    }
}