package com.example.shopstock.domain.useCases


import com.example.shopstock.domain.useCases.interfaces.IBinarySearchByNameUseCase
import com.example.shopstock.domain.useCases.interfaces.IGetItemsUseCase
import com.example.shopstock.domain.useCases.interfaces.IMergeSortAscUseCase
import com.example.shopstock.domain.useCases.interfaces.IQuickSortDescUseCase
import com.example.shopstock.domain.useCases.interfaces.ISortItemsByNameUseCase
import javax.inject.Inject

data class UseCases @Inject constructor(
    val getItems: IGetItemsUseCase,
    val mergeSortAsc: IMergeSortAscUseCase,
    val quickSortDesc: IQuickSortDescUseCase,
    val binarySearchByName: IBinarySearchByNameUseCase,
    val sortAsc: ISortItemsByNameUseCase,
)
