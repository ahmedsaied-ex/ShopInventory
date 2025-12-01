package com.example.shopstock.domain.useCases

import com.example.shopstock.domain.models.ItemEntity
import javax.inject.Inject

class QuickSortDescUseCase @Inject constructor() {
    operator fun invoke(items: MutableList<ItemEntity>, low: Int = 0, high: Int = items.size - 1) {
        if (items.isEmpty()) return
        if (low < high) {
            val pi = partition(items, low, high)
            invoke(items, low, pi - 1)
            invoke(items, pi + 1, high)
        }
    }

    private fun partition(items: MutableList<ItemEntity>, low: Int, high: Int): Int {
        val pivot = items[high].itemLeftInInventory
        var i = low - 1
        for (j in low until high) {
            if (items[j].itemLeftInInventory >= pivot) {
                i++
                items.swap(i, j)
            }
        }
        items.swap(i + 1, high)
        return i + 1
    }

    private fun MutableList<ItemEntity>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}
