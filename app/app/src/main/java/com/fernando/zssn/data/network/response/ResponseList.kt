package com.fernando.zssn.data.network.response

import com.fernando.zssn.data.model.Survivor

data class ResponseList(
    val code: Int,
    val `data`: List<Survivor>,
    val message: String,
    val totalResults: Int
)