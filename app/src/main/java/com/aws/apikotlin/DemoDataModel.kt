package com.aws.apikotlin

class DemoDataModel : ArrayList<DemoDataModelItem>()

data class DemoDataModelItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)