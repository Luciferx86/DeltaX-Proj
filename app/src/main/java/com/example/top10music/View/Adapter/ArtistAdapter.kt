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
//        holder.itemView.setOnClickListener(object : OnClickListener() {
//            fun onClick(view: View?) { //use position value  to get clicked data from list
//                Log.d("Clicked", cities[position].getName())
//                val i = Intent(context, CityDetailsActivity::class.java)
//                i.putExtra("city", cities[position].getName())
//                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                mContext.startActivity(i)
//            }
//        })
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    fun getAllSongs(allSongs: ArrayList<String>) : String {
        var x = ""
        for(i in  allSongs){
            x+= "$i , "
        }
        return x.dropLast(2 )
    }

}