package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class Similar (
	val id : String?,
	val title : String?,
	val fullTitle : String?,
	val year : String?,
	val image : String?,
	val plot : String?,
	val directors : String?,
	val stars : String?,
	val genres : String?,
	val imDbRating : String?,
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
		parcel.writeString(title)
		parcel.writeString(fullTitle)
		parcel.writeString(year)
		parcel.writeString(image)
		parcel.writeString(plot)
		parcel.writeString(directors)
		parcel.writeString(stars)
		parcel.writeString(genres)
		parcel.writeString(imDbRating)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Similar> {
		override fun createFromParcel(parcel: Parcel): Similar {
			return Similar(parcel)
		}

		override fun newArray(size: Int): Array<Similar?> {
			return arrayOfNulls(size)
		}
	}
}