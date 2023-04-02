package com.sevenpeakssoftware.hassanmashraful.di

import android.content.Context
import com.sevenpeakssoftware.hassanmashraful.BaseApplication
import com.sevenpeakssoftware.hassanmashraful.util.SCHEMA_VERSION
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): BaseApplication {
        return context as BaseApplication
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Realm {
        Realm.init(context)
        val realmConfig = RealmConfiguration.Builder()
            .name("car_db.realm")
            .schemaVersion(SCHEMA_VERSION)
            .allowWritesOnUiThread(true)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(realmConfig)
        return Realm.getDefaultInstance()
    }
}