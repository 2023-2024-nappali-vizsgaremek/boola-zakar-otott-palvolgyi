package com.boola.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.boola.controllers.DataControllerFactory
import com.boola.models.Account
import io.github.cdimascio.dotenv.dotenv
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.Identity.encode
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import java.util.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/tst") {
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else call.respond(con.getDbStatus())
        }

        post("/login") {
            val user = call.receive<Account>()
            val secret = try {
                System.getenv("JWT_SECRET")
            } catch (e:NullPointerException){
                val env = dotenv()
                env["JWT_SECRET"]
            }
            val token = JWT.create()
                .withClaim("email",user.email)
                .withExpiresAt(Date(System.currentTimeMillis() + 300000))
                .sign(Algorithm.HMAC256(secret))
            call.respond(hashMapOf("token" to token))
        }

        get("/api/account/{email}") {
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else call.respond(con.getAccount(call.parameters["email"] as String))
        }

        get("/api/currency") {
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else call.respond(con.getCurrenciesAll())
        }

        get("/api/currency/{code}") {
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else call.respond(con.getCurrency(call.parameters["code"] as String))
        }
    }
}
