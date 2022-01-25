package com.example.mvvmglobalnewsapp.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mvvmglobalnewsapp.R
import com.example.mvvmglobalnewsapp.ui.home.slidingTab.business.BusinessFragment
import com.example.mvvmglobalnewsapp.ui.home.slidingTab.entertainment.EntertainmentFragment
import com.example.mvvmglobalnewsapp.ui.home.slidingTab.general.GeneralFragment
import com.example.mvvmglobalnewsapp.ui.home.slidingTab.health.HealthFragment
import com.example.mvvmglobalnewsapp.ui.home.slidingTab.science.ScienceFragment
import com.example.mvvmglobalnewsapp.ui.home.slidingTab.sports.SportsFragment
import com.example.mvvmglobalnewsapp.ui.home.slidingTab.technology.TechnologyFragment
import com.example.mvvmglobalnewsapp.utils.Constants

class SectionPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private val constants: Constants = Constants()

    private var tabTitles = arrayOf(
        R.string.general,
        R.string.sport,
        R.string.health,
        R.string.science,
        R.string.entertainment,
        R.string.business,
        R.string.technology
    )

    //    get Item Fragment
    override fun getItem(position: Int): Fragment {
        when (position) {
            constants.GENERAL -> return GeneralFragment()
            constants.SPORTS -> return SportsFragment()
            constants.HEALTH -> return HealthFragment()
            constants.ENTERTAINMENT -> return EntertainmentFragment()
            constants.SCIENCE -> return ScienceFragment()
            constants.BUSINESS -> return BusinessFragment()
            else -> return TechnologyFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            constants.GENERAL -> return context.getString(tabTitles.get(0))
            constants.SPORTS -> return context.getString(tabTitles.get(1))
            constants.HEALTH -> return context.getString(tabTitles.get(2))
            constants.ENTERTAINMENT -> return context.getString(tabTitles.get(3))
            constants.SCIENCE -> return context.getString(tabTitles.get(4))
            constants.BUSINESS -> return context.getString(tabTitles.get(5))
            else -> return context.getString(tabTitles.get(6))
        }
    }

    override fun getCount(): Int {
        return constants.TOTAL_PAGES
    }

}













