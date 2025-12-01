package com.example.shopstock.presintation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.domain.useCases.UseCases
import com.example.shopstock.helpers.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val useCases: UseCase
) : ViewModel() {

    enum class SortOrder { NONE, ASC, DESC }

    private var currentSort = SortOrder.NONE

    private val _initList = MutableStateFlow<List<ItemEntity>>(emptyList())
    val initList: StateFlow<List<ItemEntity>> = _initList

    private val _items = MutableStateFlow<List<ItemEntity>>(emptyList())
    val items: StateFlow<List<ItemEntity>> = _items

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            useCases()   // operator invoke
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _isLoading.value = true
                            _error.value = null
                        }

                        is Resource.Success -> {
                            _isLoading.value = false
                            _error.value = null

                            _initList.value = result.data
                            _items.value = applyCurrentSort(result.data)
                        }

                        is Resource.Error -> {
                            _isLoading.value = false
                            _error.value = result.message
                            _items.value = emptyList()
                        }
                    }
                }
        }
    }

    fun sort(sortOrder: SortOrder) {
        currentSort = sortOrder
        _items.value = applyCurrentSort(_items.value)
    }

    fun resetSort() {
        currentSort = SortOrder.NONE
        _items.value = _initList.value
    }

    fun search(query: String) {
        val filtered = useCases.searchByName(_initList.value, query)
        _items.value = applyCurrentSort(filtered)
    }

    fun retry() {
        loadItems()
    }

    fun clearError() {
        _error.value = null
    }

    private fun applyCurrentSort(list: List<ItemEntity>): List<ItemEntity> {
        return when (currentSort) {
            SortOrder.ASC -> useCases.sortAsc(list)
            SortOrder.DESC -> useCases.sortDesc(list)
            SortOrder.NONE -> list
        }
    }
}


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
}
