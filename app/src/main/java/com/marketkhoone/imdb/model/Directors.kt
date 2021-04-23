package com.marketkhoone.imdb.model

import android.os.Parcel
import android.os.Parcelable

data class Directors(
	val job : String?,
	val items : List<JobItems>?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readParcelable(JobItems::class.java.classLoader)
		) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(job)
		parcel.writeTypedList(items)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Directors> {
		override fun createFromParcel(parcel: Parcel): Directors {
			return Directors(parcel)
		}

		override fun newArray(size: Int): Array<Directors?> {
			return arrayOfNulls(size)
		}
	}
}
