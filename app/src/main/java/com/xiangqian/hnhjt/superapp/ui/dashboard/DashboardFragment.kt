package com.xiangqian.hnhjt.superapp.ui.dashboard

import com.xiangqian.hnhjt.basemodule.config.DataBindingConfig
import com.xiangqian.hnhjt.basemodule.view.CommonFragment
import com.xiangqian.hnhjt.superapp.BR
import com.xiangqian.hnhjt.superapp.R
import com.xiangqian.hnhjt.superapp.databinding.FragmentDashboardBinding

class DashboardFragment : CommonFragment<FragmentDashboardBinding>() {
    private lateinit var mDashboardViewModel: DashboardViewModel


    override fun initView() {
    }

    override fun initViewModel() {
        mDashboardViewModel = getFragmentViewModel(DashboardViewModel::class.java)
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_dashboard
    }
}