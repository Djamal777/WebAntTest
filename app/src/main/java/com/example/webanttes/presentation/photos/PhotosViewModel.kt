package com.example.webanttes.presentation.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.webanttes.data.Resource
import com.example.webanttes.data.remote.request.OrderBy
import com.example.webanttes.domain.models.Photo
import com.example.webanttes.domain.repositories.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    val repository: PhotoRepository
): ViewModel() {

    val orderBy = MutableSharedFlow<OrderBy>()

    val query = MutableSharedFlow<String>()

    @OptIn(ExperimentalCoroutinesApi::class)
    val photos = combine(orderBy, query){ order, q->
        Pair(order,q)
    }.flatMapLatest {(order, q)->
        repository.searchPhotos(q, order)
    }

//    val photos = repository.getPhotos(OrderBy.latest).cachedIn(viewModelScope).asFlow()

}