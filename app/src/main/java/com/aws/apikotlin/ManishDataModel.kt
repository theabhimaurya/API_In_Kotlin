package com.aws.apikotlin

data class ManishDataModel(
    val response: Response
)

data class Response(
    val address: String,
    val dor: String,
    val email: String,
    val fname: String,
    val lname: String,
    val mobile: String
)