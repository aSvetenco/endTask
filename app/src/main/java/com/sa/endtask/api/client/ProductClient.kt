package com.sa.endtask.api.client

import com.sa.endtask.api.Api
import com.sa.endtask.api.models.Products
import retrofit2.Call
import javax.inject.Inject

class ProductClient @Inject constructor(private val api: Api) : ProductClientContract {
    override fun getProductList() = api.getProductList()
}

interface ProductClientContract {
    fun getProductList(): Call<Products>
}