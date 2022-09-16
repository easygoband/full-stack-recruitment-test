package com.fernando.zssn.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SurvivorClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.83:8090/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: SurvivorService = retrofit.create(SurvivorService::class.java)
}