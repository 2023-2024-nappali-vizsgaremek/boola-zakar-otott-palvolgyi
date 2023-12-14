package com.boola

import com.boola.controllers.DataController
import com.boola.controllers.DataControllerFactory
import com.boola.controllers.DbConnector
import com.boola.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import java.util.NoSuchElementException
import kotlin.reflect.KClass
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }

    @Test
    fun testDb() {
        val db = DbConnector("localhost")
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

}
