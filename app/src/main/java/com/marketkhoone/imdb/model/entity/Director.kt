package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class Director(
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

	companion object CREATOR : Parcelable.Creator<Director> {
		override fun createFromParcel(parcel: Parcel): Director {
			return Director(parcel)
		}

		override fun newArray(size: Int): Array<Director?> {
			return arrayOfNulls(size)
		}
	}
}