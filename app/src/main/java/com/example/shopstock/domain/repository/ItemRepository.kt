package com.example.shopstock.domain.repository

import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun getItems(): Flow<Resource<List<ItemEntity>>>
}
