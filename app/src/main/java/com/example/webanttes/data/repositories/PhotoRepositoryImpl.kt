package com.example.webanttes.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.webanttes.data.Resource
import com.example.webanttes.data.network.ApiErrorResponse
import com.example.webanttes.data.network.ApiSuccessResponse
import com.example.webanttes.data.remote.UnsplashApi
import com.example.webanttes.data.remote.request.OrderBy
import com.example.webanttes.di.factories.PhotosPagingSourceFactory
import com.example.webanttes.domain.converters.toPhoto
import com.example.webanttes.domain.models.Photo
import com.example.webanttes.domain.repositories.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val api: UnsplashApi,
    private val photosPagingSourceFactory: PhotosPagingSourceFactory
):PhotoRepository{

    override fun searchPhotos(
        query: String?,
        orderBy: OrderBy?
    ): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 1),
            pagingSourceFactory = {
                photosPagingSourceFactory.create(orderBy, query)
            }
        ).flow
    }
}