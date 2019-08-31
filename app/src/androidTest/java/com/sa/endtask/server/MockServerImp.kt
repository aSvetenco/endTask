package com.sa.endtask.server


import com.sa.endtask.api.models.Products

class MockServerImp(private val parser: AssetsParser) : MockServer {

    override fun getProductList(): Products =
        parser.getResponse(PRODUCTS_END_POINT, Products::class.java)
}

//Fake End Points
private const val PRODUCTS_END_POINT = "responses/products.json"