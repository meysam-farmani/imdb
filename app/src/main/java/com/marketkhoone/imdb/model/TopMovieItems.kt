package com.marketkhoone.imdb.model

import android.os.Parcel
import android.os.Parcelable

data class TopMovieItems (
	val id : String?,
	val rank : Int = 0,
	val title : String?,
	val fullTitle : String?,
	val year : Int = 0,
	val image : String?,
	val crew : String?,
	val imDbRating : Double = 0.0,
	val imDbRatingCount : Int = 0
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readString(),
		parcel.readDouble(),
		parcel.readInt(),
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(id)
		parcel.writeInt(rank)
		parcel.writeString(title)
		parcel.writeString(fullTitle)
		parcel.writeInt(year)
		parcel.writeString(image)
		parcel.writeString(crew)
		parcel.writeDouble(imDbRating)
		parcel.writeInt(imDbRatingCount)

	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<TopMovieItems> {
		override fun createFromParcel(parcel: Parcel): TopMovieItems {
			return TopMovieItems(parcel)
		}

		override fun newArray(size: Int): Array<TopMovieItems?> {
			return arrayOfNulls(size)
		}
	}
}