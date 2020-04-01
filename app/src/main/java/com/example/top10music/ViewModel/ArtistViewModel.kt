package com.example.flobiz.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.top10music.Model.Artist
import com.example.top10music.Model.ArtistToAdd
import com.example.top10music.Model.Song
import com.example.top10music.Model.SongToAdd
import com.example.top10music.Rest.RestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArtistViewModel : ViewModel() {
    private val BASE_URL = "https://heroku-song.herokuapp.com/"
    private var artistResponseLive: MutableLiveData<ArrayList<Artist>?>? = null
    private var addArtistResponseLive: MutableLiveData<Artist>? = null
    fun getArtists(): MutableLiveData<ArrayList<Artist>?>? {
        if (artistResponseLive == null) {
            artistResponseLive = MutableLiveData()
            loadArtists()
        }
        //finally we will return the list
        return artistResponseLive
    }

    fun loadArtists() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RestApi::class.java)
        val call: Call<ArrayList<Artist>?>? = service.getArtists()
        call?.enqueue(object : Callback<ArrayList<Artist>?> {
            override fun onResponse(
                call: Call<ArrayList<Artist>?>,
                response: Response<ArrayList<Artist>?>
            ) {
                Log.d("Response", response.code().toString())
                if (response.code() == 200) {
                    val artistResponse: ArrayList<Artist>? = response.body()
                    artistResponseLive?.setValue(artistResponse)
                } else {
                    try {
                        Log.d("Temperature", response.errorBody().string())
                    } catch (e: Exception) {
                        Log.d("Temperature", e.message)
                    }
                }
            }

            override fun onFailure(
                call: Call<ArrayList<Artist>?>,
                t: Throwable
            ) { //                weatherData.setText(t.getMessage());
                Log.d("Temperature", t.message)
            }
        })
    }

    fun addArtist(artist: ArtistToAdd): MutableLiveData<Artist>?{
        if (addArtistResponseLive == null) {
            addArtistResponseLive = MutableLiveData<Artist>()
            addArtistCall(artist)
        }
        //finally we will return the list
        return addArtistResponseLive
    }

    fun addArtistCall(artist: ArtistToAdd){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RestApi::class.java)
        val call: Call<Artist?>? = service.addArtist(artist)
        call?.enqueue(object : Callback<Artist?> {
            override fun onResponse(
                call: Call<Artist?>?,
                response: Response<Artist?>
            ) {
                Log.d("Response", response.code().toString())
                if (response.code() == 200) {
                    val artistResponse: Artist? = response.body()
                    addArtistResponseLive?.setValue(artistResponse)
                } else {
                    try {
                        Log.d("Temperature", response.errorBody().string())
                    } catch (e: Exception) {
                        Log.d("Temperature", e.message)
                    }
                }
            }

            override fun onFailure(
                call: Call<Artist?>,
                t: Throwable
            ) {
                Log.d("Temperature", t.message)
            }
        })
    }
}