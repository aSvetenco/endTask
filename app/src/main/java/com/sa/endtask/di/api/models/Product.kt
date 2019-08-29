package com.sa.endtask.di.api.models


data class Products(val products: List<Product> = listOf())

data class Product(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val image: String = ""
)