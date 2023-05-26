package com.example.feature.status

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.core.common.MainViewModel
import com.example.core.common.utils.HATE
import com.example.core.common.utils.LOVE
import com.example.core.ui.utility.ToolbarTitle
import com.example.core.ui.utility.fragmentViewBinding
import com.example.feature.status.databinding.FragmentStatusBinding
import com.google.android.material.tabs.TabLayoutMediator

class StatusFragment : Fragment(R.layout.fragment_status) {

    private val binding by fragmentViewBinding(FragmentStatusBinding::bind)
    private val activityViewModel: MainViewModel by activityViewModels()

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