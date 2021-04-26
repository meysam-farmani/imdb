package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class NewMovieItem (
	val id : String?,
	val title : String?,
	val fullTitle : String?,
	val year : String?,
	val releaseState : String?,
	val image : String?,
	val runtimeMins : String?,
	val runtimeStr : String?,
	val plot : String?,
	val contentRating : String?,
	val imDbRating : String?,
	val imDbRatingCount : String?,
	val metacriticRating : String?,
	val genres : String?,
	val genre : List<Genre>?,
	val directors : String?,
	val directorLists : List<Director>?,
	val stars : String?,
	val star : List<Star>?
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
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(Genre::class.java.classLoader),
		parcel.readString(),
		parcel.readParcelable(Director::class.java.classLoader),
		parcel.readString(),
		parcel.readParcelable(Star::class.java.classLoader)
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(id)
		parcel.writeString(title)
		parcel.writeString(fullTitle)
		parcel.writeString(year)
		parcel.writeString(releaseState)
		parcel.writeString(image)
		parcel.writeString(runtimeMins)
		parcel.writeString(runtimeStr)
		parcel.writeString(plot)
		parcel.writeString(contentRating)
		parcel.writeString(imDbRating)
		parcel.writeString(imDbRatingCount)
		parcel.writeString(metacriticRating)
		parcel.writeString(genres)
		parcel.writeTypedList(genre)
		parcel.writeString(directors)
		parcel.writeTypedList(directorLists)
		parcel.writeString(stars)
		parcel.writeTypedList(star)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<NewMovieItem> {
		override fun createFromParcel(parcel: Parcel): NewMovieItem {
			return NewMovieItem(parcel)
		}

		override fun newArray(size: Int): Array<NewMovieItem?> {
			return arrayOfNulls(size)
		}
	}
}