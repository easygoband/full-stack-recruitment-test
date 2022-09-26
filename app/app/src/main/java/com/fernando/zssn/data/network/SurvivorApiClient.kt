package com.fernando.zssn.data.network

import com.fernando.zssn.data.network.request.LocationRequest
import com.fernando.zssn.data.network.request.SurvivorRequest
import com.fernando.zssn.data.network.response.ResponseList
import com.fernando.zssn.data.network.response.ResponseSingle
import retrofit2.http.*

interface SurvivorApiClient {
    @GET("survivors")
    suspend fun listAllSurvivors(@Query("search") search: String?): ResponseList

    @GET("survivors/{survivorId}")
    suspend fun fetchSingleSurvivor(@Path(value = "survivorId") id: Int): ResponseSingle

    @PUT("survivors/{survivorId}/location")
    suspend fun updateSurvivorLocation(@Path(value = "survivorId") id: Int, @Body locationRequest: LocationRequest): ResponseSingle

    @PUT("survivors/{survivorId}/infected")
    suspend fun reportSurvivor(@Path(value = "survivorId") id: Int): ResponseSingle

    @POST("survivors")
    suspend fun createSurvivor(@Body survivorRequest: SurvivorRequest): ResponseSingle
}