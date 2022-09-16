package com.fernando.zssn.model

data class ItemRequest (
    var quantity: String,
    var type: String
){
    constructor() : this(
        "",
        ""
    )
}