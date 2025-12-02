package com.example.shopstock.domain.useCases.implementation

import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.domain.useCases.interfaces.IMergeSortAscUseCase
import javax.inject.Inject

class MergeSortAscUseCaseImpl @Inject constructor() : IMergeSortAscUseCase {

    override fun invoke(items: List<ItemEntity>): List<ItemEntity> {
        if (items.isEmpty()) return emptyList()
        if (items.size <= 1) return items

        val mid = items.size / 2
        val left = invoke(items.subList(0, mid))
        val right = invoke(items.subList(mid, items.size))

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
