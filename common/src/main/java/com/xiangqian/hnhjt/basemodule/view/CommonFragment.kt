package com.xiangqian.hnhjt.basemodule.view

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xiangqian.hnhjt.basemodule.callback.RequestPermissionsResult
import com.xiangqian.hnhjt.basemodule.config.DataBindingConfig

abstract class CommonFragment<VDB : ViewDataBinding> : Fragment() {

    private var mBinding: VDB? = null
    open val binding get() = mBinding!!

    protected abstract fun initView()

    protected abstract fun initViewModel()

    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        dataBindingConfig = getDataBindingConfig()
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        initView()
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    protected fun <T : ViewModel> getFragmentViewModel(modelClass: Class<T>): T {
        return ViewModelProvider(this).get(modelClass)
    }


    /**
     * Request permissions with PermissionListener.
     */
    private var mRequestPermissionsResult: RequestPermissionsResult? = null

    private fun requestPermission(
        requestCode: Int,
        permissions: Array<String>,
        listener: RequestPermissionsResult
    ) {
        mRequestPermissionsResult = listener
        ActivityCompat.requestPermissions(requireActivity()!!, permissions, requestCode)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
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
    }

    private fun onRequestPermissionsResult(
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