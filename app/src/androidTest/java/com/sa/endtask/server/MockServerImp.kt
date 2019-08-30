package com.sa.endtask.server


import com.sa.endtask.api.models.Products
import io.reactivex.Single

class MockServerImp(private val parser: AssetsParser) : MockServer {

    override fun getProductList(): Single<Products> =
        Single.just(parser.getResponse(PRODUCTS_END_POINT, Products::class.java))
}

//FakeEnd points
private const val PRODUCTS_END_POINT = "responses/products.json"