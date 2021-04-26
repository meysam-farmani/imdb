package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class Other(
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

	companion object CREATOR : Parcelable.Creator<Other> {
		override fun createFromParcel(parcel: Parcel): Other {
			return Other(parcel)
		}

		override fun newArray(size: Int): Array<Other?> {
			return arrayOfNulls(size)
		}
	}
}
