package com.fernando.zssn.model

data class SurvivorRequest(
    var age: String,
    var items: List<ItemRequest>,
    var latitude: String,
    var longitude: String,
    var name: String,
    var surname: String
){
    constructor() : this(
        "",
        emptyList(),
        "",
        "",
        "",
        ""
    )
}
