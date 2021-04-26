package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class Actor(
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

	companion object CREATOR : Parcelable.Creator<Actor> {
		override fun createFromParcel(parcel: Parcel): Actor {
			return Actor(parcel)
		}

		override fun newArray(size: Int): Array<Actor?> {
			return arrayOfNulls(size)
		}
	}
}