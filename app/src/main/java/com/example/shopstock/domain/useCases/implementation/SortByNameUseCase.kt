package com.example.shopstock.domain.useCases.implementation

import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.domain.useCases.interfaces.ISortItemsByNameUseCase
import javax.inject.Inject


class SortItemsByNameUseCaseImpl @Inject constructor() : ISortItemsByNameUseCase {

    override fun invoke(items: List<ItemEntity>): List<ItemEntity> {
        return items.sortedBy { it.name.lowercase() }
    }
}
