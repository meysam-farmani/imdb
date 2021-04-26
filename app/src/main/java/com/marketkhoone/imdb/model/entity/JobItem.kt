package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class JobItem(
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

	companion object CREATOR : Parcelable.Creator<JobItem> {
		override fun createFromParcel(parcel: Parcel): JobItem {
			return JobItem(parcel)
		}

		override fun newArray(size: Int): Array<JobItem?> {
			return arrayOfNulls(size)
		}
	}
}