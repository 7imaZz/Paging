package com.shorbgy.paging.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shorbgy.paging.core.BaseViewModel
import com.shorbgy.paging.pojo.Cat
import com.shorbgy.paging.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class NetworkViewModel
@Inject
constructor(
    repository: Repository
): ViewModel(){
    var cats: Flow<PagingData<Cat>> = repository.getCats().cachedIn(viewModelScope)
}