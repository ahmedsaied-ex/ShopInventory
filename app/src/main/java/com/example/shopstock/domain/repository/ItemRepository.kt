package com.example.shopstock.domain.repository

import com.example.shopstock.domain.models.ItemEntity

interface ItemRepository {
    suspend fun getItems(): List<ItemEntity>
}
