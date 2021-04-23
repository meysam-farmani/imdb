package com.marketkhoone.imdb.model

import android.os.Parcel
import android.os.Parcelable

data class Actors(
	val id : String?,
	val image : String?,
	val name : String?,
	val asCharacter : String?

): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(id)
		parcel.writeString(image)
		parcel.writeString(name)
		parcel.writeString(asCharacter)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Actors> {
		override fun createFromParcel(parcel: Parcel): Actors {
			return Actors(parcel)
		}

		override fun newArray(size: Int): Array<Actors?> {
			return arrayOfNulls(size)
		}
	}
}