package com.example.mvvmglobalnewsapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.example.mvvmglobalnewsapp.R
import com.example.mvvmglobalnewsapp.ui.home.FragmentPagerAdapter
import com.example.mvvmglobalnewsapp.utils.Constants
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewPager: ViewPager
    private lateinit var drawerLayout: DrawerLayout

    val constants: Constants = Constants()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val toggle =
            ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_navigation_drawer,
                R.string.close_navigation_drawer
            )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        viewPager = findViewById(R.id.viewpager)

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        // default view when starting the app
        onNavigationItemSelected(navigationView.menu.getItem(0).setCheckable(true))

        val fragmentPagerAdapter = FragmentPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = fragmentPagerAdapter

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId

        when (itemId) {
            R.id.nav_general ->
                viewPager.setCurrentItem(constants.GENERAL)
            R.id.nav_sports ->
                viewPager.setCurrentItem(constants.SPORTS)
            R.id.nav_health ->
                viewPager.setCurrentItem(constants.HEALTH)
            R.id.nav_entertainment ->
                viewPager.setCurrentItem(constants.ENTERTAINMENT)
            R.id.nav_science ->
                viewPager.setCurrentItem(constants.SCIENCE)
            R.id.nav_business ->
                viewPager.setCurrentItem(constants.BUSINESS)
            R.id.nav_technology ->
                viewPager.setCurrentItem(constants.TECHNOLOGY)
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.top_app_bar_search) return super.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

}
















