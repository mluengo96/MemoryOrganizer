package com.mluengo.memoryorganizer.di

import android.app.Application
import androidx.room.Room
import com.mluengo.memoryorganizer.data.local.AppDatabase
import com.mluengo.memoryorganizer.data.local.Converters
import com.mluengo.memoryorganizer.data.local.repository.FolderRepositoryImpl
import com.mluengo.memoryorganizer.domain.repository.FolderRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    private val converter = Converters(Moshi.Builder().build())

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "bookmark_db"
        )
            .addTypeConverter(converter)
            .build()
    }

    @Provides
    @Singleton
    fun provideFolderRepository(
        db: AppDatabase
    ): FolderRepository {
        return FolderRepositoryImpl(
            dao = db.dao
        )
    }
}