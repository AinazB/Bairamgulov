package com.ainaz.tinkofftest.data.network.model

import com.ainaz.tinkofftest.domain.model.Gif

internal fun GifDto.toGif(): Gif {
    return Gif(
        id = id ?: 0,
        author = author ?: "",
        description = description ?: "",
        gifUrl = gifURL ?: "",
        previewUrl = previewURL ?: ""
    )
}