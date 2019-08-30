package com.sa.endtask.api

import com.sa.endtask.api.client.ProductClientContract
import com.sa.endtask.api.models.Products
import com.sa.endtask.server.MockServer
import io.reactivex.Single

class MockProductClient(private val server: MockServer) : ProductClientContract {
    override fun getProductList(): Single<Products> = server.getProductList()
}