package com.marketkhoone.imdb.model

import android.os.Parcel
import android.os.Parcelable

data class ImdbTrailer(
	val imDbId : String?,
	val title : String?,
	val fullTitle : String?,
	val type : String?,
	val year : Int = 0,
	val videoId : String?,
	val videoTitle : String?,
	val videoDescription : String?,
	val thumbnailUrl : String?,
	val uploadDate : String?,
	val link : String?,
	val linkEmbed : String?,
	val errorMessage : String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
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
		parcel.writeInt(year)
		parcel.writeString(videoId)
		parcel.writeString(videoTitle)
		parcel.writeString(videoDescription)
		parcel.writeString(thumbnailUrl)
		parcel.writeString(uploadDate)
		parcel.writeString(link)
		parcel.writeString(linkEmbed)
		parcel.writeString(errorMessage)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ImdbTrailer> {
		override fun createFromParcel(parcel: Parcel): ImdbTrailer {
			return ImdbTrailer(parcel)
		}

		override fun newArray(size: Int): Array<ImdbTrailer?> {
			return arrayOfNulls(size)
		}
	}
}
