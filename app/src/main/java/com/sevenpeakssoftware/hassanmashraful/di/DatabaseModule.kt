package com.sevenpeakssoftware.hassanmashraful.di

import com.sevenpeakssoftware.hassanmashraful.datasource.CarListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideCarListDao(realm: Realm): CarListDao {
        return CarListDao(
            realm
        )
    }

}