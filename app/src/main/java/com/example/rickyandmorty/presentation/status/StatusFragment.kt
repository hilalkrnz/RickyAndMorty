package com.example.rickyandmorty.presentation.status

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.rickyandmorty.R
import com.example.rickyandmorty.databinding.FragmentStatusBinding
import com.example.rickyandmorty.presentation.MainActivityViewModel
import com.example.rickyandmorty.utility.HATE
import com.example.rickyandmorty.utility.LOVE
import com.example.rickyandmorty.utility.ToolbarTitle
import com.example.rickyandmorty.utility.fragmentViewBinding
import com.google.android.material.tabs.TabLayoutMediator

class StatusFragment : Fragment(R.layout.fragment_status) {

    private val binding by fragmentViewBinding(FragmentStatusBinding::bind)
    private val activityViewModel: MainActivityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel.setBottomBarVisibility(true)


        initStatusToolbarComponent()
        setTabLayout()
    }

    private fun initStatusToolbarComponent() {
        binding.statusToolbarComponent.setOnlyToolbarTitleVisibilityAndTitleText(
            true,
            ToolbarTitle.STATUS.toolbarTitle
        )
    }

    private fun setTabLayout() {
        val tabLayoutItems = arrayOf(
            String.LOVE,
            String.HATE
        )
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabLayoutItems[position]
        }.attach()

    }

}