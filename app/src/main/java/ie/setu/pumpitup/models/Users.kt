package ie.setu.pumpitup.models

import android.os.Parcel
import android.os.Parcelable

data class Users(val email:String,
                 val password:String) : Parcelable  {

    constructor(parcel: Parcel): this(parcel.readString()!!, parcel.readString()!!)


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(this.email)
        parcel.writeString(this.password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR: Parcelable.Creator<Users> {
        override fun createFromParcel(parcel: Parcel): Users{
            return Users(parcel)
        }

        override fun newArray(size: Int): Array<Users?> {
            return arrayOfNulls(size)
        }
    }
}


