package com.boola.controllers

import java.sql.Connection

class DataController(private val connection: Connection) {
    fun getDbStatus():Boolean {
        return connection.isValid(4)
    }
}