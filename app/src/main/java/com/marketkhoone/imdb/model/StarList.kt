package com.marketkhoone.imdb.model

import android.os.Parcel
import android.os.Parcelable

data class StarList(
	val id: String?,
	val name: String?,

	): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(id)
		parcel.writeString(name)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<StarList> {
		override fun createFromParcel(parcel: Parcel): StarList {
			return StarList(parcel)
		}

		override fun newArray(size: Int): Array<StarList?> {
			return arrayOfNulls(size)
		}
	}
}