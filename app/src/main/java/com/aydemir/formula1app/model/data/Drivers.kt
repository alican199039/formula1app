package com.aydemir.formula1app.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Items(
    val items: MutableList<Driver>
)

@Parcelize
data class Driver(
    val id: Int,
    val name: String,
    val point: Int
) : Parcelable