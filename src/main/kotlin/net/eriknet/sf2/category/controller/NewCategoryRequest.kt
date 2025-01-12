package net.eriknet.sf2.category.controller

data class NewCategoryRequest(
    val slug: String,
    val title: String,
    val order: Int
)
