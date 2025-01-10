package com.example.semestralka.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val amount: Double,
    val quantity: Int = 1,
    val totalPrice: Double,
    val description: String?,
    val date: Date
)