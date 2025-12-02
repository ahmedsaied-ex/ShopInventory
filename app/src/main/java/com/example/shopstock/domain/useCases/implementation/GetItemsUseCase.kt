package com.example.shopstock.domain.useCases.implementation

import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.domain.repository.ItemRepository
import com.example.shopstock.domain.useCases.interfaces.IGetItemsUseCase
import com.example.shopstock.helpers.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsUseCaseImpl @Inject constructor(
    private val repository: ItemRepository
) : IGetItemsUseCase {

    override suspend fun invoke(): Flow<Resource<List<ItemEntity>>> {
        return repository.getItems()
    }
}