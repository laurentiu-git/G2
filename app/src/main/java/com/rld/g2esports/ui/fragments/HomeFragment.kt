package com.rld.g2esports.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.rld.g2esports.R
import com.rld.g2esports.adapters.ViewPagerAdapter
import com.rld.g2esports.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var fragmentBinding: FragmentHomeBinding? = null
    private var mediator: TabLayoutMediator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        fragmentBinding = binding

        val fragmentList = arrayListOf(
            ShopFragment(),
            BestSellingFragment()
        )


        binding.viewpager.adapter = ViewPagerAdapter(this, fragmentList)

        mediator =  TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = "Latest"
                1 -> tab.text = "Best selling"
            }
        }
        mediator?.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding?.viewpager?.adapter = null
        mediator?.detach()
        mediator = null
        fragmentBinding = null
    }
}
