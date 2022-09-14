package com.fernando.zssn.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Survivor(
    val name: String,
    val surname: String
) : Parcelable