package com.boola.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {
    val jwtAudience = "https://localhost:8080/login"
    val jwtDomain = "https://localhost:8080"
    val jwtRealm = "Boola login"
    val jwtSecret = try {System.getenv("JWT_SECRET") }
    catch (e:NullPointerException){
        val env = dotenv()
        env["JWT_SECRET"]
    }
    authentication {
        jwt {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtDomain)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
}
