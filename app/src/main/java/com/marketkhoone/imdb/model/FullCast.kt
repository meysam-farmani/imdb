package com.marketkhoone.imdb.model

import android.os.Parcel
import android.os.Parcelable

data class FullCast(
	val imDbId : String?,
	val title : String?,
	val fullTitle : String?,
	val type : String?,
	val year : Int = 0,
	val directors : Directors?,
	val writers : Writers?,
	val actors : List<Actors>?,
	val others : List<Others>?,
	val errorMessage : String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readParcelable(Directors::class.java.classLoader),
		parcel.readParcelable(Writers::class.java.classLoader),
		parcel.readParcelable(Actors::class.java.classLoader),
		parcel.readParcelable(Others::class.java.classLoader),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(imDbId)
		parcel.writeString(title)
		parcel.writeString(fullTitle)
		parcel.writeString(type)
		parcel.writeInt(year)
		parcel.writeParcelable(directors, flags)
		parcel.writeParcelable(writers, flags)
		parcel.writeTypedList(actors)
		parcel.writeTypedList(others)
		parcel.writeString(errorMessage)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<FullCast> {
		override fun createFromParcel(parcel: Parcel): FullCast {
			return FullCast(parcel)
		}

		override fun newArray(size: Int): Array<FullCast?> {
			return arrayOfNulls(size)
		}
	}
}
