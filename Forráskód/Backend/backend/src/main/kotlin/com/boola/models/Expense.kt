package com.boola.models

import java.util.Date
import java.util.UUID

data class Expense(val id:UUID, val name: String, val status:Boolean, val date:Date, val payeeId:Byte, val amount:Double)
