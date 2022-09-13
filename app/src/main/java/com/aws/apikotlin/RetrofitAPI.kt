package com.aws.apikotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST


interface RetrofitAPI {

    @GET(Constants.todos)
    fun demoData(): Call<DemoDataModel?>
}