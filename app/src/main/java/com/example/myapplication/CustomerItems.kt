package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class CustomerItems(
    val CusName: String,
    val CusPassword: String,
    val CusMail:String,
    val CusBirtdate:String,
    val CusPhone:String,
    val CusAddress:String){}
