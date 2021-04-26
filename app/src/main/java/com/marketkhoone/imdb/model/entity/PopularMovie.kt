package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class PopularMovie(
    val items : List<PopularMovieItem>?,
    val errorMessage : String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readParcelable(PopularMovieItem::class.java.classLoader),
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

	companion object CREATOR : Parcelable.Creator<PopularMovie> {
		override fun createFromParcel(parcel: Parcel): PopularMovie {
			return PopularMovie(parcel)
		}

		override fun newArray(size: Int): Array<PopularMovie?> {
			return arrayOfNulls(size)
		}
	}
}
