package com.shorbgy.paging.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shorbgy.paging.pojo.Cat
import retrofit2.HttpException
import java.io.IOException
import java.lang.Integer.max

private const val STARTING_PAGE_INDEX = 1
class CatPagingSource(private val api: RetrofitApi): PagingSource<Int, Cat>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = api.getCatImages(page = page, size = params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page-1,
                nextKey = if (response.isEmpty()) null else page+1
            )
        }catch (e: IOException){
            return LoadResult.Error(e)
        }catch (e: HttpException){
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        // In our case we grab the item closest to the anchor position
        // then return its id - (state.config.pageSize / 2) as a buffer
        val anchorPosition = state.anchorPosition ?: return null
        val cat = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = (state.config.pageSize / 2))
    }

    private fun ensureValidKey(key: Int) = max(STARTING_PAGE_INDEX, key)
}