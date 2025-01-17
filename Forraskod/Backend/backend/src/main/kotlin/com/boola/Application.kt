package com.boola

import com.boola.controllers.DataController
import com.boola.controllers.DataControllerFactory
import com.boola.controllers.DbConnector
import com.boola.models.Profile
import com.boola.plugins.*
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.util.*

fun main() {
    val port = try {
        System.getenv("PORT").toInt()
    } catch (e:NullPointerException){
        val env = dotenv()
        env["PORT"].toInt()
    }
    embeddedServer(Netty, port = port, host = "0.0.0.0", module = Application::module)
            .start(wait = true)

}

fun Application.module() {
    configureSecurity()
    configureHTTP()
    configureSerialization()
    configureRouting()
    DataControllerFactory(10)
}
