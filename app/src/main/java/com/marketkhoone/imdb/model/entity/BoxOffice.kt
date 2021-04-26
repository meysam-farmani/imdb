package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class BoxOffice(
	val budget : String?,
	val openingWeekendUSA : String?,
	val grossUSA : String?,
	val cumulativeWorldwideGross : String?

): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(budget)
		parcel.writeString(openingWeekendUSA)
		parcel.writeString(grossUSA)
		parcel.writeString(cumulativeWorldwideGross)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<BoxOffice> {
		override fun createFromParcel(parcel: Parcel): BoxOffice {
			return BoxOffice(parcel)
		}

		override fun newArray(size: Int): Array<BoxOffice?> {
			return arrayOfNulls(size)
		}
	}
}