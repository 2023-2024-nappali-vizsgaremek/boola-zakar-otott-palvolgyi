package com.boola.models

import kotlinx.serialization.Serializable

@Serializable
data class Currency(val code: String, val name: String)