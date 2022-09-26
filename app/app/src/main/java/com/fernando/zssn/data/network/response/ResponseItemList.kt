package com.fernando.zssn.data.network.response

import com.fernando.zssn.data.model.Item

data class ResponseItemList(
    val code: Int,
    val `data`: List<Item>,
    val message: String,
    val totalResults: Int
)