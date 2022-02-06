package com.ainaz.tinkofftest.di

import com.ainaz.tinkofftest.data.network.GifsService
import com.ainaz.tinkofftest.data.network.repository.GifsRepositoryImpl
import com.ainaz.tinkofftest.domain.repository.GifsRepository
import com.ainaz.tinkofftest.domain.usecase.GetHotGifsUseCase
import com.ainaz.tinkofftest.domain.usecase.GetLatestGifsUseCase
import com.ainaz.tinkofftest.domain.usecase.GetTopGifsUseCase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideGifsService(): GifsService {
        val httpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://developerslife.ru")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(GifsService::class.java)
    }

    @Provides
    fun provideGifsRepository(service: GifsService): GifsRepository = GifsRepositoryImpl(service)

    @Provides
    fun provideLatestGifsUseCase(gifsRepository: GifsRepository) = GetLatestGifsUseCase(gifsRepository)

    @Provides
    fun provideTopGifsUseCase(gifsRepository: GifsRepository) = GetTopGifsUseCase(gifsRepository)

    @Provides
    fun provideHotGifsUseCase(gifsRepository: GifsRepository) = GetHotGifsUseCase(gifsRepository)

}