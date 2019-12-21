package xyz.mazuninky.lab6.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {
    @GET("videos?part=contentDetails,snippet&chart=mostPopular&regionCode=IN&maxResults=25&key=AIzaSyCAQvYUC7X_iGMy9c4IHwEazpvZaRVmZkI")
    suspend fun findTrends(): PlayListData
}