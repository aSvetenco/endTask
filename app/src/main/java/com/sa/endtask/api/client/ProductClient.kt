package com.sa.endtask.api.client

import com.sa.endtask.api.Api
import com.sa.endtask.api.models.Products
import io.reactivex.Single

class ProductClient(private val api: Api) : ProductClientContract {
    override fun getProductList() = api.getProductList()
}

interface ProductClientContract {
    fun getProductList(): Single<Products>
}