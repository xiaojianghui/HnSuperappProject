package com.xiangqian.hnhjt.superapp.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.xiangqian.hnhjt.basemodule.config.DataBindingConfig
import com.xiangqian.hnhjt.basemodule.util.ARouterPath
import com.xiangqian.hnhjt.basemodule.view.CommonActivity
import com.xiangqian.hnhjt.superapp.BR
import com.xiangqian.hnhjt.superapp.R
import com.xiangqian.hnhjt.superapp.TextViewModel
import com.xiangqian.hnhjt.superapp.databinding.ActTextLayoutBinding

@Route(path = ARouterPath.TEXT_ACTIVITY)
class TestActivity : CommonActivity<ActTextLayoutBinding>() {
    private lateinit var mTextViewModel: TextViewModel
    override fun initViewModel() {
        mTextViewModel = getActivityViewModel(TextViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.act_text_layout
    }

    override fun initView(savedInstanceState: Bundle?) {

        mTextViewModel.mTextContent.observe(this) {
            binding.txtContent.text = it
        }
        binding.btnChange.setOnClickListener {
            mTextViewModel.chageTextContent()
        }
    }
}