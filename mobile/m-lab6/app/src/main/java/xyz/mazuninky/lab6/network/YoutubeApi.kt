package xyz.mazuninky.lab6.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {
    @GET("videos?part=contentDetails,snippet&chart=mostPopular&regionCode=IN&maxResults=50&key=AIzaSyCAQvYUC7X_iGMy9c4IHwEazpvZaRVmZkI")
    suspend fun findTrends(): PlayListData

    @GET("playlistItems?part=contentDetails,snippet&key=AIzaSyCAQvYUC7X_iGMy9c4IHwEazpvZaRVmZkI&maxResults=50")
    suspend fun getPlaylist(@Query("playlistId") playlistId: String): PlayListData
}