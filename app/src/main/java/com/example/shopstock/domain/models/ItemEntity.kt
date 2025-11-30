package com.example.shopstock.domain.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlin.math.ceil
import com.example.shopstock.helpers.State



@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val totalQuantity: Int,
    val price: Double,
    val soldLast7Days: List<Int>,
){
    @Ignore
    val itemLeftInInventory = totalQuantity - soldLast7Days.sum()

    @Ignore
    val predictSelling= ceil(soldLast7Days.average()).toInt()


    @Ignore
    val soldLast7DaysSum = soldLast7Days.sum()

    @Ignore
    val percentage : Float = 100-((soldLast7DaysSum/totalQuantity.toFloat())*100)

    @Ignore
    val state: State = when{
        percentage <20 -> State.CRITICAL
        percentage < 50 -> State.WARNING
        else -> State.STABLE
    }

    @Ignore
    val inventoryStatus: String ="${itemLeftInInventory}/${totalQuantity} (${String.format("%.1f", percentage)}%)"


}
