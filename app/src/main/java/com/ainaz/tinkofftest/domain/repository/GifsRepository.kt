package com.ainaz.tinkofftest.domain.repository

import androidx.paging.PagingData
import com.ainaz.tinkofftest.domain.model.Gif
import kotlinx.coroutines.flow.Flow

interface GifsRepository {
    fun getPagedLatestGifs(): Flow<PagingData<Gif>>

    fun getPagedTopGifs(): Flow<PagingData<Gif>>

    fun getPagedHotGifs(): Flow<PagingData<Gif>>
}