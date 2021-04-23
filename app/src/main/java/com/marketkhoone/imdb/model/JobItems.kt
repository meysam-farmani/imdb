package com.marketkhoone.imdb.model

import android.os.Parcel
import android.os.Parcelable

data class JobItems(
	val id : String?,
	val name : String?,
	val description : String?

): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(id)
		parcel.writeString(name)
		parcel.writeString(description)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<JobItems> {
		override fun createFromParcel(parcel: Parcel): JobItems {
			return JobItems(parcel)
		}

		override fun newArray(size: Int): Array<JobItems?> {
			return arrayOfNulls(size)
		}
	}
}