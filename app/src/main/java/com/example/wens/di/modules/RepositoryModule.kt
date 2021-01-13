package com.example.wens.di.modules

import com.example.wens.operation.remote.WensRemoteOperation
import com.example.wens.repository.IWensDataSource
import com.example.wens.repository.WensRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesWensRemoteOperation():IWensDataSource = WensRemoteOperation

    @Singleton
    @Provides
    fun provideWensRepository(wensRemoteSource: IWensDataSource) =
        WensRepository(wensRemoteSource)

}