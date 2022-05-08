package com.xiangqian.hnhjt.basemodule.view

import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.xiangqian.hnhjt.basemodule.callback.RequestPermissionsResult
import com.xiangqian.hnhjt.basemodule.config.DataBindingConfig

open abstract class CommonActivity<VDB : ViewDataBinding> : AppCompatActivity() {

    private var mBinding: VDB? = null
    open val binding get() = mBinding!!
    protected abstract fun initViewModel()

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)  // Start auto inject.
        //保持先后顺序
        initViewModel() //1
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.executePendingBindings() //立即更新UI

        initView(savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    protected fun <T : ViewModel> getActivityViewModel(modelClass: Class<T>): T {
        return ViewModelProvider(this).get(modelClass)
    }


    private var mRequestPermissionsResult: RequestPermissionsResult? = null

    open fun requestPermission(
        requestCode: Int,
        permissions: Array<String?>?,
        listener: RequestPermissionsResult?
    ) {
        mRequestPermissionsResult = listener
        ActivityCompat.requestPermissions(this, permissions!!, requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        /**
         * 转给AndPermission分析结果。
         *
         * @param requestCode  请求码。
         * @param permissions  权限数组，一个或者多个。
         * @param grantResults 请求结果。
         * @param listener PermissionListener 对象。
         */
        if (mRequestPermissionsResult != null) onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            mRequestPermissionsResult!!
        )
        // 获取到Activity下的Fragment
        val fragments = supportFragmentManager.fragments
            ?: return
        // 查找在Fragment中onRequestPermissionsResult方法并调用
        for (fragment in fragments) {
            fragment?.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
        listener: RequestPermissionsResult
    ) {
        val grantedList: MutableList<String> = ArrayList()
        val deniedList: MutableList<String> = ArrayList()
        for (i in permissions.indices) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) grantedList.add(permissions[i]) else deniedList.add(
                permissions[i]
            )
        }
        if (deniedList.isEmpty()) listener.onSucceed(
            requestCode,
            grantedList
        ) else listener.onFailed(requestCode, deniedList)
    }

}