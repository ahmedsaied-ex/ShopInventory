package com.example.shopstock.domain.useCases

import com.example.shopstock.domain.models.ItemEntity
import javax.inject.Inject

class BinarySearchByNameUseCase @Inject constructor() {
    operator fun invoke(items: List<ItemEntity>, prefix: String): List<ItemEntity> {
        val sortedItems = items.sortedBy { it.name.lowercase() }

        // Binary search helper functions
        fun findFirstIndex(): Int {
            var left = 0
            var right = sortedItems.size - 1
            var result = -1
            while (left <= right) {
                val mid = left + (right - left) / 2
                val name = sortedItems[mid].name.lowercase()
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
            var right = sortedItems.size - 1
            var result = -1
            while (left <= right) {
                val mid = left + (right - left) / 2
                val name = sortedItems[mid].name.lowercase()
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
            sortedItems.subList(first, last + 1)
        } else emptyList()
    }
}


class SearchItemsByPrefixUseCase {
    fun execute(items: List<ItemEntity>, prefix: String): List<ItemEntity> {
        val sortedItems = items.sortedBy { it.name.lowercase() }

        // Binary search helper functions
        fun findFirstIndex(): Int {
            var left = 0
            var right = sortedItems.size - 1
            var result = -1
            while (left <= right) {
                val mid = left + (right - left) / 2
                val name = sortedItems[mid].name.lowercase()
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
            var right = sortedItems.size - 1
            var result = -1
            while (left <= right) {
                val mid = left + (right - left) / 2
                val name = sortedItems[mid].name.lowercase()
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
            sortedItems.subList(first, last + 1)
        } else emptyList()
    }
}