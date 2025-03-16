package com.example.fetch_api_activity

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    private val moshi = Moshi.Builder() // convert json to kotlin object
        .add(KotlinJsonAdapterFactory())  // enable moshi to read the data classes
        .build()

    private val retrofit = Retrofit.Builder() // htttp request builder
        .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi)) // json parser
        .build()

    val api: FetchRewardsApi = retrofit.create(FetchRewardsApi::class.java)
}
