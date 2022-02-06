package com.ainaz.tinkofftest.data.network.model

data class GifDto(
    val id: Int = 0,
    val description: String = "",
    val votes: Int = 0,
    val author: String = "",
    val gifURL: String = "",
    val gifSize: Int = 0,
    val previewURL: String = "",
    val videoURL: String = "",
    val videoPath: String = "",
    val videoSize: Int = 0,
    val type: String = "",
    val width: String = "",
    val height: String = "",
    val commentsCount: Int = 0,
    val fileSize: Int = 0,
    val canVote: Boolean = false
)