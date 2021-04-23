package com.marketkhoone.imdb.model

import android.os.Parcel
import android.os.Parcelable

data class NewMovie(
	val items : List<NewMovieItems>?,
	val errorMessage : String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readParcelable(NewMovieItems::class.java.classLoader),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeTypedList(items)
		parcel.writeString(errorMessage)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<NewMovie> {
		override fun createFromParcel(parcel: Parcel): NewMovie {
			return NewMovie(parcel)
		}

		override fun newArray(size: Int): Array<NewMovie?> {
			return arrayOfNulls(size)
		}
	}
}
