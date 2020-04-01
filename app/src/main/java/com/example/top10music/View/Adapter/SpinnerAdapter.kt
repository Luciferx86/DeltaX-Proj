package com.example.top10music.View.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CompoundButton
import com.example.top10music.R
import kotlinx.android.synthetic.main.spinner_item.view.*


class SpinnerAdapter(val context: Context, var listItemsTxt: ArrayList<String>) : BaseAdapter() {

    //Adapter for spinner for all available artists when adding a new song

    val mInflater: LayoutInflater = LayoutInflater.from(context)

    var selectedArtists = ArrayList<String>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        if (convertView == null) {
            view = mInflater.inflate(R.layout.spinner_item, parent, false)
        } else {
            view = convertView
        }
        view.spinnerArtistName.text = listItemsTxt[position]
        view.spinnerCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
//                Log.d("IsChecked",isChecked.toString())
                selectedArtists.add(listItemsTxt[position])
                Log.d("SelectedArtists",selectedArtists.toString())
            }else{
                selectedArtists.remove(listItemsTxt[position])
                Log.d("SelectedArtists",selectedArtists.toString())
            }
        }

        return view
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listItemsTxt.size
    }
}