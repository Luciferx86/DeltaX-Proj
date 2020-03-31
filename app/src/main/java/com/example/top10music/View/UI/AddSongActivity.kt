package com.example.top10music.View.UI

import android.os.Bundle
import android.util.Log
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.flobiz.ViewModel.ArtistViewModel
import com.example.flobiz.ViewModel.SongViewModel
import com.example.top10music.Model.SongToAdd
import com.example.top10music.R
import com.example.top10music.View.Adapter.SpinnerAdapter
import kotlinx.android.synthetic.main.activity_add_song.*
import kotlinx.android.synthetic.main.activity_add_song.addSong
import kotlinx.android.synthetic.main.add_artist_dialog_layout.*


class AddSongActivity : AppCompatActivity() {
    lateinit var adapter: SpinnerAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song)

        observeArtistData()

        addArtistBtn.setOnClickListener {
            var dialog = AddArtistDialog(this);
            dialog.show()

            dialog.dialogAddArtistBtn.setOnClickListener {
                val artist = dialog.getArtistToAdd()

                val model: ArtistViewModel = ViewModelProviders.of(this).get<ArtistViewModel>(
                    ArtistViewModel::class.java
                )
                model.addArtist(artist)?.observe(this, Observer {
                    Log.d("GotResponse", it.toString())
                    val addedArtist = it
                    if (addedArtist != null) {
                        Log.d("AddedArtist", "${addedArtist.toString()} added successfully")
                        dialog.hide()
                    } else {
                        Log.d("Cities", "no cities found")
                    }
                })
                Log.d("DialogBtn", artist.toString())
            }

        }

        addSong.setOnClickListener {
            Log.d("SelectedArtists", adapter.selectedArtists.toString());
            val songName = songName.text.toString()
            val releaseDate = releaseDate.text.toString()
            val imageURL = ""
            val artists = adapter.selectedArtists

            if (songName.length > 4 && releaseDate.length > 5 && artists.size > 0) {
                observeAddSongsData(SongToAdd(songName, releaseDate, imageURL, artists))
            } else {
                if (songName.length <= 4) {
                    Toast.makeText(this, "Name should be more than 4 chars", Toast.LENGTH_LONG)
                        .show()
                } else if (releaseDate.length <= 5) {
                    Toast.makeText(this, "Invalid Date", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "No Artist Selected", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun observeAddSongsData(song: SongToAdd) {
        val model: SongViewModel = ViewModelProviders.of(this).get<SongViewModel>(
            SongViewModel::class.java
        )
        model.addSong(song)?.observe(this, Observer {
            Log.d("GotResponse", it.toString())
            val addedSong = it
            if (addedSong != null) {
                Log.d("AddedSong", "${addedSong.toString()} added successfully")
                finish()
            } else {
                Log.d("Cities", "no cities found")
            }
        })
    }

    fun observeArtistData() {
        val model: ArtistViewModel = ViewModelProviders.of(this).get<ArtistViewModel>(
            ArtistViewModel::class.java
        )
        var artistArray: ArrayList<String> = ArrayList();
        model.getArtists()?.observe(this, Observer {
            Log.d("GotResponse", it.toString())
            val artistsRec = it
            if (artistsRec != null) {
                for (i in artistsRec) {
                    artistArray.add(i.artistName)
                }
                adapter = SpinnerAdapter(
                    this, artistArray
                )
                artistSpinner.adapter = adapter
                artistSpinner.setPrompt("Select your favorite Planet!");
            } else {
                Log.d("Cities", "no cities found")
            }
        })
    }
}
