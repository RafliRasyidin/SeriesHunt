package com.rasyidin.serieshunt.di

import android.content.Context
import androidx.transition.ChangeBounds
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.serieshunt.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesGlideInstance(@ApplicationContext context: Context) =
        Glide.with(context)
            .setDefaultRequestOptions(
                RequestOptions()
                    .error(R.drawable.ic_broken_image)
            )

    @Provides
    @Singleton
    fun providesChangeBoundsInstance(): ChangeBounds = ChangeBounds()

}