package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class Country(
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

	companion object CREATOR : Parcelable.Creator<Country> {
		override fun createFromParcel(parcel: Parcel): Country {
			return Country(parcel)
		}

		override fun newArray(size: Int): Array<Country?> {
			return arrayOfNulls(size)
		}
	}
}