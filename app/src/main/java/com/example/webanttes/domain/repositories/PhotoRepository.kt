package com.example.webanttes.domain.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.webanttes.data.Resource
import com.example.webanttes.data.remote.request.OrderBy
import com.example.webanttes.data.remote.response.ResponsePhoto
import com.example.webanttes.domain.models.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    fun searchPhotos(query: String?, orderBy: OrderBy?): Flow<PagingData<Photo>>
}