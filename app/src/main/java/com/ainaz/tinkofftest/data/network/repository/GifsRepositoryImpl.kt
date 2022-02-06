package com.ainaz.tinkofftest.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ainaz.tinkofftest.data.network.GifsPageLoader
import com.ainaz.tinkofftest.data.network.GifsPagingSource
import com.ainaz.tinkofftest.data.network.GifsService
import com.ainaz.tinkofftest.data.network.model.toGif
import com.ainaz.tinkofftest.domain.model.Gif
import com.ainaz.tinkofftest.domain.repository.GifsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GifsRepositoryImpl(
    private val service: GifsService
) : GifsRepository {

    override fun getPagedLatestGifs(): Flow<PagingData<Gif>> {
        val loader: GifsPageLoader = { pageIndex, _ ->
            service.getLatestGifs(pageIndex).result.map { it.toGif() }
        }
        return initPager(loader)
    }

    override fun getPagedTopGifs(): Flow<PagingData<Gif>> {
        val loader: GifsPageLoader = { pageIndex, _ ->
            service.getTopGifs(pageIndex).result.map { it.toGif() }
        }
        return initPager(loader)
    }

    override fun getPagedHotGifs(): Flow<PagingData<Gif>> {
        val loader: GifsPageLoader = { pageIndex, _ ->
            service.getHotGifs(pageIndex).result.map { it.toGif() }
        }
        return initPager(loader)
    }

    private fun initPager(loader: GifsPageLoader) = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { GifsPagingSource(loader, PAGE_SIZE) }
    ).flow

    private companion object {
        const val PAGE_SIZE = 5
    }
}