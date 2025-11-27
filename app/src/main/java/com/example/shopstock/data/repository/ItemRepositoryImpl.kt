package com.example.shopstock.data.repository

import com.example.shopstock.data.DummyData
import com.example.shopstock.data.local.ItemLocalDataSource
import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val localDataSource: ItemLocalDataSource
) : ItemRepository {

    private val initialItems = DummyData.list

    override suspend fun getItems(): List<ItemEntity> {
        return localDataSource.getItems(initialItems)
    }
}