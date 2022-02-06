package com.ainaz.tinkofftest.domain.usecase

import androidx.paging.PagingData
import com.ainaz.tinkofftest.domain.model.Gif
import com.ainaz.tinkofftest.domain.repository.GifsRepository
import kotlinx.coroutines.flow.Flow

class GetTopGifsUseCase(val gifsRepository: GifsRepository) {
    operator fun invoke(): Flow<PagingData<Gif>> = gifsRepository.getPagedTopGifs()
}