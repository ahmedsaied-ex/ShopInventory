package com.example.shopstock.presintation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.domain.useCases.BinarySearchByNameUseCase
import com.example.shopstock.domain.useCases.GetItemsUseCase
import com.example.shopstock.domain.useCases.MergeSortAscUseCase
import com.example.shopstock.domain.useCases.QuickSortDescUseCase
import com.example.shopstock.domain.useCases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ItemViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _items = MutableStateFlow<List<ItemEntity>>(emptyList())
    val items: StateFlow<List<ItemEntity>> = _items

    private val _searchResult = MutableStateFlow<ItemEntity?>(null)
    val searchResult: StateFlow<ItemEntity?> = _searchResult

    init {
        viewModelScope.launch {
            _items.value = useCases.getItems()
        }
    }

    fun sortAsc() {
        _items.value = useCases.mergeSortAsc(_items.value)
    }

    fun sortDesc() {
        val mutableList = _items.value.toMutableList()
        useCases.quickSortDesc(mutableList)
        _items.value = mutableList
    }

    fun searchByName(name: String) {
        _searchResult.value = useCases.binarySearchByName(_items.value, name)
    }
}
