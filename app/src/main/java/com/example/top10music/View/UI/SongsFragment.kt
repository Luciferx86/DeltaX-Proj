package com.example.top10music.View.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flobiz.ViewModel.SongViewModel
import com.example.top10music.Model.Song
import com.example.top10music.R
import com.example.top10music.View.Adapter.SongAdapter
import kotlinx.android.synthetic.main.songs_fragment.view.*


class SongsFragment : Fragment() {
    var songsRecycler: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.songs_fragment, container, false)
        songsRecycler = view.findViewById(R.id.songsRecycler) as RecyclerView
        view.addSong.setOnClickListener {
            val i: Intent = Intent(context, AddSongActivity::class.java)
            this.startActivityForResult(i, 2)
        }

        //fetching songs from API
        observeSongsData()
        return view
    }

    //function to get all songs stored in DB
    private fun observeSongsData() {
        val model: SongViewModel = ViewModelProviders.of(this).get<SongViewModel>(
            SongViewModel::class.java
        )
        model.getSongs()?.observe(this, Observer<ArrayList<Song>?> {
            Log.d("GotResponse", it.toString())
            val songs = it
            if (songs != null) {
                Log.d("ActivityResult", "got Result")
                songsRecycler?.layoutManager = LinearLayoutManager(context)
                songsRecycler?.adapter = SongAdapter(
                    songs,
                    this.activity
                )
            } else {
                Log.d("Cities", "no cities found")
            }
        })
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        // check if the request code is same as what is passed  here it is 2
        Log.d("ActivityResult", resultCode.toString())
        if (resultCode == 2) {
            //refreshing list after adding song
            observeSongsData()
        } else if (resultCode == 4) {
            Toast.makeText(context, "Failed to add song", Toast.LENGTH_SHORT).show()
        }
    }
}