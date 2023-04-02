package com.sevenpeakssoftware.hassanmashraful.di

import com.google.gson.GsonBuilder
import com.sevenpeakssoftware.hassanmashraful.network.CarListService
import com.sevenpeakssoftware.hassanmashraful.network.model.CarListDtoMapper
import com.sevenpeakssoftware.hassanmashraful.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Singleton
    @Provides
    fun provideCarListDtoMapper(): CarListDtoMapper {
        return CarListDtoMapper()
    }

    @Provides
    @Singleton
    fun provideCarListService(retrofit: Retrofit): CarListService {
        return retrofit.create(CarListService::class.java)
    }
}