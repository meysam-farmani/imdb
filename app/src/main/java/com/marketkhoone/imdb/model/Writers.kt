package com.marketkhoone.imdb.model

import android.os.Parcel
import android.os.Parcelable

data class Writers(
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

	companion object CREATOR : Parcelable.Creator<Writers> {
		override fun createFromParcel(parcel: Parcel): Writers {
			return Writers(parcel)
		}

		override fun newArray(size: Int): Array<Writers?> {
			return arrayOfNulls(size)
		}
	}
}
