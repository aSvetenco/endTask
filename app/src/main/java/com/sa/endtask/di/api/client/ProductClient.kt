package com.sa.endtask.di.api.client

import com.sa.endtask.di.api.Api
import com.sa.endtask.di.api.models.Products
import retrofit2.Call
import javax.inject.Inject

class ProductClient @Inject constructor(private val api: Api) : ProductClientContract {
    override fun getProductList() = api.getProductList()
}

interface ProductClientContract {
    fun getProductList(): Call<Products>
}