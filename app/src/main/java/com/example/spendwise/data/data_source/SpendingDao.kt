package com.example.spendwise.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.spendwise.domain.models.Spending
import kotlinx.coroutines.flow.Flow

@Dao
interface SpendingDao {

    @Query("SELECT * FROM expenses_table ORDER BY due DESC")
    fun getAllData(): Flow<List<Spending>>

    @Query("SELECT * From expenses_table WHERE id = :id")
    suspend fun getSpecificData(id: Int): Spending

    @Query("SELECT * From expenses_table WHERE category = :category ORDER BY due DESC")
    fun getAllDataWithFilter(category: String): Flow<List<Spending>>

    @Upsert
    suspend fun insertData(item: Spending)

    @Query("DELETE FROM expenses_table")
    suspend fun deleteAllData()

    @Delete
    suspend fun deleteData(item: Spending)

}