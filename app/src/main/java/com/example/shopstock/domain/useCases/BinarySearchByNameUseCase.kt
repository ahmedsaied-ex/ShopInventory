package com.example.shopstock.domain.useCases

import com.example.shopstock.domain.models.ItemEntity
import javax.inject.Inject

class BinarySearchByNameUseCase @Inject constructor() {
    operator fun invoke(items: List<ItemEntity>, target: String): ItemEntity? {
        var low = 0
        var high = items.size - 1
        val sorted = items.sortedBy { it.name } // نضمن الترتيب
        while (low <= high) {
            val mid = (low + high) / 2
            val cmp = sorted[mid].name.compareTo(target)
            when {
                cmp == 0 -> return sorted[mid]
                cmp < 0 -> low = mid + 1
                else -> high = mid - 1
            }
        }
        return null
    }
}
