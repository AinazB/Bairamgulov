package com.ainaz.tinkofftest.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ainaz.tinkofftest.domain.model.Gif

typealias GifsPageLoader = suspend (pageIndex: Int, pageSize: Int) -> List<Gif>

class GifsPagingSource(
    private val loader: GifsPageLoader,
    private val pageSize: Int
) : PagingSource<Int, Gif>() {
    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        val pageIndex = params.key ?: 0

        return try {
            val gifs = loader.invoke(pageIndex, pageSize)

            return LoadResult.Page(
                data = gifs,
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = if (gifs.size < pageSize) null else pageIndex + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }
}