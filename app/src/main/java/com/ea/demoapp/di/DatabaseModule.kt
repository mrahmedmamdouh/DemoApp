package com.ea.demoapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ea.demoapp.data.datasource.MedicineDao
import com.ea.demoapp.data.datasource.MedicineEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [MedicineEntity::class], version = 1)
abstract class MedicineDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MedicineDatabase {
        return Room.databaseBuilder(
            context,
            MedicineDatabase::class.java,
            "medicine_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMedicineDao(database: MedicineDatabase): MedicineDao {
        return database.medicineDao()
    }
}
