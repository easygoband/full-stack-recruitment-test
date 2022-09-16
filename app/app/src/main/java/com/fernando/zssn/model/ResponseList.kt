package com.fernando.zssn.model

data class ResponseList(
    val code: Int,
    val `data`: List<Survivor>,
    val message: String,
    val totalResults: Int
)