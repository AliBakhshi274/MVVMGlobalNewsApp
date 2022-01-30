package com.example.mvvmglobalnewsapp.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mvvmglobalnewsapp.R
import com.example.mvvmglobalnewsapp.ui.home.business.BusinessFragment
import com.example.mvvmglobalnewsapp.ui.home.entertainment.EntertainmentFragment
import com.example.mvvmglobalnewsapp.ui.home.general.GeneralFragment
import com.example.mvvmglobalnewsapp.ui.home.health.HealthFragment
import com.example.mvvmglobalnewsapp.ui.home.science.ScienceFragment
import com.example.mvvmglobalnewsapp.ui.home.sports.SportsFragment
import com.example.mvvmglobalnewsapp.ui.home.technology.TechnologyFragment
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.BUSINESS
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.ENTERTAINMENT
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.GENERAL
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.HEALTH
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.SCIENCE
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.SPORTS
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.TECHNOLOGY
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.TOTAL_PAGES

class ViewpagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private var tabTitles = arrayOf(
        R.string.general,
        R.string.sports,
        R.string.health,
        R.string.science,
        R.string.entertainment,
        R.string.business,
        R.string.technology
    )

    //    get Item Fragment
    override fun getItem(position: Int): Fragment {
        when (position) {
            GENERAL -> return GeneralFragment()
            SPORTS -> return SportsFragment()
            HEALTH -> return HealthFragment()
            ENTERTAINMENT -> return EntertainmentFragment()
            SCIENCE -> return ScienceFragment()
            BUSINESS -> return BusinessFragment()
            TECHNOLOGY -> return TechnologyFragment()
            else -> return GeneralFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            GENERAL -> return context.getString(tabTitles.get(0))
            SPORTS -> return context.getString(tabTitles.get(1))
            HEALTH -> return context.getString(tabTitles.get(2))
            ENTERTAINMENT -> return context.getString(tabTitles.get(3))
            SCIENCE -> return context.getString(tabTitles.get(4))
            BUSINESS -> return context.getString(tabTitles.get(5))
            else -> return context.getString(tabTitles.get(6))
        }
    }

    override fun getCount(): Int {
        return TOTAL_PAGES
    }

}













