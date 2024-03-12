package com.boola.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Profile(@Serializable(with = UUIDSerializer::class) val id:UUID, val name:String, val isBusiness:Boolean,
                   val languageId:String, @Serializable(with = UUIDSerializer::class) val expenseListId:UUID?,
                   val accountEmail:String)
