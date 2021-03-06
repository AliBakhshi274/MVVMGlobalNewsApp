package com.example.mvvmglobalnewsapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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
import com.example.mvvmglobalnewsapp.ui.no_internet_connection.noInternetConnectionActivity
import com.example.mvvmglobalnewsapp.ui.saved.SavedNewsActivity
import com.example.mvvmglobalnewsapp.ui.search.SearchActivity
import com.example.mvvmglobalnewsapp.ui.settings.SettingsActivity
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.BUSINESS_PAGE_NUMBER
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.ENTERTAINMENT_PAGE_NUMBER
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.GENERAL_PAGE_NUMBER
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.HEALTH_PAGE_NUMBER
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.SCIENCE_PAGE_NUMBER
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.SPORTS_PAGE_NUMBER
import com.example.mvvmglobalnewsapp.utils.Constants.Companion.TECHNOLOGY_PAGE_NUMBER
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

        mvvmSkeleton()

        hasInternetConnection()

    }

    private fun hasInternetConnection() {
        if (viewModel.hasInternetConnection()) {
            setContentView(R.layout.activity_main)
            uiFeatures()
        } else {
            startActivity(Intent(this, noInternetConnectionActivity::class.java))
        }
    }

    private fun mvvmSkeleton() {
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = MainViewModelProviderFactory(application, newsRepository)
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
                viewPager.setCurrentItem(GENERAL_PAGE_NUMBER)
            R.id.nav_sports ->
                viewPager.setCurrentItem(SPORTS_PAGE_NUMBER)
            R.id.nav_health ->
                viewPager.setCurrentItem(HEALTH_PAGE_NUMBER)
            R.id.nav_entertainment ->
                viewPager.setCurrentItem(ENTERTAINMENT_PAGE_NUMBER)
            R.id.nav_science ->
                viewPager.setCurrentItem(SCIENCE_PAGE_NUMBER)
            R.id.nav_business ->
                viewPager.setCurrentItem(BUSINESS_PAGE_NUMBER)
            R.id.nav_technology ->
                viewPager.setCurrentItem(TECHNOLOGY_PAGE_NUMBER)
            R.id.nav_about -> {
                nav_about()
            }
            R.id.nav_settings -> {
                nav_settings()
            }
            R.id.nav_search -> {
                nav_search()
            }
            R.id.nav_saved -> {
                nav_favorites()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    private fun nav_favorites() {
        startActivity(Intent(this, SavedNewsActivity().javaClass))
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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
















