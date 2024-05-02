package com.boola.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ExpenseList(@Serializable(with = UUIDSerializer::class) val id: UUID, val balance: Double,
                       val currencyCode: String)
