package com.example.shopstock.domain.useCases

import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.domain.repository.ItemRepository
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val repository: ItemRepository) {
    suspend operator fun invoke(): List<ItemEntity> = repository.getItems()
}
