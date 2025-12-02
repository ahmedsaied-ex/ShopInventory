package com.example.shopstock.domain.useCases.interfaces

import com.example.shopstock.domain.models.ItemEntity

interface IBinarySearchByNameUseCase {
    operator fun invoke(items: List<ItemEntity>, prefix: String): List<ItemEntity>
}
