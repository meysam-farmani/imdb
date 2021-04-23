package com.marketkhoone.imdb.model

import android.os.Parcel
import android.os.Parcelable

data class DirectorList(
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

	companion object CREATOR : Parcelable.Creator<DirectorList> {
		override fun createFromParcel(parcel: Parcel): DirectorList {
			return DirectorList(parcel)
		}

		override fun newArray(size: Int): Array<DirectorList?> {
			return arrayOfNulls(size)
		}
	}
}