package com.aws.apikotlin

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private var retrofit: Retrofit? = null

    fun initRetrofit(): Retrofit? {
        val client = OkHttpClient.Builder()
            .build()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit
    }
}