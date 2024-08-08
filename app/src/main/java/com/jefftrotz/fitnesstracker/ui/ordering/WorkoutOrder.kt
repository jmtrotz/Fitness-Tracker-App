package com.jefftrotz.fitnesstracker.ui.ordering

sealed class WorkoutOrder(val orderType: OrderType) {

    class Date(orderType: OrderType): WorkoutOrder(orderType)
    class Name(orderType: OrderType): WorkoutOrder(orderType)

    fun copy(orderType: OrderType): WorkoutOrder {
        return when(this) {
            is Date -> Date(orderType)
            is Name -> Name(orderType)
        }
    }
}