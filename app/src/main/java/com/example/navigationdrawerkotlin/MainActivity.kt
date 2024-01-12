package com.example.navigationdrawerkotlin

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.toolbar)

        //Step 1

        //Step 1
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.openDrawer,
            R.string.closeDrawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        loadFragment(HomeFragment(), false)

        navigationView.setNavigationItemSelectedListener { item: MenuItem ->
            val id = item.itemId
            if (id == R.id.nav_Home) {
                loadFragment(HomeFragment(), false)
            } else if (id == R.id.nav_Search) {
                loadFragment(SearchFragment(), false)
            } else if (id == R.id.nav_Settings) {
                loadFragment(SettingsFragment(), false)
            } else if (id == R.id.nav_Contact) {
                loadFragment(ContactFragment(), false)
            } else if (id == R.id.nav_Profile) {
                loadFragment(ProfileFragment(), false)
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun loadFragment(fragment: Fragment?, flag: Boolean) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        if (flag) ft.add(R.id.frameLayout, fragment!!) else ft.replace(R.id.frameLayout, fragment!!)
        ft.commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}