package com.fernando.zssn.model

data class ResponseSingle (
    val code: Int,
    val `data`: Survivor,
    val message: String,
    val totalResults: Int
)