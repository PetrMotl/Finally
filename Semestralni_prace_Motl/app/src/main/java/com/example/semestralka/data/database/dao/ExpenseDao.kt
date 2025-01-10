package com.example.semestralka.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.semestralka.data.database.entities.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): kotlinx.coroutines.flow.Flow<List<Expense>>

    @Query("SELECT SUM(totalPrice) FROM expenses")
    fun getTotalSpent(): Flow<Double?>

    @Query("DELETE FROM expenses")
    suspend fun deleteAllExpenses()

    @Delete
    suspend fun deleteExpense(expense: Expense)
}