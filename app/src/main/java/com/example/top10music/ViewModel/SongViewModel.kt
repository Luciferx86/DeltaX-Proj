package com.example.flobiz.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.top10music.Model.Song
import com.example.top10music.Model.SongRating
import com.example.top10music.Model.SongToAdd
import com.example.top10music.Rest.RestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SongViewModel : ViewModel() {
    private val BASE_URL = "https://heroku-song.herokuapp.com/"
    private var songResponseLive: MutableLiveData<ArrayList<Song>?>? = null
    private var addSongResponseLive: MutableLiveData<Song>? = null
    private var rateSongResponseLive: MutableLiveData<SongRating>? = null


    fun addSong(song: SongToAdd): MutableLiveData<Song>? {
        if (addSongResponseLive == null) {
            addSongResponseLive = MutableLiveData<Song>()
            addSongCall(song)
        }
        //finally we will return the list
        return addSongResponseLive
    }

    fun addSongCall(song: SongToAdd) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RestApi::class.java)
        val call: Call<Song?>? = service.addSong(song)
        call?.enqueue(object : Callback<Song?> {
            override fun onResponse(
                call: Call<Song?>?,
                response: Response<Song?>
            ) {
                Log.d("Response", response.code().toString())
                if (response.code() == 200) {
                    val songResponse: Song? = response.body()
                    addSongResponseLive?.setValue(songResponse)
                } else {
                    try {
                        Log.d("Temperature", response.errorBody().string())
                    } catch (e: Exception) {
                        Log.d("Temperature", e.message)
                    }
                }
            }

            override fun onFailure(
                call: Call<Song?>,
                t: Throwable
            ) {
                Log.d("Temperature", t.message)
            }
        })
    }

    fun getSongs(): MutableLiveData<ArrayList<Song>?>? {
        songResponseLive = MutableLiveData()
        loadSongs()
        //finally we will return the list
        return songResponseLive
    }

    fun loadSongs() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RestApi::class.java)
        val call: Call<ArrayList<Song>?>? = service.getSongs()
        call?.enqueue(object : Callback<ArrayList<Song>?> {
            override fun onResponse(
                call: Call<ArrayList<Song>?>,
                response: Response<ArrayList<Song>?>
            ) {
                Log.d("Response", response.code().toString())
                if (response.code() == 200) {
                    val songResponse: ArrayList<Song>? = response.body()
                    songResponseLive?.setValue(songResponse)
                } else {
                    try {
                        Log.d("Temperature", response.errorBody().string())
                    } catch (e: Exception) {
                        Log.d("Temperature", e.message)
                    }
                }
            }

            override fun onFailure(
                call: Call<ArrayList<Song>?>,
                t: Throwable
            ) {
                Log.d("Temperature", t.message)
            }
        })
    }

    fun rateSong(rating: SongRating): MutableLiveData<SongRating>? {
        rateSongResponseLive = MutableLiveData<SongRating>()
        rateSongCall(rating)
        //finally we will return the list
        return rateSongResponseLive
    }

    fun rateSongCall(rating: SongRating) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RestApi::class.java)
        val call: Call<SongRating?>? = service.rateSong(rating)
        call?.enqueue(object : Callback<SongRating?> {
            override fun onResponse(
                call: Call<SongRating?>?,
                response: Response<SongRating?>
            ) {
                Log.d("Response", response.code().toString())
                if (response.code() == 200) {
                    val ratingResponse: SongRating? = response.body()
                    rateSongResponseLive?.setValue(ratingResponse)
                } else {
                    try {
                        Log.d("Temperature", response.errorBody().string())
                    } catch (e: Exception) {
                        Log.d("Temperature", e.message)
                    }
                }
            }

            override fun onFailure(
                call: Call<SongRating?>,
                t: Throwable
            ) {
                Log.d("Temperature", t.message)
            }
        })
    }
}