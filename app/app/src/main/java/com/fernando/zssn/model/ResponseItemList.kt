package com.fernando.zssn.model

data class ResponseItemList(
    val code: Int,
    val `data`: List<Item>,
    val message: String,
    val totalResults: Int
)