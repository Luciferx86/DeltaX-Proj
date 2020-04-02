package com.example.top10music.View.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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
import kotlinx.android.synthetic.main.add_artist_dialog_layout.*
import kotlinx.android.synthetic.main.single_artist_item.*


class AddSongActivity : AppCompatActivity() {
    lateinit var adapter: SpinnerAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song)

        //fetching all stored artists from API
        getAllArtists()

        addArtistBtn.setOnClickListener {

            //showing new dialog for adding new Artist
            val dialog = AddArtistDialog(this);
            dialog.show()

            dialog.dialogAddArtistBtn.setOnClickListener {
                dialog.dialogNameHeading.visibility = View.GONE
                dialog.dialogArtistName.visibility = View.GONE
                dialog.dialogDobHeading.visibility = View.GONE
                dialog.dialogDOB.visibility = View.GONE
                dialog.dialogBioHeading.visibility = View.GONE
                dialog.dialogBio.visibility = View.GONE
                dialog.dialogAddArtistBtn.visibility = View.GONE
                dialog.dialogProgressLayout.visibility = View.VISIBLE
                val artist = dialog.getArtistToAdd()
                val model: ArtistViewModel = ViewModelProviders.of(this).get<ArtistViewModel>(
                    ArtistViewModel::class.java
                )
                model.addArtist(artist)?.observe(this, Observer {
                    Log.d("GotResponse", it.toString())
                    val addedArtist = it
                    if (addedArtist != null) {

                        //Artist added successfully
                        Log.d("AddedArtist", "${addedArtist.toString()} added successfully")
                        Toast.makeText(this,"Artist Added Successfully",Toast.LENGTH_SHORT).show()
                        this.getAllArtists()
                        dialog.hide()
                    } else {
                        dialog.dialogNameHeading.visibility = View.VISIBLE
                        dialog.dialogArtistName.visibility = View.VISIBLE
                        dialog.dialogDobHeading.visibility = View.VISIBLE
                        dialog.dialogDOB.visibility = View.VISIBLE
                        dialog.dialogBioHeading.visibility = View.VISIBLE
                        dialog.dialogBio.visibility = View.VISIBLE
                        dialog.dialogAddArtistBtn.visibility = View.VISIBLE
                        dialog.dialogProgressLayout.visibility = View.GONE
                        Log.d("Cities", "no cities found")
                    }
                })
            }

        }

        addSong.setOnClickListener {
            Log.d("SelectedArtists", adapter.selectedArtists.toString());
            val songName = songName.text.toString()
            val releaseDate = releaseDate.text.toString()
            val imageURL = ""
            val artists = adapter.selectedArtists

            if (songName.length > 4 && releaseDate.length > 5 && artists.size > 0) {
                addSongToApi(SongToAdd(songName, releaseDate, imageURL, artists))
            } else {
                when {
                    songName.length <= 4 -> {
                        Toast.makeText(this, "Name should be more than 4 chars", Toast.LENGTH_LONG)
                            .show()
                    }
                    releaseDate.length <= 5 -> {
                        Toast.makeText(this, "Invalid Date", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        Toast.makeText(this, "No Artist Selected", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    //function to add new song to API
    private fun addSongToApi(song: SongToAdd) {
        val model: SongViewModel = ViewModelProviders.of(this).get<SongViewModel>(
            SongViewModel::class.java
        )
        model.addSong(song)?.observe(this, Observer {
            Log.d("GotResponse", it.toString())
            val addedSong = it
            if (addedSong != null) {
                Log.d("AddedSong", "${addedSong.toString()} added successfully")
                val intent = Intent()
                intent.putExtra("MESSAGE", "This is a message")
                setResult(2,intent)
                finish()
            } else {
                Log.d("Cities", "no cities found")
            }
        })
    }

    //function to get all existing Artists from API
    private fun getAllArtists() {
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


    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("MESSAGE", "This is a message")
        setResult(4, intent)
        super.onBackPressed()
    }
}
