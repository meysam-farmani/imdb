package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class SearchTitleResult (
	val id : String?,
	val resultType : String?,
	val image : String?,
	val title : String?,
	val description : String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(id)
		parcel.writeString(resultType)
		parcel.writeString(image)
		parcel.writeString(title)
		parcel.writeString(description)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<SearchTitleResult> {
		override fun createFromParcel(parcel: Parcel): SearchTitleResult {
			return SearchTitleResult(parcel)
		}

		override fun newArray(size: Int): Array<SearchTitleResult?> {
			return arrayOfNulls(size)
		}
	}
}