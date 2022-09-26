package com.fernando.zssn.data.model

import android.os.Parcelable
import com.fernando.zssn.data.model.Item
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Survivor(
    val age: Int,
    val id: Int,
    val infectedReports: Int,
    val isInfected: Boolean,
    val items: @RawValue List<Item>,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val points: Int,
    val surname: String

) : Parcelable