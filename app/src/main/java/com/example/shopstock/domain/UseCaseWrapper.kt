package com.example.shopstock.domain

import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.domain.useCases.UseCases
import com.example.shopstock.helpers.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UseCase @Inject constructor(
    private val useCases: UseCases
) {
    suspend operator fun invoke(): Flow<Resource<List<ItemEntity>>> {
        return useCases.getItems()
    }



    fun sortAsc(items: List<ItemEntity>): List<ItemEntity> = useCases.mergeSortAsc(items)

    fun sortDesc(items: List<ItemEntity>): List<ItemEntity> {
        val mutableList = items.toMutableList()
        useCases.quickSortDesc(mutableList)
        return mutableList
    }

    fun searchByName(items: List<ItemEntity>, query: String): List<ItemEntity> {
        if (query.isBlank()) return items
        return useCases.binarySearchByName(items, query)
    }
    fun resetSort(items: List<ItemEntity>) :List<ItemEntity> {
        return useCases.sortAsc(
            items = items
        )
    }
}
