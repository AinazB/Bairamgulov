package com.ainaz.tinkofftest.domain.model

data class Gif(
    val id: Int,
    val author: String,
    val description: String,
    val gifUrl: String,
    val previewUrl: String
)
