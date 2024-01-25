package com.boola.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(val id:Int, val name: String)

/*
enum class Category {
    FOOD,
    TRAVEL,
    TRANSPORT,
    ENTERTAINMENT,
    HEALTH,
    SHOPPING,
    SERVICES,
    BILLS,
    GROCERIES,
    FINANCES,
    GENERAL
}
 */