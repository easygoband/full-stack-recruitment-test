package com.fernando.zssn.model

import retrofit2.http.*

interface SurvivorService {
    @GET("survivors")
    suspend fun listAllSurvivors(@Query("search") search: String?): ResponseList

    @GET("survivors/{survivorId}")
    suspend fun fetchSingleSurvivor(@Path(value = "survivorId") id: Int): ResponseSingle

    @PUT("survivors/{survivorId}/location")
    suspend fun updateSurvivorLocation(@Path(value = "survivorId") id: Int, @Body locationRequest: LocationRequest): ResponseSingle

    @PUT("survivors/{survivorId}/infected")
    suspend fun reportSurvivor(@Path(value = "survivorId") id: Int): ResponseSingle

    @GET("items")
    suspend fun fetchItems(): ResponseItemList

    @POST("survivors")
    suspend fun createSurvivor(@Body survivorRequest: SurvivorRequest): ResponseSingle
}