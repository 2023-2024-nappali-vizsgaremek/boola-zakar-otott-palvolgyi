package com.boola.controllers

import java.net.URI
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import kotlin.system.exitProcess

class DbConnector() {
    private val db: Connection

    init {
        try {
            val herokUri: URI = URI.create(System.getenv("DATABASE_URL"))
            val splitUri = herokUri.userInfo.split(':')
            val username = splitUri.first()
            val password = splitUri.last()
            db = DriverManager.getConnection(
                "jdbc:postgresql://${herokUri.host}:${herokUri.port}${herokUri.path}?sslmode=require",username,
                password)
        } catch (e:SQLException) {
            println("Postgres connection failed! Error:" + e.message + "\n exiting...")
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