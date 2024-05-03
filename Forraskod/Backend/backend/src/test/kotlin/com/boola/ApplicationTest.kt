package com.boola

import at.favre.lib.crypto.bcrypt.BCrypt
import com.boola.controllers.DataController
import com.boola.controllers.DataControllerFactory
import com.boola.controllers.DbConnector
import com.boola.models.Account
import com.boola.models.Profile
import com.boola.plugins.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.util.NoSuchElementException
import kotlin.reflect.KClass
import kotlin.test.*

class ApplicationTest {

    @Test
    fun testDb() {
        val db = DbConnector()
        assertEquals(db.testConnection(),true)
    }

    @Test
    fun testController() {
        DataControllerFactory(1)
        val resp = DataControllerFactory.getController()?.getDbStatus()
        assertEquals(resp,true)
    }

    @Test
    fun testConnectionPooling() {
        val size = 5
        DataControllerFactory(size)
        for(d in 0..size) {
            val resp = DataControllerFactory.getController()?.getDbStatus()
            assertEquals(resp,true)
        }
    }

    @Test
    fun testConnectionOverload(){
        val size = 5
        DataControllerFactory(size)
        var controller:DataController? = null
        for(d in 0..size+1) {
            controller = DataControllerFactory.getController()
        }
        assert(controller == null)
    }

    @Test
    fun testGetAccounts(){
        val size = 1
        DataControllerFactory(size)
        val controller = DataControllerFactory.getController()
        val accounts = controller?.getAccountsAll()
        assert(accounts != null && accounts.size > 0)
    }

    @Test
    fun testGetCurrency(){
        val size = 1
        DataControllerFactory(size)
        val controller = DataControllerFactory.getController()
        val currency = controller?.getCurrency("HUF")
        assert(currency != null)
    }

}
