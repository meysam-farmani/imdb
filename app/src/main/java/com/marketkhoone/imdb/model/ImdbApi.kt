package com.marketkhoone.imdb.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ImdbApi {

    @GET("Top250Movies/{apiKey}")
    fun getTop250Movies(
        @Path("apiKey") apiKey: String): Single<NewMovie>

    @GET("InTheaters/{apiKey}")
    fun getInTheaters(
        @Path("apiKey") apiKey: String): Single<NewMovie>

    @GET("ComingSoon/{apiKey}")
    fun getComingSoon(
        @Path("apiKey") apiKey: String): Single<NewMovie>

    @GET("FullCast/{apiKey}/{imdbId}")
    fun getFullCast(
        @Path("apiKey") apiKey: String,
        @Path("imdbId") imdbId: String?): Single<FullCast>

    @GET("YouTubeTrailer/{apiKey}/{imdbId}")
    fun getYouTubeTrailer(
        @Path("apiKey") apiKey: String,
        @Path("imdbId") imdbId: String?): Single<YouTubeTrailer>

    @GET("Trailer/{apiKey}/{imdbId}")
    fun getTrailer(
        @Path("apiKey") apiKey: String,
        @Path("imdbId") imdbId: String?): Single<ImdbTrailer>
}