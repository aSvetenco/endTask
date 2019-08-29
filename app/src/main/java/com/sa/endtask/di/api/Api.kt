package com.sa.endtask.di.api

import com.sa.endtask.di.api.models.Products
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("catalog/example.json")
    fun getProductList(): Call<Products>
}