package com.example.shopstock.domain.useCases


import javax.inject.Inject

data class UseCases @Inject constructor(
    val getItems: GetItemsUseCase,
    val mergeSortAsc: MergeSortAscUseCase,
    val quickSortDesc: QuickSortDescUseCase,
    val binarySearchByName: BinarySearchByNameUseCase
)