package com.example.semestralka.data.database.dao

import androidx.room.*
import com.example.semestralka.data.database.entities.FavoriteItem
import kotlinx.coroutines.flow.Flow

//práce s daty v dtb u favourite itemu
@Dao
interface FavoriteItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteItem(item: FavoriteItem)

    @Query("SELECT * FROM favorite_items")
    fun getAllFavoriteItems(): Flow<List<FavoriteItem>>
}