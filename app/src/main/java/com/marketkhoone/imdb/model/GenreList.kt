import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GenreList(
	val key: String?,
	val value: String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(key)
		parcel.writeString(value)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<GenreList> {
		override fun createFromParcel(parcel: Parcel): GenreList {
			return GenreList(parcel)
		}

		override fun newArray(size: Int): Array<GenreList?> {
			return arrayOfNulls(size)
		}
	}
}