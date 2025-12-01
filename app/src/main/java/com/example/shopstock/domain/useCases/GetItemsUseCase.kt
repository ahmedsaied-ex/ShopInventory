package com.example.shopstock.domain.useCases

import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.domain.repository.ItemRepository
import com.example.shopstock.helpers.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ItemRepository
) {
   suspend operator fun invoke(): Flow<Resource<List<ItemEntity>>> {
        return repository.getItems()
    }
}
