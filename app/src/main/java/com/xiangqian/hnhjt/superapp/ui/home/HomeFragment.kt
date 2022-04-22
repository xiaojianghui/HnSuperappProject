package com.xiangqian.hnhjt.superapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.xiangqian.hnhjt.basemodule.glide.ImageLoaderUtils
import com.xiangqian.hnhjt.basemodule.util.ARouterPath
import com.xiangqian.hnhjt.superapp.databinding.FragmentHomeBinding
import com.xiangqian.hnhjt.superapp.ui.TestActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        textView.setOnClickListener {
            ARouter.getInstance().build(ARouterPath.TEXT_ACTIVITY).navigation()
        }
        ImageLoaderUtils().getInstance()!!
            .displayImage(activity, "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D765d3a715fb5c9ea62f303e3e538b622%2F3801213fb80e7bec16210f9b2b2eb9389a506bd4.jpg&refer=http%3A%2F%2Fimgsa.baidu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1653228217&t=dbd89fcd8384ea4a1da9043e774cf344", binding.imgIcon, DiskCacheStrategy.ALL)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}