package com.rasyidin.serieshunt.core.di

import com.rasyidin.serieshunt.core.data.repository.TvShowRepository
import com.rasyidin.serieshunt.core.data.source.remote.RemoteDataSource
import com.rasyidin.serieshunt.core.data.source.remote.network.ApiService
import com.rasyidin.serieshunt.core.data.repository.ITvShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRemoteDataSource(apiService: ApiService): RemoteDataSource =
        RemoteDataSource(apiService)

    @Provides
    @Singleton
    fun providesTvShowRepository(remoteDataSource: RemoteDataSource): ITvShowRepository =
        TvShowRepository(remoteDataSource)
}