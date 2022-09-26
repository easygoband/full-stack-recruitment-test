package com.fernando.zssn.data.model

import java.io.Serializable

data class Item(
    val id: Int,
    val points: Int,
    val quantity: Int,
    val type: String
) : Serializable