package com.example.semestralka.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.semestralka.data.database.dao.ExpenseDao
import com.example.semestralka.data.database.dao.FavoriteItemDao
import com.example.semestralka.data.database.entities.Expense
import com.example.semestralka.data.database.entities.FavoriteItem

@Database(entities = [Expense::class, FavoriteItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun favoriteItemDao(): FavoriteItemDao
}