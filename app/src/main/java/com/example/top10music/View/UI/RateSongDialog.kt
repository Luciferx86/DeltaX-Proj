package com.example.top10music.View.UI

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import com.example.top10music.Model.ArtistToAdd
import com.example.top10music.R
import kotlinx.android.synthetic.main.add_artist_dialog_layout.*
import kotlinx.android.synthetic.main.rate_song_dialog_layout.*
import kotlinx.android.synthetic.main.rate_song_dialog_layout.view.*
import kotlinx.android.synthetic.main.single_song_item.*

class RateSongDialog // TODO Auto-generated constructor stub
    (c: Activity?, songName: String) : Dialog(c) {
    //    var d: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.rate_song_dialog_layout)

    }


    fun getRating(): Float {
        val rating = dialogRatingBar.rating
        return rating;
    }

}