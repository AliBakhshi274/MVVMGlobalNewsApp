package com.example.mvvmglobalnewsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.mvvmglobalnewsapp.R
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    private var tabIcons = intArrayOf(
        R.drawable.ic_general_item_24,
        R.drawable.ic_sports_item_24,
        R.drawable.ic_health_item,
        R.drawable.ic_entertainment_item_24,
        R.drawable.ic_science_item_24,
        R.drawable.ic_business_item_24,
        R.drawable.ic_technology_item_24
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, null)

        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewpager)

        viewPager?.adapter = FragmentPagerAdapter(requireContext() ,childFragmentManager)

        tabLayout?.post(Runnable {
            kotlin.run {
                tabLayout?.setupWithViewPager(viewPager)
                setupIcons()
            }
        })

        viewPager?.setOffscreenPageLimit(50)

        return view
    }

    private fun setupIcons() {
        tabLayout?.getTabAt(0)?.setIcon(tabIcons[0])
        tabLayout?.getTabAt(1)?.setIcon(tabIcons.get(1))
        tabLayout?.getTabAt(2)?.setIcon(tabIcons.get(2))
        tabLayout?.getTabAt(3)?.setIcon(tabIcons.get(3))
        tabLayout?.getTabAt(4)?.setIcon(tabIcons.get(4))
        tabLayout?.getTabAt(5)?.setIcon(tabIcons.get(5))
        tabLayout?.getTabAt(6)?.setIcon(tabIcons.get(6))
    }

}



















