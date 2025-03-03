package com.example.fetch_api_activity
import retrofit2.http.GET

interface FetchRewardsApi {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}
