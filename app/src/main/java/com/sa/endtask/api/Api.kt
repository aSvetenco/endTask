package com.sa.endtask.api

import com.sa.endtask.api.models.Products
import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("catalog/example.json")
    fun getProductList(): Single<Products>
}