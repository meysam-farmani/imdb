import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieData(
	val items : List<Items>?,
	val errorMessage : String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readParcelable(Items::class.java.classLoader),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeTypedList(items)
		parcel.writeString(errorMessage)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Items> {
		override fun createFromParcel(parcel: Parcel): Items {
			return Items(parcel)
		}

		override fun newArray(size: Int): Array<Items?> {
			return arrayOfNulls(size)
		}
	}
}
