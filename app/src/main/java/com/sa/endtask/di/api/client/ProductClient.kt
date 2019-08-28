package com.sa.endtask.di.api.client

import com.sa.endtask.di.api.Api
import com.sa.endtask.di.api.models.Product
import javax.inject.Inject

class ProductClient @Inject constructor(private val api: Api) : ProductClientContract {
    override fun getProductList(): List<Product> = api.getProductList().execute().body()!!
}

interface ProductClientContract {
    fun getProductList(): List<Product>
}