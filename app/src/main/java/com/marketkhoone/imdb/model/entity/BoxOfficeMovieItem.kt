package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class BoxOfficeMovieItem (
	val id : String?,
	val rank : String?,
	val title : String?,
	val image : String?,
	val weekend : String?,
	val gross : String?,
	val weeks : String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(id)
		parcel.writeString(rank)
		parcel.writeString(title)
		parcel.writeString(image)
		parcel.writeString(weekend)
		parcel.writeString(gross)
		parcel.writeString(weeks)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<BoxOfficeMovieItem> {
		override fun createFromParcel(parcel: Parcel): BoxOfficeMovieItem {
			return BoxOfficeMovieItem(parcel)
		}

		override fun newArray(size: Int): Array<BoxOfficeMovieItem?> {
			return arrayOfNulls(size)
		}
	}
}