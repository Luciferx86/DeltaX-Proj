package com.example.top10music.View.Adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.flobiz.ViewModel.SongViewModel
import com.example.top10music.Model.Song
import com.example.top10music.Model.SongRating
import com.example.top10music.R
import com.example.top10music.View.UI.RateSongDialog
import kotlinx.android.synthetic.main.rate_song_dialog_layout.*


class SongAdapter(
    songs: List<Song>,
    context: FragmentActivity?
) :
    RecyclerView.Adapter<SongAdapter.SongViewHolder>() {
    private val songs: List<Song> = songs
    private val context: Activity? = context

    //A view holder inner class where we get reference to the views in the layout using their ID
    class SongViewHolder(v: View) : ViewHolder(v) {
        var songName: TextView = v.findViewById(R.id.songName)
        var releaseDate : TextView =  v.findViewById(R.id.releaseDate)
        var coverImage :  ImageView =  v.findViewById(R.id.coverImage)
        var rateBtn : LinearLayout = v.findViewById(R.id.rateSong)
        var avgRating : TextView = v.findViewById(R.id.avgRating)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SongViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_song_item, parent, false)
        return SongViewHolder(
            view
        )
    }

    override fun onBindViewHolder(
        holder: SongViewHolder,
        position: Int
    ) {
        val mContext: Context? = context
        holder.songName.text = songs[position].songName
        holder.releaseDate.text = songs[position].releaseDate
        holder.avgRating.text = songs[position].avgRating.toString()
        holder.coverImage.setImageResource(R.drawable.ic_launcher_background)
        holder. rateBtn.setOnClickListener {
            var dialog = RateSongDialog(context, songs[position].songName)
            dialog.show()
            dialog.rateDialogSongNameHeading.text = songs[position].songName
            dialog.dialogRateBtn.setOnClickListener{
                dialog.progressLayout.visibility = View.VISIBLE
                dialog.rateDialogSongNameHeading.visibility = View.GONE
                dialog.dialogRateBtn.visibility = View.GONE
                dialog.dialogRatingBar.visibility =View.GONE
                var rating = SongRating(songs[position].id.toString(), dialog.getRating())
                Log.d("DialogRating",dialog.getRating().toString())
                val model: SongViewModel = ViewModelProviders.of(context as FragmentActivity).get<SongViewModel>(
                    SongViewModel::class.java
                )
                model.rateSong(rating)?.observe(context , Observer {
                    Log.d("GotResponse", it.toString())
                    val addedRating = it
                    if (addedRating != null) {
                        Log.d("RatingAdded", "${addedRating.toString()} added successfully")
                        dialog.hide()
                        Toast.makeText(context,"Your rating has been submitted", Toast.LENGTH_LONG).show()
                    } else {
                        dialog.progressLayout.visibility = View.GONE
                        dialog.rateDialogSongNameHeading.visibility = View.VISIBLE
                        dialog.dialogRateBtn.visibility = View.VISIBLE
                        dialog.dialogRatingBar.visibility =View.VISIBLE
                        Log.d("Cities", "no cities found")
                        Toast.makeText(context,"Error submitting your rating", Toast.LENGTH_LONG).show()
                    }
                })
            }

        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }

}