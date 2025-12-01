package com.example.shopstock.domain.useCases

import com.example.shopstock.domain.models.ItemEntity
import javax.inject.Inject

class MergeSortAscUseCase @Inject constructor() {
    operator fun invoke(items: List<ItemEntity>): List<ItemEntity> {
        if (items.isNullOrEmpty()) return emptyList()
        if (items.size <= 1) return items
        val middle = items.size / 2
        val left = invoke(items.subList(0, middle))
        val right = invoke(items.subList(middle, items.size))
        return merge(left, right)
    }

    private fun merge(left: List<ItemEntity>, right: List<ItemEntity>): List<ItemEntity> {
        var l = 0
        var r = 0
        val result = mutableListOf<ItemEntity>()
        while (l < left.size && r < right.size) {
            if (left[l].itemLeftInInventory <= right[r].itemLeftInInventory) {
                result.add(left[l++])
            } else {
                result.add(right[r++])
            }
        }
        result.addAll(left.subList(l, left.size))
        result.addAll(right.subList(r, right.size))
        return result
    }
}
