package com.mikirinkode.kotakfilm.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mikirinkode.kotakfilm.core.data.entity.CatalogueEntity
import com.mikirinkode.kotakfilm.core.data.entity.TrailerVideoEntity


@Database(
    entities = [CatalogueEntity::class, TrailerVideoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
}