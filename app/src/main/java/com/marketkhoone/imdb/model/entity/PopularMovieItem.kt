package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class PopularMovieItem (
	val id : String?,
	val rank : String?,
	val rankUpDown : String?,
	val title : String?,
	val fullTitle : String?,
	val year : String?,
	val image : String?,
	val crew : String?,
	val imDbRating : String?,
	val imDbRatingCount : String?
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
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(id)
		parcel.writeString(rank)
		parcel.writeString(rankUpDown)
		parcel.writeString(title)
		parcel.writeString(fullTitle)
		parcel.writeString(year)
		parcel.writeString(image)
		parcel.writeString(crew)
		parcel.writeString(imDbRating)
		parcel.writeString(imDbRatingCount)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<PopularMovieItem> {
		override fun createFromParcel(parcel: Parcel): PopularMovieItem {
			return PopularMovieItem(parcel)
		}

		override fun newArray(size: Int): Array<PopularMovieItem?> {
			return arrayOfNulls(size)
		}
	}
}