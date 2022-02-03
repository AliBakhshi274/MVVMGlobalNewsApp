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
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.BUSINESS_PAGE_NUMBER
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.ENTERTAINMENT_PAGE_NUMBER
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.GENERAL_PAGE_NUMBER
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.HEALTH_PAGE_NUMBER
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.SCIENCE_PAGE_NUMBER
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.SPORTS_PAGE_NUMBER
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.TECHNOLOGY_PAGE_NUMBER
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
            GENERAL_PAGE_NUMBER -> return GeneralFragment()
            SPORTS_PAGE_NUMBER -> return SportsFragment()
            HEALTH_PAGE_NUMBER -> return HealthFragment()
            ENTERTAINMENT_PAGE_NUMBER -> return EntertainmentFragment()
            SCIENCE_PAGE_NUMBER -> return ScienceFragment()
            BUSINESS_PAGE_NUMBER -> return BusinessFragment()
            TECHNOLOGY_PAGE_NUMBER -> return TechnologyFragment()
            else -> return GeneralFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            GENERAL_PAGE_NUMBER -> return context.getString(tabTitles.get(0))
            SPORTS_PAGE_NUMBER -> return context.getString(tabTitles.get(1))
            HEALTH_PAGE_NUMBER -> return context.getString(tabTitles.get(2))
            ENTERTAINMENT_PAGE_NUMBER -> return context.getString(tabTitles.get(3))
            SCIENCE_PAGE_NUMBER -> return context.getString(tabTitles.get(4))
            BUSINESS_PAGE_NUMBER -> return context.getString(tabTitles.get(5))
            else -> return context.getString(tabTitles.get(6))
        }
    }

    override fun getCount(): Int {
        return TOTAL_PAGES
    }

}













