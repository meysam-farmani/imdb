package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class TvSeriesInfo(
	val yearEnd : String?,
	val creators : String?,
	val creatorList : List<CreatorList>?,
	val seasons : List<String>?

): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(CreatorList::class.java.classLoader),
		parcel.readParcelable(String::class.java.classLoader)
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(yearEnd)
		parcel.writeString(creators)
		parcel.writeTypedList(creatorList)
		parcel.writeStringList(seasons)

	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<TvSeriesInfo> {
		override fun createFromParcel(parcel: Parcel): TvSeriesInfo {
			return TvSeriesInfo(parcel)
		}

		override fun newArray(size: Int): Array<TvSeriesInfo?> {
			return arrayOfNulls(size)
		}
	}
}