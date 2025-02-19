package com.example.semestralka.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

//Definuje třídu favouriteItem, která představuje tabulku favourite_items v databázi
@Entity(tableName = "favorite_items")
data class FavoriteItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double
)