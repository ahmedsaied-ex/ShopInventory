package com.example.shopstock.domain.useCases.implementation

import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.domain.useCases.interfaces.IBinarySearchByNameUseCase
import javax.inject.Inject


class BinarySearchByNameUseCaseImpl @Inject constructor() : IBinarySearchByNameUseCase {

    override fun invoke(items: List<ItemEntity>, prefix: String): List<ItemEntity> {
        fun findFirstIndex(): Int {
            var l = 0
            var r = items.size - 1
            var result = -1
            val target = prefix.lowercase()

            while (l <= r) {
                val mid = (l + r) / 2
                val name = items[mid].name.lowercase()

                when {
                    name.startsWith(target) -> {
                        result = mid
                        r = mid - 1
                    }
                    name < target -> l = mid + 1
                    else -> r = mid - 1
                }
            }
            return result
        }

        fun findLastIndex(): Int {
            var l = 0
            var r = items.size - 1
            var result = -1
            val target = prefix.lowercase()

            while (l <= r) {
                val mid = (l + r) / 2
                val name = items[mid].name.lowercase()

                when {
                    name.startsWith(target) -> {
                        result = mid
                        l = mid + 1
                    }
                    name < target -> l = mid + 1
                    else -> r = mid - 1
                }
            }
            return result
        }

        val first = findFirstIndex()
        val last = findLastIndex()

        return if (first != -1 && last != -1) items.subList(first, last + 1)
        else emptyList()
    }
}
