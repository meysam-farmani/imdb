package com.marketkhoone.imdb.model

import android.os.Parcel
import android.os.Parcelable

data class Others(
	val job : String?,
	val items : List<JobItems>?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readParcelable(JobItems::class.java.classLoader),
		) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(job)
		parcel.writeTypedList(items)

	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Others> {
		override fun createFromParcel(parcel: Parcel): Others {
			return Others(parcel)
		}

		override fun newArray(size: Int): Array<Others?> {
			return arrayOfNulls(size)
		}
	}
}
