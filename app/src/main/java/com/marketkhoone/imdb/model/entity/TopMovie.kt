package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class TopMovie(
    val items : List<TopMovieItem>?,
    val errorMessage : String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readParcelable(TopMovieItem::class.java.classLoader),
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

	companion object CREATOR : Parcelable.Creator<TopMovie> {
		override fun createFromParcel(parcel: Parcel): TopMovie {
			return TopMovie(parcel)
		}

		override fun newArray(size: Int): Array<TopMovie?> {
			return arrayOfNulls(size)
		}
	}
}
