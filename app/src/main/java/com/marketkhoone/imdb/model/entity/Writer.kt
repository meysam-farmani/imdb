package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class Writer(
	val job : String?,
	val items : List<JobItem>?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readParcelable(JobItem::class.java.classLoader),
		) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(job)
		parcel.writeTypedList(items)

	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Writer> {
		override fun createFromParcel(parcel: Parcel): Writer {
			return Writer(parcel)
		}

		override fun newArray(size: Int): Array<Writer?> {
			return arrayOfNulls(size)
		}
	}
}
