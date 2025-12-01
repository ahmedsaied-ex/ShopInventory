package com.example.shopstock.data.repository

import com.example.shopstock.data.DummyData
import com.example.shopstock.data.local.ItemLocalDataSource
import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.domain.repository.ItemRepository
import com.example.shopstock.helpers.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val localDataSource: ItemLocalDataSource
) : ItemRepository {

    private val initialItems = DummyData.list

    override suspend fun getItems(): Flow<Resource<List<ItemEntity>>> = flow {
        emit(Resource.Loading)

        try {
            val items = localDataSource.getItems(initialItems)
            emit(Resource.Success(items))

        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error", e))
        }

    }
}
