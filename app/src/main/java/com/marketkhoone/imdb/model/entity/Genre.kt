package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class Genre(
	val key: String?,
	val value: String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(key)
		parcel.writeString(value)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Genre> {
		override fun createFromParcel(parcel: Parcel): Genre {
			return Genre(parcel)
		}

		override fun newArray(size: Int): Array<Genre?> {
			return arrayOfNulls(size)
		}
	}
}