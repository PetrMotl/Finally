package com.example.semestralka.data.database

import android.content.Context
import androidx.room.Room

//Singleton pro vytvoření databáze (v pragramu je pouze jedna dtb)
object DatabaseBuilder {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase { //získává přístup k databázi
        return INSTANCE ?: synchronized(this) { //kontrola zdali dtb existuje, pokud není null vrátí ji
            val instance = Room.databaseBuilder( //vytvoření dtb pomocí room
                context.applicationContext,
                AppDatabase::class.java, //odkaz na třídu dtb, kde jsou DAO a entity
                "budget_tracker_db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}