package com.example.top10music.View.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.top10music.R
import com.example.top10music.View.Adapter.TabsPagerAdapter
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewPager: ViewPager = findViewById(R.id.pager);
        var viewPagerAdapter = TabsPagerAdapter(supportFragmentManager);
        viewPager.adapter = viewPagerAdapter;
        var tabLayout :TabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }
}
