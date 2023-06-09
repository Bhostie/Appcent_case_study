package com.example.appcent_case_study.my_classes

import retrofit2.http.*
import retrofit2.Call

interface ApiService {

    @GET("genre/")
    fun getGenres(): Call<GenreList>?

    @GET("artists/")
    fun getArtists(): Call<ArtistList>?

    @GET("{artistId}/")
    fun getArtistDetails(@Path("artistId") artistId: String): Call<ArtistItem>?

    @GET("albums/")
    fun getArtistAlbums(): Call<AlbumList>

    @GET("tracks/")
    fun getTracks(): Call<TrackList>

    @GET("search?q={keyword}&limit=50/")
    fun getSearchResults2(@Query("keyword") keyword: String): Call<SearchList>?

    @GET("search")
    fun getSearchResults(@Query("q") keyword: String, @Query("limit") limit: Int = 50): Call<SearchList>?



}