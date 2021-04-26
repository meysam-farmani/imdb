package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class YouTubeTrailer(
	val imDbId : String?,
	val title : String?,
	val fullTitle : String?,
	val type : String?,
	val year : String?,
	val videoId : String?,
	val videoUrl : String?,
	val errorMessage : String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
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
		parcel.writeString(imDbId)
		parcel.writeString(title)
		parcel.writeString(fullTitle)
		parcel.writeString(type)
		parcel.writeString(year)
		parcel.writeString(videoId)
		parcel.writeString(videoUrl)
		parcel.writeString(errorMessage)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<YouTubeTrailer> {
		override fun createFromParcel(parcel: Parcel): YouTubeTrailer {
			return YouTubeTrailer(parcel)
		}

		override fun newArray(size: Int): Array<YouTubeTrailer?> {
			return arrayOfNulls(size)
		}
	}
}
