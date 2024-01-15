package com.boola.models

import kotlinx.serialization.Serializable

@Serializable
data class Account(val email: String, val pwHash:String, val name:String)
