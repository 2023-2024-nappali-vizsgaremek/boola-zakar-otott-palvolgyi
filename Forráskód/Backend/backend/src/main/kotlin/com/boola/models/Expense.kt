package com.boola.models

import kotlinx.serialization.Serializable
import java.util.*


@Serializable
data class Expense(@Serializable(with = UUIDSerializer::class) val id:UUID, val name: String, val status:Boolean,
                   @Serializable(with = DateSerializer::class) val date:Date, val payeeId:Byte, val amount:Double,
                   val category:Category, val tags:String, val statException:Boolean, val note:String,
                   @Serializable(with = UUIDSerializer::class) val listId: UUID)


