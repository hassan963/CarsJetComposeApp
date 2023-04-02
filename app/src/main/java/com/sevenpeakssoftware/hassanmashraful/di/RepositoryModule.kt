package com.sevenpeakssoftware.hassanmashraful.di

import com.sevenpeakssoftware.hassanmashraful.datasource.CarListDao
import com.sevenpeakssoftware.hassanmashraful.network.CarListService
import com.sevenpeakssoftware.hassanmashraful.network.model.CarListDtoMapper
import com.sevenpeakssoftware.hassanmashraful.repository.CarListRepository
import com.sevenpeakssoftware.hassanmashraful.repository.CarListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideCarListRepository(
        dao: CarListDao,
        carListService: CarListService,
        mapper: CarListDtoMapper
    ): CarListRepository {
        return CarListRepositoryImpl(
            dao,
            carListService,
            mapper
        )
    }
}