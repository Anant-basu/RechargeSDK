package com.example.recharge.modal

data class RechargeRequestModal(
    val payload: List<Payload>,
    val responseCode: Int,
    val status: String
)