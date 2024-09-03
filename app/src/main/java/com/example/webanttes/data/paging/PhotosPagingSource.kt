package com.example.webanttes.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.webanttes.data.network.ApiErrorResponse
import com.example.webanttes.data.network.ApiSuccessResponse
import com.example.webanttes.data.remote.UnsplashApi
import com.example.webanttes.data.remote.request.OrderBy
import com.example.webanttes.domain.converters.toPhoto
import com.example.webanttes.domain.models.Photo
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class PhotosPagingSource @AssistedInject constructor(
    private val api: UnsplashApi,
    @Assisted private val orderBy: OrderBy?,
    @Assisted private val query: String?
):PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val currentPage = params.key ?: 1
        if(query.isNullOrEmpty()) {
            return when (val photos =
                api.getPhotos(
                    page = currentPage,
                    perPage = 20,
                    orderBy = orderBy
                )) {
                is ApiSuccessResponse -> LoadResult.Page(
                    data = photos.data.map { it.toPhoto() },
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (photos.data.isEmpty()) null else currentPage + 1
                )
                is ApiErrorResponse -> LoadResult.Error(photos.error)
            }
        }else{
            return when (val photos =
                api.searchPhotos(
                    page = currentPage,
                    perPage = 20,
                    orderBy = orderBy,
                    query = query
                )) {
                is ApiSuccessResponse -> LoadResult.Page(
                    data = photos.data.results.map { it.toPhoto() },
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (photos.data.results.isEmpty()) null else currentPage + 1
                )
                is ApiErrorResponse -> LoadResult.Error(photos.error)
            }
        }
    }
}