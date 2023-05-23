package com.example.rickyandmorty.presentation.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rickyandmorty.presentation.favorite.hate.HateFragment
import com.example.rickyandmorty.presentation.favorite.love.LoveFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            LoveFragment()
        } else {
            HateFragment()
        }
    }

}

const val TOTAL_TABS = 2