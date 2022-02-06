package com.ainaz.tinkofftest.ui.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ainaz.tinkofftest.databinding.FragmentContainerBinding
import com.google.android.material.tabs.TabLayout

class ContainerFragment : Fragment() {
    private var _binding: FragmentContainerBinding? = null
    private val binding get() = _binding!!
    private var adapter: FragmentAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupPagerWithTabLayout()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupPagerWithTabLayout() {
        adapter = FragmentAdapter(childFragmentManager, lifecycle)
        binding.gifsViewpager.adapter = adapter

        binding.tabLayout.apply {
            addTab(newTab().setText("Последние"))
            addTab(newTab().setText("Лучшие"))
            addTab(newTab().setText("Горячие"))
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.gifsViewpager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.gifsViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                with(binding.tabLayout) {
                    selectTab(getTabAt(position))
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}