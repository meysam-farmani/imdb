package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class MovieTitle(
	val id: String?,
	val title: String?,
	val originalTitle: String?,
	val fullTitle: String?,
	val type: String?,
	val year: String?,
	val image: String?,
	val releaseDate: String?,
	val runtimeMins: String?,
	val runtimeStr: String?,
	val plot: String?,
	val plotLocal: String?,
	val plotLocalIsRtl: String?,
	val awards: String?,
	val directors: String?,
	val directorList: List<Director>?,
	val writers: String?,
	val writerList: List<Writer>?,
	val stars: String?,
	val starList: List<Star>?,
	val actorList: List<Actor>?,
	val fullCast: String?,
	val genres: String?,
	val genreList: List<Genre>?,
	val companies: String?,
	val companyList: List<Company>?,
	val countries: String?,
	val countryList: List<Country>?,
	val languages: String?,
	val languageList: List<Language>?,
	val contentRating: String?,
	val imDbRating: String?,
	val imDbRatingVotes: String?,
	val metacriticRating: String?,
	val ratings: String?,
	val wikipedia: String?,
	val posters: String?,
	val images: String?,
	val trailer: Trailer?,
	val boxOffice: BoxOffice?,
	val tagline: String?,
	val keywordswords: String?,
	val keywordListwordList: List<String>?,
	val similars: List<Similar>?,
	val tvSeriesInfo: TvSeriesInfo?,
	val tvEpisodeInfo: String?,
	val errorMessage: String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString() ,
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(Director::class.java.classLoader),
		parcel.readString(),
		parcel.readParcelable(Writer::class.java.classLoader),
		parcel.readString(),
		parcel.readParcelable(Star::class.java.classLoader),
		parcel.readParcelable(Actor::class.java.classLoader),
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(Genre::class.java.classLoader),
		parcel.readString(),
		parcel.readParcelable(Company::class.java.classLoader),
		parcel.readString(),
		parcel.readParcelable(Country::class.java.classLoader),
		parcel.readString(),
		parcel.readParcelable(Language::class.java.classLoader),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(Trailer::class.java.classLoader),
		parcel.readParcelable(BoxOffice::class.java.classLoader),
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(String::class.java.classLoader),
		parcel.readParcelable(Similar::class.java.classLoader),
		parcel.readParcelable(TvSeriesInfo::class.java.classLoader),
		parcel.readString(),
		parcel.readString(),
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(id)
		parcel.writeString(title)
		parcel.writeString(originalTitle)
		parcel.writeString(fullTitle)
		parcel.writeString(type)
		parcel.writeString(year)
		parcel.writeString(image)
		parcel.writeString(releaseDate)
		parcel.writeString(runtimeMins)
		parcel.writeString(runtimeStr)
		parcel.writeString(plot)
		parcel.writeString(plotLocal)
		parcel.writeString(plotLocalIsRtl)
		parcel.writeString(awards)
		parcel.writeString(directors)
		parcel.writeTypedList(directorList)
		parcel.writeString(writers)
		parcel.writeTypedList(writerList)
		parcel.writeString(stars)
		parcel.writeTypedList(starList)
		parcel.writeTypedList(actorList)
		parcel.writeString(fullCast)
		parcel.writeString(genres)
		parcel.writeTypedList(genreList)
		parcel.writeString(companies)
		parcel.writeTypedList(companyList)
		parcel.writeString(countries)
		parcel.writeTypedList(countryList)
		parcel.writeString(languages)
		parcel.writeTypedList(languageList)
		parcel.writeString(contentRating)
		parcel.writeString(imDbRating)
		parcel.writeString(imDbRatingVotes)
		parcel.writeString(metacriticRating)
		parcel.writeString(ratings)
		parcel.writeString(wikipedia)
		parcel.writeString(posters)
		parcel.writeString(images)
		parcel.writeParcelable(trailer, flags)
		parcel.writeParcelable(boxOffice, flags)
		parcel.writeString(tagline)
		parcel.writeString(keywordswords)
		parcel.writeStringList(keywordListwordList)
		parcel.writeTypedList(similars)
		parcel.writeParcelable(tvSeriesInfo, flags)
		parcel.writeString(tvEpisodeInfo)
		parcel.writeString(errorMessage)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<MovieTitle> {
		override fun createFromParcel(parcel: Parcel): MovieTitle {
			return MovieTitle(parcel)
		}

		override fun newArray(size: Int): Array<MovieTitle?> {
			return arrayOfNulls(size)
		}
	}
}