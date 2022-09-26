package com.fernando.zssn.data.network.response

import com.fernando.zssn.data.model.Survivor

data class ResponseSingle (
    val code: Int,
    val `data`: Survivor,
    val message: String,
    val totalResults: Int
)