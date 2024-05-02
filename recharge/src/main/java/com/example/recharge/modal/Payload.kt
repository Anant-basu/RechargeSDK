package com.example.recharge.modal

data class Payload(
    val category: Category,
    val `operator`: Operator,
    val productId: Int,
    val subCategory: SubCategory
)