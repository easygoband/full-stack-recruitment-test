package com.fernando.zssn.data.network.request

data class LocationRequest(
    var latitude: String,
    var longitude: String
){
    constructor() : this("0", "0")
}