package com.xiangqian.hnhjt.zxin.view

import android.Manifest
import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.king.zxing.CaptureHelper
import com.king.zxing.DecodeFormatManager
import com.king.zxing.OnCaptureCallback
import com.king.zxing.camera.CameraManager
import com.king.zxing.camera.FrontLightMode
import com.xiangqian.hnhjt.basemodule.callback.RequestPermissionsResult
import com.xiangqian.hnhjt.basemodule.util.ARouterPath
import com.xiangqian.hnhjt.basemodule.view.CommonActivity
import com.xiangqian.hnhjt.zxin.R
import com.xiangqian.hnhjt.zxin.databinding.ZxinActQrscannerBinding
import com.xiangqian.hnhjt.zxin.viewmodel.QRScannerViewModel

@Route(path = ARouterPath.QR_SCANNER_ACTIVITY)
class QRScannerActivity : CommonActivity<ZxinActQrscannerBinding>(), OnCaptureCallback {

    private lateinit var mQRScannerViewModel: QRScannerViewModel
    private lateinit var captureHelper: CaptureHelper

    override fun initViewModel() {
        mQRScannerViewModel = getActivityViewModel(QRScannerViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.zxin_act_qrscanner
    }

    override fun initView(savedInstanceState: Bundle?) {
        requestPermission(10010, arrayOf(Manifest.permission.CAMERA), object :
            RequestPermissionsResult {
            override fun onSucceed(requestCode: Int, grantPermissions: List<String?>?) {
                initQRConfig()
            }

            override fun onFailed(requestCode: Int, deniedPermissions: List<String?>?) {
                ToastUtils.getDefaultMaker().setGravity(Gravity.CENTER, 0, 0).show("请打开相机权限！")
                finish()
            }
        })

    }


    /**
     * 初始化QRScanner配置
     */
    private fun initQRConfig() {
        captureHelper = CaptureHelper(this, binding.svPreview, binding.vfv, null)
        captureHelper.setOnCaptureCallback(this)
        captureHelper.decodeFormats(DecodeFormatManager.QR_CODE_FORMATS)
            .supportAutoZoom(true) // 自动缩放
            .fullScreenScan(true) // 全屏扫码识别
            .supportLuminanceInvert(true) // 是否支持识别反色码，黑白颜色反转，开启提高识别效率
            .continuousScan(true) // 开启连续扫描
            .autoRestartPreviewAndDecode(false) // 连续扫描开启情况下，取消自动继续扫描，自己处理完后调用restartPreviewAndDecode()
            .playBeep(true) // 播放beep声音
            .supportZoom(true) // 支持双指缩放
            .frontLightMode(FrontLightMode.OFF) // 默认关闭闪光灯
            .setOnCaptureCallback(this) // 设置回调
            .onCreate()
        val cameraManager: CameraManager = captureHelper.cameraManager
        cameraManager.setOnTorchListener { torch: Boolean ->
            binding.tvTorch.isSelected = torch
            binding.tvTorch.text = if (torch) "关闭照明" else "打开照明"
        }
        binding.tvTorch.setOnClickListener(View.OnClickListener {
            cameraManager.setTorch(
                !binding.tvTorch.isSelected
            )
        })
        binding.tvTorch.post(Runnable { this.updateScanFrameLocation() })
    }

    /**
     * 更新扫描框位置
     */
    private fun updateScanFrameLocation() {
        //(327+184)/2-184
        binding.vfv.setPadding(0, 0, 0, ConvertUtils.dp2px(71f))
        binding.vfv.scannerStart = 0 // 动态改变padding时，需要设置该值为0，以触发在onDraw中对其的重新赋值
    }

    override fun onResultCallback(result: String?): Boolean {
        TODO("处理扫描结果")
    }
}