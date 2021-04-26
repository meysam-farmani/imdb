package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class BoxOfficeMovie(
    val items : List<BoxOfficeMovieItem>?,
    val errorMessage : String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readParcelable(BoxOfficeMovieItem::class.java.classLoader),
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

	companion object CREATOR : Parcelable.Creator<BoxOfficeMovie> {
		override fun createFromParcel(parcel: Parcel): BoxOfficeMovie {
			return BoxOfficeMovie(parcel)
		}

		override fun newArray(size: Int): Array<BoxOfficeMovie?> {
			return arrayOfNulls(size)
		}
	}
}
