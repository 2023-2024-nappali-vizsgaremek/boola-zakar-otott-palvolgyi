package com.boola.controllers

import io.github.cdimascio.dotenv.dotenv
import java.net.URI
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import kotlin.system.exitProcess

class DbConnector {
    private val db: Connection

    init {
        try {
            val herokUri: URI = try {
                URI.create(System.getenv("DATABASE_URL"))
            } catch (e: NullPointerException) {
                val env = dotenv()
                URI.create(env["DATABASE_URL"])
            }
            val splitUri = herokUri.userInfo.split(':')
            val username = splitUri.first()
            val password = splitUri.last()
            println("jdbc:postgresql://${herokUri.host}:${herokUri.port}${herokUri.path}")
            db = DriverManager.getConnection(
                "jdbc:postgresql://${herokUri.host}:${herokUri.port}${herokUri.path}",username,
                password)
            println("Successfully connected to " + herokUri.host)
        } catch (e:SQLException) {
            error("Postgres connection failed! Error:" + e.cause + "\n exiting...")
            exitProcess(0)
        }
    }
        fun getConnection(): Connection {
            return db
        }

        fun testConnection(): Boolean {
            return db.isValid(5)
        }
    }