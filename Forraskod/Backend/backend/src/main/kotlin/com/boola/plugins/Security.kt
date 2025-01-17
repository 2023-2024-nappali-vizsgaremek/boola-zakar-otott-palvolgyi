package com.boola.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.github.cdimascio.dotenv.dotenv
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configureSecurity() {
    val jwtAudience = "https://localhost:8080/login"
    val jwtDomain = "https://localhost:8080"
    val jwtRealm = "Boola-public"

    val jwtSecret:String = try {
        System.getenv("JWT_SECRET")
    } catch (_:NullPointerException){
        val env = dotenv()
        env["JWT_SECRET"]
    }

    authentication {
       jwt("boola-auth") {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtDomain)
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim("email").asString() != "")
                    JWTPrincipal(credential.payload)
                else null
            }

            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized,"The token was incorrect")
            }
        }
        jwt("boola-refresh") {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience("$jwtDomain/refresh")
                    .withIssuer(jwtDomain)
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim("email").asString() != "")
                    JWTPrincipal(credential.payload)
                else null
            }

            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized,"The token was incorrect")
            }
        }
    }
}
