package com.example.webanttes.di.factories

import com.example.webanttes.data.paging.PhotosPagingSource
import com.example.webanttes.data.remote.request.OrderBy
import dagger.assisted.AssistedFactory

@AssistedFactory
interface PhotosPagingSourceFactory {

    fun create(orderBy: OrderBy?, query: String?): PhotosPagingSource
}