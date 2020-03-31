package com.example.top10music.View.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.flobiz.ViewModel.ArtistViewModel
import com.example.flobiz.ViewModel.SongViewModel
import com.example.top10music.Model.Artist
import com.example.top10music.Model.Song
import com.example.top10music.R
import com.example.top10music.View.Adapter.ArtistAdapter
import com.example.top10music.View.Adapter.SongAdapter
import com.example.top10music.View.Adapter.TabsPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        addSong.setOnClickListener {
//            var i: Intent = Intent(this, AddSongActivity::class.java)
//            startActivity(i);
//        }

        var viewPager: ViewPager = findViewById(R.id.pager);
        var viewPagerAdapter = TabsPagerAdapter(supportFragmentManager);
        viewPager.adapter = viewPagerAdapter;
        var tabLayout :TabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

//        observeSongsData()
//        observeArtistData()


    }
//
//    fun observeArtistData(){
//        val model: ArtistViewModel = ViewModelProviders.of(this).get<ArtistViewModel>(
//            ArtistViewModel::class.java
//        )
//        model.getArtists()?.observe(this, Observer<ArrayList<Artist>?> {
//            Log.d("GotResponse", it.toString())
//            val artists = it
//            if (artists != null) {
//                artistRecycler.layoutManager = LinearLayoutManager(this)
//                artistRecycler.adapter = ArtistAdapter(
//                    artists,
//                    applicationContext
//                )
//            } else {
//                Log.d("Cities", "no cities found")
//            }
//        })
//    }
}
