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
    fun testRoot() = testApplication {
        application {
            configureSecurity()
            configureSerialization()
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }

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

    @Serializable
    data class JsonWebToken (val access:String,val refresh:String)

    @Test
    fun testLogin(){
        testApplication {
            application {
                configureSecurity()
                configureRouting()
                configureHTTP()
                configureSerialization()
                DataControllerFactory(10)
            }
            val body = Json.encodeToJsonElement<Account>(
                Account(
                    "otottkovi@hotmail.com", "password",
                    "Tomika"
                )
            ).toString()
            val regResp = client.post("/register"){
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            assertEquals(regResp.status, HttpStatusCode.Created)
            var resp = client.post("/login"){
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            println("y:" + resp.bodyAsText())
            val token = Json.decodeFromString<JsonWebToken>(resp.bodyAsText())
            resp = client.get("/tst"){
                header("Authorization","Bearer " + token.access)
            }
            assertEquals(HttpStatusCode.OK,resp.status)
        }
    }

    @Test
    fun testProfile(){
        testApplication {
            application {
                configureSecurity()
                configureRouting()
                configureHTTP()
                configureSerialization()
                DataControllerFactory(10)
            }
            val body = Json.encodeToJsonElement<Account>(
                Account(
                    "otottkovi@hotmail.com", "password",
                    "Tomika"
                )
            ).toString()
            var resp = client.post("/login") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            assertEquals(HttpStatusCode.OK, resp.status)
            val authToken = Json.decodeFromString<JsonWebToken>(resp.bodyAsText())
            resp = client.get("/api/profile") {
                header("Authorization", "Bearer " + authToken.access)
            }
            assertEquals(HttpStatusCode.OK, resp.status)
            val profiles = Json.decodeFromString<List<Profile>>(resp.bodyAsText())
            assert(profiles.isNotEmpty())
        }
    }

}
