package com.example.top10music.Rest

import com.example.top10music.Model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST





interface RestApi {
    @GET("api/songs")
    fun getSongs(): Call<ArrayList<Song>?>?

    @GET("api/artists")
    fun getArtists(): Call<ArrayList<Artist>?>?

    @POST("/api/songs")
    fun addSong(@Body song: SongToAdd?): Call<Song?>?

    @POST("/api/artists")
    fun addArtist(@Body artist: ArtistToAdd?): Call<Artist?>?

    @POST("/api/songs/rate")
    fun rateSong(@Body rating: SongRating?): Call<SongRating?>?
}