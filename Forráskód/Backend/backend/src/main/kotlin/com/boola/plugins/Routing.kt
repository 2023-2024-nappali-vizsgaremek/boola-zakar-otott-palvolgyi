package com.boola.plugins

import at.favre.lib.crypto.bcrypt.BCrypt
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.boola.controllers.DataControllerFactory
import com.boola.models.Account
import com.boola.models.ExpenseList
import com.boola.models.Profile
import io.github.cdimascio.dotenv.dotenv
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import io.ktor.util.Identity.encode
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import java.security.MessageDigest
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

        authenticate("boola-auth") {
            get("/tst") {
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else{
                    call.respond(con.getDbStatus())
                    DataControllerFactory.returnController(con)
                }
            }
        }

        post("/login") {
            val user = call.receive<Account>()
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else {
                val sentPw = (con.getAccountSalt(user.email) + user.pwHash)
                    .toCharArray()
                val storedPw = con.getAccount(user.email).pwHash.toCharArray()
                println("Stored $storedPw, got $sentPw")
                val verification = BCrypt.verifyer().verify(sentPw, storedPw)
                if(!verification.verified) {
                    call.respond(HttpStatusCode.Unauthorized)
                    DataControllerFactory.returnController(con)
                }
                val secret:String = try {
                    System.getenv("JWT_SECRET")
                } catch (_:NullPointerException){
                    val env = dotenv()
                    env["JWT_SECRET"]
                }
                println("$secret is the secret")
                val token = JWT.create()
                    .withClaim("email",user.email)
                    .withExpiresAt(Date(System.currentTimeMillis() + 300000))
                    .withAudience("http://localhost:8080/login")
                    .withIssuer("http://localhost:8080")
                    .sign(Algorithm.HMAC256(secret))
                call.respond(hashMapOf("token" to token))
                DataControllerFactory.returnController(con)
            }

        }

        get("/api/account/{email}") {
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else{
                call.respond(con.getAccount(call.parameters["email"] as String))
                DataControllerFactory.returnController(con)
            }
        }

        get("/api/salt/{email}"){
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else {
                call.respond(con.getAccountSalt(call.parameters["email"] as String))
                DataControllerFactory.returnController(con)
            }
        }

        post("/register") {
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else {
                val account = call.receive<Account>()
                con.addAccount(account)
                call.respond(HttpStatusCode.Created)
                DataControllerFactory.returnController(con)
            }
        }

        put("/api/account/{email}"){
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else {
                try {
                    call.parameters["email"]?.let { con.setAccount(it,call.receive<Account>()) }
                    call.respond(HttpStatusCode.OK)
                    DataControllerFactory.returnController(con)
                } catch (e:Exception){
                    e.message?.let { print(it) }
                    call.respond(HttpStatusCode.BadRequest)
                    DataControllerFactory.returnController(con)
                }

            }
        }
        delete("/api/account/{email}")
        {
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else{
                try{
                call.parameters["email"]?.let {con.deleteAccount(con.getAccount(it))}
                call.respond(HttpStatusCode.NoContent)
                    DataControllerFactory.returnController(con)
            }
                catch(e:Exception)
                {
                    e.message?.let { print(it) }
                    call.respond(HttpStatusCode.BadRequest)
                    DataControllerFactory.returnController(con)

                }
            }
        }

        get("/api/currency") {
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else{
                call.respond(con.getCurrenciesAll())
                DataControllerFactory.returnController(con)
            }
        }

        get("/api/currency/{code}") {
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else {
                call.respond(con.getCurrency(call.parameters["code"] as String))
                DataControllerFactory.returnController(con)
            }
        }


        get("/api/category") {
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else {
                call.respond(con.getCurrenciesAll())
                DataControllerFactory.returnController(con)
            }
        }

        get("/api/category/{id}") {
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else {
                call.respond(con.getCurrenciesAll())
                DataControllerFactory.returnController(con)
            }
        }
        authenticate("boola-auth") {
            get("/api/profile/{id}"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    call.respond(con.getCurrenciesAll())
                    DataControllerFactory.returnController(con)
                }
            }
            get("/api/profile"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    call.respond(con.getCurrenciesAll())
                    DataControllerFactory.returnController(con)
                }
            }
            post("/api/profile/{id}") {
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val profile = call.receive<Profile>()
                    con.addProfile(profile)
                    call.respond(HttpStatusCode.Created)
                    DataControllerFactory.returnController(con)
                }

            }
            put("/api/profile/{id}") {
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    try {
                        call.parameters["id"]?.let { con.setProfile(UUID.fromString(it),call.receive<Profile>()) }
                        call.respond(HttpStatusCode.OK)
                        DataControllerFactory.returnController(con)
                    } catch (e:Exception){
                        e.message?.let { print(it) }
                        call.respond(HttpStatusCode.BadRequest)
                        DataControllerFactory.returnController(con)
                    }

                }
            }
            delete("/api/profile/{id}") {
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else{
                    try{
                        call.parameters["id"]?.let {con.deleteProfile(con.getProfile(UUID.fromString(it)))}
                        call.respond(HttpStatusCode.NoContent)
                        DataControllerFactory.returnController(con)
                    }
                    catch(e:Exception)
                    {
                        e.message?.let { print(it) }
                        call.respond(HttpStatusCode.BadRequest)
                        DataControllerFactory.returnController(con)

                    }
                }
            }
        }
        authenticate ("boola-auth"){
            get("/api/expenselist/{id}"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    call.respond(con.getCurrenciesAll())
                    DataControllerFactory.returnController(con)
                }
            }
            get("/api/expenselist/") {
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    call.respond(con.getCurrenciesAll())
                    DataControllerFactory.returnController(con)
                }

            }
            post("/api/expenselist/{id}") {
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val expenselist= call.receive<ExpenseList>()
                    con.addExopenseList(expenselist)
                    call.respond(HttpStatusCode.Created)
                    DataControllerFactory.returnController(con)
                }

            }

            delete("/api/profile/{id}") {
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else{
                    try{
                        call.parameters["email"]?.let {con.deleteExpenseList(con.getExpenseList(UUID.fromString(it)))}
                        call.respond(HttpStatusCode.NoContent)
                        DataControllerFactory.returnController(con)
                    }
                    catch(e:Exception)
                    {
                        e.message?.let { print(it) }
                        call.respond(HttpStatusCode.BadRequest)
                        DataControllerFactory.returnController(con)

                    }
                }
            }
        }
    }
}
