package com.example.top10music.View.UI


import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.flobiz.ViewModel.ArtistViewModel
import com.example.flobiz.ViewModel.SongViewModel
import com.example.top10music.Model.ArtistToAdd
import com.example.top10music.Model.SongToAdd
import com.example.top10music.R
import kotlinx.android.synthetic.main.add_artist_dialog_layout.*


class AddArtistDialog // TODO Auto-generated constructor stub
    (c: Activity) : Dialog(c) {
    //    var d: Dialog? = null
    var activity = c

    var addArtistBtn = findViewById<Button>(R.id.dialogAddArtistBtn)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.add_artist_dialog_layout)
    }

//    fun observeAddArtistData(artist: ArtistToAdd) {
//        val model: ArtistViewModel = ViewModelProviders.of(this.context).get<ArtistViewModel>(
//            ArtistViewModel::class.java
//        )
//        model.addArtist(artist)?.observe(this, Observer {
//            Log.d("GotResponse", it.toString())
//            val addedArtist = it
//            if (addedArtist != null) {
//                Log.d("AddedSong", "${addedArtist.toString()} added successfully")
//            } else {
//                Log.d("Cities", "no cities found")
//            }
//        })
//    }

    fun getArtistToAdd(): ArtistToAdd {
        val artistName = dialogArtistName.text.toString()
        val artistDOB = dialogDOB.text.toString()
        val bio = dialogBio.text.toString()
        return ArtistToAdd(artistName, artistDOB, bio);
    }

}