package com.fernando.zssn.model

data class LocationRequest(
    var latitude: String,
    var longitude: String
){
    constructor() : this("0", "0")
}