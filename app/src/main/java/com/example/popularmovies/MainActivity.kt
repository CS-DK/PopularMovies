package com.example.popularmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.popularmovies.R.id
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val tabs: TabLayout = findViewById(R.id.tabs)
        val adapter = TabsPagerAdapter(supportFragmentManager)

        adapter.addFragment(MovieFragment(), "Movies")
        adapter.addFragment(TVShowFragment(), "TV Shows")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}