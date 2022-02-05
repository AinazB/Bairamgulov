package com.ainaz.tinkofftest.ui.container

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ainaz.tinkofftest.ui.hot.HotFragment
import com.ainaz.tinkofftest.ui.latest.LatestFragment
import com.ainaz.tinkofftest.ui.top.TopFragment

class FragmentAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment = when (position) {
        1 -> HotFragment()
        2 -> TopFragment()
        else -> LatestFragment()
    }
}

private const val FRAGMENT_COUNT = 3