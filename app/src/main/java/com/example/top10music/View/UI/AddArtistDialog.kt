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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.add_artist_dialog_layout)
    }

    //function to get Artist Object
    fun getArtistToAdd(): ArtistToAdd {
        val artistName = dialogArtistName.text.toString()
        val artistDOB = dialogDOB.text.toString()
        val bio = dialogBio.text.toString()
        return ArtistToAdd(artistName, artistDOB, bio);
    }

}