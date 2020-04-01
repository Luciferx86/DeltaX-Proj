package com.example.top10music.View.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.top10music.Model.Artist
import com.example.top10music.R


class ArtistAdapter(
    artists: List<Artist>,
    context: FragmentActivity?
) :
    RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    //Adapter for Artists RecyclerView

    private val artists: List<Artist> = artists
    private val context: Context? = context

    //A view holder inner class where we get reference to the views in the layout using their ID
    class ArtistViewHolder(v: View) : ViewHolder(v) {
        var artistName: TextView = v.findViewById(R.id.artistName)
        var artistDOB : TextView =  v.findViewById(R.id.artistDOB)
        var allSongs :  TextView =  v.findViewById(R.id.allSongs)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtistViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_artist_item, parent, false)
        return ArtistViewHolder(
            view
        )
    }

    override fun onBindViewHolder(
        holder: ArtistViewHolder,
        position: Int
    ) {
        val mContext: Context? = context
        holder.artistName.text = artists[position].artistName
        holder.artistDOB.text = artists[position].artistDOB
        holder.allSongs.text = getAllSongs(artists[position].sungSongs)
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    //function to get all songs as String
    fun getAllSongs(allSongs: ArrayList<String>) : String {
        var x = ""
        for(i in  allSongs){
            x+= "$i , "
        }
        return x.dropLast(2 )
    }

}