package com.example.shopstock.domain.useCases

import com.example.shopstock.domain.models.ItemEntity
import javax.inject.Inject


class SortItemsByNameUseCase @Inject constructor() {
    operator fun invoke(items: List<ItemEntity>): List<ItemEntity> {
        return items.sortedBy { it.name.lowercase() }
    }
}

class BinarySearchByNameUseCase @Inject constructor() {
    operator fun invoke(items: List<ItemEntity>, prefix: String): List<ItemEntity> {
        fun findFirstIndex(): Int {
            var left = 0
            var right = items.size - 1
            var result = -1
            while (left <= right) {
                val mid = left + (right - left) / 2
                val name = items[mid].name.lowercase()
                val target = prefix.lowercase()
                when {
                    name.startsWith(target) -> {
                        result = mid
                        right = mid - 1
                    }
                    name < target -> left = mid + 1
                    else -> right = mid - 1
                }
            }
            return result
        }

        fun findLastIndex(): Int {
            var left = 0
            var right = items.size - 1
            var result = -1
            while (left <= right) {
                val mid = left + (right - left) / 2
                val name = items[mid].name.lowercase()
                val target = prefix.lowercase()
                when {
                    name.startsWith(target) -> {
                        result = mid
                        left = mid + 1
                    }
                    name < target -> left = mid + 1
                    else -> right = mid - 1
                }
            }
            return result
        }

        val first = findFirstIndex()
        val last = findLastIndex()

        return if (first != -1 && last != -1) {
            items.subList(first, last + 1)
        } else emptyList()
    }
}