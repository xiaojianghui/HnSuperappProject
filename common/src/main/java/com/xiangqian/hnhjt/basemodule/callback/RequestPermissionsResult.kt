package com.xiangqian.hnhjt.basemodule.callback

interface RequestPermissionsResult {
    fun onSucceed(requestCode: Int, grantPermissions: List<String?>?)

    fun onFailed(requestCode: Int, deniedPermissions: List<String?>?)
}