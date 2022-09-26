package com.fernando.zssn.data.network.request

data class ItemRequest (
    var quantity: String,
    var type: String
){
    constructor() : this(
        "",
        ""
    )
}