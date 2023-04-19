package com.example.recyclerviewex

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class Items(val image: Int, val toy_name: String,val toy_age:String,val toy_price:String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    fun  getPrice():String
    {

        return toy_price.toString()
    }

    override fun describeContents(): Int {
        return  0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(image)
        dest.writeString(toy_name)
        dest.writeString(toy_age)
        dest.writeString(toy_price)

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