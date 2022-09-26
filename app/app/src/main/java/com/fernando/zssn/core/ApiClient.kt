package com.fernando.zssn.core

import com.fernando.zssn.data.network.ItemApiClient
import com.fernando.zssn.data.network.SurvivorApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://165.227.203.24:8090/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val survivorService: SurvivorApiClient = retrofit.create(SurvivorApiClient::class.java)

    val itemService: ItemApiClient = retrofit.create(ItemApiClient::class.java)

}