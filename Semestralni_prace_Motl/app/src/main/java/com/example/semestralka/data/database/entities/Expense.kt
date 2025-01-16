package com.example.semestralka.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

//Definuje třídu expense, která prezentuje tabulku expenses v databázi
@Entity(tableName = "expenses") //název tabulky v databázi
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, //id se automaticky generuje při vložení záznamu --> zabránění duplicitám
    val name: String,
    val amount: Double,
    val quantity: Int = 1,
    val totalPrice: Double,
    val description: String?, //zatím nedělám
    val date: Date //zatím nedělám
)