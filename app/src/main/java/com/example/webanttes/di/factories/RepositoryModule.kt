package com.example.webanttes.di.factories

import com.example.webanttes.data.remote.UnsplashApi
import com.example.webanttes.data.repositories.PhotoRepositoryImpl
import com.example.webanttes.domain.repositories.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{

    @Provides
    @Singleton
    fun providePhotoRepository(api: UnsplashApi, photosPagingSourceFactory:PhotosPagingSourceFactory): PhotoRepository = PhotoRepositoryImpl(api, photosPagingSourceFactory)
}