package com.marketkhoone.imdb.model.entity

import android.os.Parcel
import android.os.Parcelable

data class SearchTitle(
	val searchType : String?,
	val expression : String?,
	val results : List<SearchTitleResult>?,
	val errorMessage : String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(SearchTitleResult::class.java.classLoader),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(searchType)
		parcel.writeString(expression)
		parcel.writeTypedList(results)
		parcel.writeString(errorMessage)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<SearchTitle> {
		override fun createFromParcel(parcel: Parcel): SearchTitle {
			return SearchTitle(parcel)
		}

		override fun newArray(size: Int): Array<SearchTitle?> {
			return arrayOfNulls(size)
		}
	}
}
