package com.example.spendwise.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spendwise.domain.models.Spending

@Database(entities = [Spending::class], version = 1)
abstract class SpendingDatabase: RoomDatabase(){
    abstract val dao: SpendingDao
}