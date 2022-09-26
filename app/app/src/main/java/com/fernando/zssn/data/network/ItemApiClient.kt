package com.fernando.zssn.data.network

import com.fernando.zssn.data.network.response.ResponseItemList
import retrofit2.http.GET

interface ItemApiClient {
    @GET("items")
    suspend fun fetchItems(): ResponseItemList
}