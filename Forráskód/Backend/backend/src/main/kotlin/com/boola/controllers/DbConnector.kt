package com.boola.controllers

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import kotlin.system.exitProcess

class DbConnector(dbLocation: String) {
    private val db: Connection

    init {
        try {
            db = DriverManager.getConnection("jdbc:mariadb://$dbLocation:3306/boola?user=root&pw=")
        } catch (e:SQLException) {
            println("MySQL connection failed! Error:" + e.message + "\n exiting...")
            exitProcess(0)
        }
    }

    fun getConnection():Connection{
        return db;
    }

    fun testConnection():Boolean{
        return db.isValid(5)
    }
}