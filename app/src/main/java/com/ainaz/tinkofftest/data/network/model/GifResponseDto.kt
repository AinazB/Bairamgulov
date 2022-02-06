package com.ainaz.tinkofftest.data.network.model

data class GifResponseDto(
    val result: List<GifDto>,
    val totalCount: Int
)
