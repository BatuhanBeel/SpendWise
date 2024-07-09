package com.example.spendwise.data.repository

import com.example.spendwise.data.data_source.SpendingDao
import com.example.spendwise.domain.models.Spending
import com.example.spendwise.domain.repository.SpendingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SpendingRepositoryImpl @Inject constructor(
    private val dao: SpendingDao
): SpendingRepository {

    override fun getAllItem(): Flow<List<Spending>> {
        return dao.getAllData()
    }

    override suspend fun getSpecificItem(id: Int): Spending {
        return dao.getSpecificData(id)
    }

    override fun getAllItemWithFilter(category: String): Flow<List<Spending>> {
        return dao.getAllDataWithFilter(category)
    }

    override suspend fun insertItem(item: Spending) {
        dao.insertData(item)
    }

    override suspend fun deleteAllItem() {
        dao.deleteAllData()
    }

    override suspend fun deleteItem(item: Spending) {
        dao.deleteData(item)
    }
}