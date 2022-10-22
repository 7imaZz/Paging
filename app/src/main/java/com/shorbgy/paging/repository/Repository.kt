package com.shorbgy.paging.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shorbgy.paging.network.CatPagingSource
import com.shorbgy.paging.network.RetrofitApi
import com.shorbgy.paging.pojo.Cat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 30
class Repository
@Inject
constructor(private val api: RetrofitApi){
    fun getCats(): Flow<PagingData<Cat>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE*2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CatPagingSource(api) }
        ).flow
    }
}