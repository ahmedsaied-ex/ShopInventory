package com.example.shopstock.data.local

import com.example.shopstock.domain.models.ItemEntity
import javax.inject.Inject

    class ItemLocalDataSource @Inject constructor(private val dao: ItemDao) {

        suspend fun getItems(initialItems: List<ItemEntity>): List<ItemEntity> {
        val items = dao.getAllItems()
        return items.ifEmpty {
            dao.insertItems(initialItems)
            initialItems
        }
    }

    suspend fun insertItems(items: List<ItemEntity>) {
        dao.insertItems(items)
    }
}
