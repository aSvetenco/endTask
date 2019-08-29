package com.sa.endtask.api

import com.sa.endtask.api.models.Products
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("catalog/example.json")
    fun getProductList(): Call<Products>
}