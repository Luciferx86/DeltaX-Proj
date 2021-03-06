package com.example.top10music.View.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.top10music.View.UI.ArtistsFragment
import com.example.top10music.View.UI.SongsFragment


class TabsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    //Adapter for ViewPager on MainActivity
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment
        if (position == 0) {
            fragment = SongsFragment()
        } else {
            fragment = ArtistsFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "Songs"
        } else {
            title = "Artists"
        }
        return title
    }
}