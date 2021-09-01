package com.rasyidin.serieshunt.di

import com.rasyidin.serieshunt.core.data.repository.ITvShowRepository
import com.rasyidin.serieshunt.core.domain.usecase.ITvShowUseCase
import com.rasyidin.serieshunt.core.domain.usecase.TvShowInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesTvShowUseCase(tvShowRepository: ITvShowRepository): ITvShowUseCase =
        TvShowInteractors(tvShowRepository)
}