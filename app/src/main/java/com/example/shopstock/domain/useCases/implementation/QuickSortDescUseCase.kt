package com.example.shopstock.domain.useCases.implementation

import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.domain.useCases.interfaces.IQuickSortDescUseCase
import javax.inject.Inject
class QuickSortDescUseCaseImpl @Inject constructor() : IQuickSortDescUseCase {

    override fun invoke(items: MutableList<ItemEntity>, low: Int, high: Int) {
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
