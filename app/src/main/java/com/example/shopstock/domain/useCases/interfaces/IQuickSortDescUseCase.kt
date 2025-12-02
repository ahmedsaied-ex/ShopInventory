package com.example.shopstock.domain.useCases.interfaces

import com.example.shopstock.domain.models.ItemEntity

interface IQuickSortDescUseCase {
    operator fun invoke(items: MutableList<ItemEntity>, low: Int = 0, high: Int = items.size - 1)
}
