package com.marketkhoone.imdb.model

import android.os.Parcel
import android.os.Parcelable

data class NewMovieItems (
	val id : String?,
	val title : String?,
	val fullTitle : String?,
	val year : Int = 0,
	val releaseState : String?,
	val image : String?,
	val runtimeMins : Int = 0,
	val runtimeStr : String?,
	val plot : String?,
	val contentRating : String?,
	val imDbRating : String?,
	val imDbRatingCount : String?,
	val metacriticRating : String?,
	val genres : String?,
	val genreList : List<GenreList>?,
	val directors : String?,
	val directorList : List<DirectorList>?,
	val stars : String?,
	val starList : List<StarList>?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(GenreList::class.java.classLoader),
		parcel.readString(),
		parcel.readParcelable(DirectorList::class.java.classLoader),
		parcel.readString(),
		parcel.readParcelable(StarList::class.java.classLoader)
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(id)
		parcel.writeString(title)
		parcel.writeString(fullTitle)
		parcel.writeInt(year)
		parcel.writeString(releaseState)
		parcel.writeString(image)
		parcel.writeInt(runtimeMins)
		parcel.writeString(runtimeStr)
		parcel.writeString(plot)
		parcel.writeString(contentRating)
		parcel.writeString(imDbRating)
		parcel.writeString(imDbRatingCount)
		parcel.writeString(metacriticRating)
		parcel.writeString(genres)
		parcel.writeTypedList(genreList)
		parcel.writeString(directors)
		parcel.writeTypedList(directorList)
		parcel.writeString(stars)
		parcel.writeTypedList(starList)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<NewMovieItems> {
		override fun createFromParcel(parcel: Parcel): NewMovieItems {
			return NewMovieItems(parcel)
		}

		override fun newArray(size: Int): Array<NewMovieItems?> {
			return arrayOfNulls(size)
		}
	}
}