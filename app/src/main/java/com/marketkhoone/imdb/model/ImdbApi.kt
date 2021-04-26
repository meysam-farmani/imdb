package com.marketkhoone.imdb.model

import com.marketkhoone.imdb.model.entity.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ImdbApi {

    @GET("InTheaters/{apiKey}")
    fun getInTheaters(
        @Path("apiKey") apiKey: String): Single<NewMovie>

    @GET("ComingSoon/{apiKey}")
    fun getComingSoon(
        @Path("apiKey") apiKey: String): Single<NewMovie>

    @GET("YouTubeTrailer/{apiKey}/{imdbId}")
    fun getYouTubeTrailer(
        @Path("apiKey") apiKey: String,
        @Path("imdbId") imdbId: String?): Single<YouTubeTrailer>

    @GET("Title/{apiKey}/{imdbId}/Trailer")
    fun getMovieTitle(
        @Path("apiKey") apiKey: String,
        @Path("imdbId") imdbId: String?): Single<MovieTitle>

    @GET("Top250Movies/{apiKey}")
    fun getTop250Movies(
        @Path("apiKey") apiKey: String): Single<TopMovie>

    @GET("Top250TVs/{apiKey}")
    fun getTop250TVs(
        @Path("apiKey") apiKey: String): Single<TopMovie>

    @GET("MostPopularMovies/{apiKey}")
    fun getMostPopularMovies(
        @Path("apiKey") apiKey: String): Single<PopularMovie>

    @GET("MostPopularTVs/{apiKey}")
    fun getMostPopularTVs(
        @Path("apiKey") apiKey: String): Single<PopularMovie>

    @GET("BoxOffice/{apiKey}")
    fun getBoxOffice(
        @Path("apiKey") apiKey: String): Single<BoxOfficeMovie>

    @GET("SearchTitle/{apiKey}/{expression}")
    fun getSearchTitle(
        @Path("apiKey") apiKey: String,
        @Path("expression") expression: String?): Single<SearchTitle>
}