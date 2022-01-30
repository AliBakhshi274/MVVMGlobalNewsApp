package com.example.mvvmglobalnewsapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.mvvmglobalnewsapp.R
import com.example.mvvmglobalnewsapp.adapters.ViewpagerAdapter
import com.example.mvvmglobalnewsapp.database.ArticleDatabase
import com.example.mvvmglobalnewsapp.repository.NewsRepository
import com.example.mvvmglobalnewsapp.ui.about.AboutActivity
import com.example.mvvmglobalnewsapp.ui.search.SearchActivity
import com.example.mvvmglobalnewsapp.ui.settings.SettingsActivity
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.BUSINESS
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.ENTERTAINMENT
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.GENERAL
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.HEALTH
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.SCIENCE
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.SPORTS
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.TECHNOLOGY
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // mvvmSkeleton
    lateinit var viewModel: MainViewModel

    // uiConstants_Implementation
    private lateinit var viewPager: ViewPager
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mvvmSkeleton()

        uiFeatures()

    }

    private fun mvvmSkeleton() {
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = MainViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)
    }

    private fun uiFeatures() {

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

        val fragmentPagerAdapter = ViewpagerAdapter(this, supportFragmentManager)
        viewPager.adapter = fragmentPagerAdapter

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId

        when (itemId) {
            R.id.nav_general ->
                viewPager.setCurrentItem(GENERAL)
            R.id.nav_sports ->
                viewPager.setCurrentItem(SPORTS)
            R.id.nav_health ->
                viewPager.setCurrentItem(HEALTH)
            R.id.nav_entertainment ->
                viewPager.setCurrentItem(ENTERTAINMENT)
            R.id.nav_science ->
                viewPager.setCurrentItem(SCIENCE)
            R.id.nav_business ->
                viewPager.setCurrentItem(BUSINESS)
            R.id.nav_technology ->
                viewPager.setCurrentItem(TECHNOLOGY)
            R.id.nav_about -> {
                nav_about()
            }
            R.id.nav_settings -> {
                nav_settings()
            }
            R.id.nav_search -> {
                nav_search()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    private fun nav_search() {
        startActivity(Intent(this, SearchActivity().javaClass))
    }

    private fun nav_settings() {
        startActivity(Intent(this, SettingsActivity().javaClass))
    }

    private fun nav_about() {
        startActivity(Intent(this, AboutActivity().javaClass))
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
















