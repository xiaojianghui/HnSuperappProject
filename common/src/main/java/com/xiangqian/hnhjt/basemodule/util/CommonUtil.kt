package com.xiangqian.hnhjt.basemodule.util

import android.content.pm.PackageManager.NameNotFoundException
import android.text.TextUtils
import com.blankj.utilcode.util.Utils
import com.tencent.mmkv.MMKV

enum class CommonUtil {
    //对象
    INSTANCE{};
    //获取Token
    open fun getSuperToken(): String? {
        val mmkv = MMKV.mmkvWithID(CommonKey.mmkvSuperToken)
        val toKen = mmkv.decodeString(CommonKey.mmkvSuperTokenkey, "")
        return if (TextUtils.isEmpty(toKen)) "" else toKen
    }

    //存入Token
    open fun setSuperToken(toKen: String?) {
        if (!TextUtils.isEmpty(toKen)) {
            val mmkv = MMKV.mmkvWithID(CommonKey.mmkvSuperToken)
            mmkv.encode(CommonKey.mmkvSuperTokenkey, toKen)
        }
    }

    open fun getAppVersionCode(): String? {
        var nowVersionCode = "0"
        try {
            nowVersionCode = Utils.getApp().getPackageManager()
                .getPackageInfo("com.xiangqian.hnhjt.superapp", 0).versionName
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
        }
        return nowVersionCode
    }

}