package com.example.top10music.View.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flobiz.ViewModel.ArtistViewModel
import com.example.top10music.Model.Artist
import com.example.top10music.R
import com.example.top10music.View.Adapter.ArtistAdapter

class ArtistsFragment : Fragment() {
    var artistRecycler: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.artists_fragment, container, false)
        artistRecycler = view.findViewById(R.id.artistRecycler) as RecyclerView

        //fetch artists from API
        observeArtistData()
        return view
    }

    //function to get all artists from API
    private fun observeArtistData() {
        val model: ArtistViewModel = ViewModelProviders.of(this).get<ArtistViewModel>(
            ArtistViewModel::class.java
        )
        model.getArtists()?.observe(this, Observer<ArrayList<Artist>?> {
            Log.d("GotResponse", it.toString())
            val artists = it
            if (artists != null) {
                artistRecycler?.layoutManager = LinearLayoutManager(context)
                artistRecycler?.adapter = ArtistAdapter(
                    artists,
                    this.activity
                )
            } else {
                Log.d("Cities", "no cities found")
            }
        })
    }
}