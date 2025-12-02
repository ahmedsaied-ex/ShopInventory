package com.example.shopstock.domain.useCases.interfaces

import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface IGetItemsUseCase {
    suspend operator fun invoke(): Flow<Resource<List<ItemEntity>>>
}
