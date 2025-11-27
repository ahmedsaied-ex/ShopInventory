package com.example.shopstock.domain.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val soldLast7Days: List<Int>,
    val itemLeftInInventory: Int
){
    @Ignore
    val predictSelling= soldLast7Days.average().toInt()
}
