package com.example.shopstock.domain.useCases.interfaces

import com.example.shopstock.domain.models.ItemEntity

interface ISortItemsByNameUseCase {
    operator fun invoke(items: List<ItemEntity>): List<ItemEntity>
}
