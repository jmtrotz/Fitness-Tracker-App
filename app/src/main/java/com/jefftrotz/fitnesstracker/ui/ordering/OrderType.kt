package com.jefftrotz.fitnesstracker.ui.ordering

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}