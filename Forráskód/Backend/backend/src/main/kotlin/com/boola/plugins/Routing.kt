package com.boola.plugins

import at.favre.lib.crypto.bcrypt.BCrypt
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.boola.controllers.DataControllerFactory
import com.boola.models.*
import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import java.util.*
import javax.xml.crypto.Data

private const val AccessTokenLifetime = 9000000

private const val RefreshTokenLifetime = 259200000

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        get("/") {
            call.respondText("Hello World! This is the home of Boola, the new financial app!")
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
            println("User submitted: " + user)
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else {
                val sentPw = user.pwHash.toCharArray()
                val storedPw = con.getAccount(user.email).pwHash.toCharArray()
                println(storedPw.concatToString())
                val verification = BCrypt.verifyer().verify(sentPw,storedPw)
                if(!verification.verified) {
                    call.respond(HttpStatusCode.Unauthorized)
                    DataControllerFactory.returnController(con)
                    return@post
                }
                val secret:String = try {

                    System.getenv("JWT_SECRET")
                } catch (e:NullPointerException){
                    val env = dotenv()
                    env["JWT_SECRET"]
                }

                val accessToken = JWT.create()

                    .withClaim("email",user.email)
                    .withExpiresAt(Date(System.currentTimeMillis() + AccessTokenLifetime))
                    .withAudience("https://localhost:8080/login")
                    .withIssuer("https://localhost:8080")
                    .sign(Algorithm.HMAC256(secret))
                val refreshToken = JWT.create()
                    .withClaim("email",user.email)
                    .withExpiresAt(Date(System.currentTimeMillis() + RefreshTokenLifetime))
                    .withAudience("https://localhost:8080/refresh")
                    .withIssuer("https://localhost:8080")
                    .sign(Algorithm.HMAC256(secret))
                call.respond(hashMapOf("access" to accessToken,"refresh" to refreshToken))
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
                    return@put
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


        authenticate("boola-refresh") {
           post("/refresh"){
               val email = call.receive<String>()
               val secret = try {
                   System.getenv("JWT_SECRET")
               } catch (_:NullPointerException){
                   val env = dotenv()
                   env["JWT_SECRET"]
               }
               val accessToken = JWT.create()
                   .withClaim("email",email)
                   .withExpiresAt(Date(System.currentTimeMillis() + RefreshTokenLifetime))
                   .withAudience("https://localhost:8080/refresh")
                   .withIssuer("https://localhost:8080")
                   .sign(Algorithm.HMAC256(secret))
               call.respond(hashMapOf("access" to accessToken))
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
                call.respond(con.getCategoriesAll())
                DataControllerFactory.returnController(con)
            }
        }

        get("/api/category/{id}") {
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else {
                val id = call.parameters["id"]
                if(id == null) call.respond(HttpStatusCode.BadRequest)
                else call.respond(con.getCategory(id.toInt()))
                DataControllerFactory.returnController(con)
            }
        }
        authenticate("boola-auth") {
            get("/api/profile/{id}"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val id = UUID.fromString(call.parameters["id"] as String)
                    val principal = call.principal<JWTPrincipal>()
                    val profile = con.getProfile(id)
                    if(principal!!.payload.getClaim("email").asString() == profile.accountEmail){
                        call.respond(profile)
                        DataControllerFactory.returnController(con)
                        return@get
                    }
                    call.respond(HttpStatusCode.Forbidden)
                    DataControllerFactory.returnController(con)
                }
            }
            get("/api/profile"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val principal = call.principal<JWTPrincipal>()
                    val email: String = principal!!.payload.getClaim("email").asString()
                    val profiles = con.getAllProfile(email)
                    call.respond(profiles)
                    DataControllerFactory.returnController(con)
                }
            }

            post("/api/profile") {

                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    var profile = call.receive<Profile>()
                    val ownerEmail = profile.accountEmail
                    val loginEmail = call.principal<JWTPrincipal>()!!.payload.getClaim("email").asString()
                    if(ownerEmail != loginEmail) {
                        call.respond(HttpStatusCode.Forbidden)
                        DataControllerFactory.returnController(con)
                        return@post
                    }
                    if(profile.expenseListId == UUID.fromString("00000000-0000-0000-0000-000000000000")) {
                        val expenseListId = UUID.randomUUID()
                        con.addExopenseList(ExpenseList(expenseListId,0.0,"HUF")) //send currency with request
                        profile = profile.copy(expenseListId = expenseListId)
                    }
                    con.addProfile(profile)
                    call.respond(HttpStatusCode.Created,"api/profile/" + profile.id)
                    DataControllerFactory.returnController(con)
                }

            }
            put("/api/profile/{id}") {
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    try {
                        call.parameters["id"]?.let {
                            val uuid = UUID.fromString(it)
                            val oldProfile = con.getProfile(uuid)
                            if(call.principal<JWTPrincipal>()!!.payload.getClaim("email").asString() ==
                                oldProfile.accountEmail){
                                con.setProfile(uuid,call.receive<Profile>())
                                call.respond(HttpStatusCode.OK)
                                DataControllerFactory.returnController(con)
                                return@put
                            }
                            call.respond(HttpStatusCode.Forbidden)
                        }
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
                        call.parameters["id"]?.let {
                            val uuid = UUID.fromString(it)
                            val profile = con.getProfile(uuid)
                            if(call.principal<JWTPrincipal>()!!.payload.getClaim("email").asString() ==
                                profile.accountEmail) {
                                con.deleteExpenseList(profile.expenseListId!!)
                                con.deleteProfile(profile)
                                call.respond(HttpStatusCode.NoContent)
                                DataControllerFactory.returnController(con)
                                return@delete
                            }
                            call.respond(HttpStatusCode.Forbidden)
                        }
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
                    val id = call.parameters["id"]
                    if(id == null) {
                        call.respond(HttpStatusCode.BadRequest)
                        DataControllerFactory.returnController(con)
                        return@get
                    }
                    val email = call.principal<JWTPrincipal>()!!.payload.getClaim("email").asString()
                    val uuid = UUID.fromString(id)
                    val expenseList = con.getExpenseList(uuid)
                    val owner = con.getAllProfile(email).first {
                        it.expenseListId == expenseList.id
                    }.accountEmail
                    if(email == owner){
                        call.respond(expenseList)
                        DataControllerFactory.returnController(con)
                        return@get
                    }
                    call.respond(HttpStatusCode.Forbidden)
                    DataControllerFactory.returnController(con)
                }
            }

            get("/api/expenselist/") {
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val owner = call.principal<JWTPrincipal>()!!.payload.getClaim("email").asString()
                    if(owner == null) {
                        call.respond(HttpStatusCode.BadRequest)
                        DataControllerFactory.returnController(con)
                        return@get
                    }
                    val profile = con.getAllProfile(owner).first{
                        it.accountEmail == owner
                    }.expenseListId
                    val expenseLists = con.getExpenseListsAll().filter {
                        profile == it.id
                    }
                    call.respond(expenseLists)
                    DataControllerFactory.returnController(con)
                }
            }

            put("/api/expenselist/{id}"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val idString = call.parameters["id"] as String
                    val id = UUID.fromString(idString)
                    val newExpenseList = call.receive<ExpenseList>()
                    val email = call.principal<JWTPrincipal>()!!.payload.getClaim("email").asString()
                    try{
                        con.getExpenseList(id)
                    } catch (_:Exception) {
                        call.respond(HttpStatusCode.NotFound)
                        DataControllerFactory.returnController(con)
                        return@put
                    }
                    val ownsProfile = con.getAllProfile(email).any{
                        it.expenseListId == id
                    }
                    if(!ownsProfile) {
                        call.respond(HttpStatusCode.Forbidden)
                        DataControllerFactory.returnController(con)
                        return@put
                    }
                    con.setExpenseList(id,newExpenseList)
                    call.respond(HttpStatusCode.OK)
                    DataControllerFactory.returnController(con)
                }
            }

            delete("/api/expenselist/{id}") {
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val idString = call.parameters["id"] as String
                    val id = UUID.fromString(idString)
                    val newExpenseList = call.receive<ExpenseList>()
                    val email = call.principal<JWTPrincipal>()!!.payload.getClaim("email").asString()
                    try{
                        con.getExpenseList(id)
                    } catch (_:Exception) {
                        call.respond(HttpStatusCode.NotFound)
                        DataControllerFactory.returnController(con)
                        return@delete
                    }
                    val ownsProfile = con.getAllProfile(email).any{
                        it.expenseListId == id
                    }
                    if(!ownsProfile) {
                        call.respond(HttpStatusCode.Forbidden)
                        DataControllerFactory.returnController(con)
                        return@delete
                    }
                    con.deleteExpenseList(id)
                    call.respond(HttpStatusCode.NoContent)
                    DataControllerFactory.returnController(con)
                }
            }
        }
        authenticate("boola-auth") {
            get("/api/partner"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    call.respond(con.getPartnersAll())
                    DataControllerFactory.returnController(con)
                }
            }
            get("/api/partner/{id}") {
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val idString = call.parameters["id"]
                    if(idString == null) call.respond(HttpStatusCode.NotFound)
                    else call.respond(con.getPartner(idString.toByte()))
                    DataControllerFactory.returnController(con)
                }
            }
            post("/api/partner"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val partner = call.receive<Partner>()
                    con.addPartner(partner)
                    call.respond(HttpStatusCode.Created)
                    DataControllerFactory.returnController(con)
                }
            }
            put("/api/partner/{id}"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val partner = call.receive<Partner>()
                    val idString = call.parameters["id"]
                    idString?.let {
                        call.respond(HttpStatusCode.OK)
                        con.setPartner(partner,it.toByte())
                        DataControllerFactory.returnController(con)
                        return@put
                    }
                    call.respond(HttpStatusCode.NotFound)
                    DataControllerFactory.returnController(con)
                }
            }
            delete("/api/partner/{id}"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val idString = call.parameters["id"]
                    if(idString == null) call.respond(HttpStatusCode.NotFound)
                    else {
                        con.deletePartner(idString.toByte())
                        call.respond(HttpStatusCode.NoContent)
                    }
                    DataControllerFactory.returnController(con)
                }
            }
        }

        authenticate("boola-auth") {
            get("api/expense/{id}"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val idString = call.parameters["id"]
                    if(idString == null) call.respond(HttpStatusCode.NotFound)
                    else {
                        val id = UUID.fromString(idString)
                        val expense = con.getExpense(id)
                        val email = call.principal<JWTPrincipal>()!!.payload.getClaim("email").asString()
                        val ownsExpense = con.getAllProfile(email).any {
                            it.expenseListId == expense.listId
                        }
                        if(!ownsExpense) call.respond(HttpStatusCode.Forbidden)
                        else {
                            call.respond(expense)
                        }
                    }
                    DataControllerFactory.returnController(con)
                }
            }

            get("api/expense"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val expenseListId = UUID.fromString(call.request.queryParameters["listId"])
                    val email = call.principal<JWTPrincipal>()!!.payload.getClaim("email").asString()
                    val ownsExpenseList = con.getAllProfile(email).any{
                        it.expenseListId == expenseListId
                    }
                    if(!ownsExpenseList) {
                        call.respond(HttpStatusCode.Forbidden)
                        DataControllerFactory.returnController(con)
                        return@get
                    }
                    val expenses = con.getExpensesAll(expenseListId)
                    call.respond(expenses)
                    DataControllerFactory.returnController(con)
                }
            }

            post("api/expense"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val expenseToAdd = call.receive<Expense>()
                    val email = call.principal<JWTPrincipal>()!!.payload.getClaim("email").asString()
                    val hasOwnerProfile = con.getAllProfile(email).any {
                        it.expenseListId == expenseToAdd.listId
                    }
                    if(!hasOwnerProfile) {
                        call.respond(HttpStatusCode.Forbidden)
                        DataControllerFactory.returnController(con)
                        return@post
                    }
                    con.addExpense(expenseToAdd)
                    call.respond(HttpStatusCode.Created,"api/get/expense/" + expenseToAdd.id)
                    DataControllerFactory.returnController(con)
                }
            }

            put("api/expense/{id}"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val id = UUID.fromString(call.parameters["id"] as String)
                    val expenseToSet = call.receive<Expense>()
                    val email = call.principal<JWTPrincipal>()!!.payload.getClaim("email").asString()
                    val ownsExpense = con.getAllProfile(email).any{
                        it.expenseListId == expenseToSet.listId
                    }
                    val expenseExists = con.getExpensesAll(expenseToSet.listId).any {
                        it.id == expenseToSet.id
                    }
                    if(!ownsExpense){
                        call.respond(HttpStatusCode.Forbidden)
                        DataControllerFactory.returnController(con)
                        return@put
                    }
                    if(!expenseExists) {
                        call.respond(HttpStatusCode.NotFound)
                        DataControllerFactory.returnController(con)
                        return@put
                    }
                    con.setExpense(id,expenseToSet)
                    call.respond(HttpStatusCode.OK)
                    DataControllerFactory.returnController(con)
                }
            }

            delete("api/expense/{id}"){
                val con = DataControllerFactory.getController()
                if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
                else {
                    val id = UUID.fromString(call.parameters["id"] as String)
                    val expenseToDelete = con.getExpense(id)
                    val email = call.principal<JWTPrincipal>()!!.payload.getClaim("email").asString()
                    val ownsExpense = con.getAllProfile(email).any{
                        it.expenseListId == expenseToDelete.listId
                    }
                    val hasExpense = con.getExpensesAll(expenseToDelete.listId).any{
                        it.id == expenseToDelete.id
                    }
                    if(!ownsExpense){
                        call.respond(HttpStatusCode.Forbidden)
                        DataControllerFactory.returnController(con)
                        return@delete
                    }
                    if(!hasExpense){
                        call.respond(HttpStatusCode.NotFound)
                        DataControllerFactory.returnController(con)
                        return@delete
                    }
                    con.deleteExpense(expenseToDelete.id)
                    call.respond(HttpStatusCode.NoContent)
                    DataControllerFactory.returnController(con)
                }
            }
        }

        get("api/language/{code}"){
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else {
                val code = call.parameters["code"]
                if(code == null){
                    call.respond(HttpStatusCode.NotFound)
                    DataControllerFactory.returnController(con)
                    return@get
                }
                val language = con.getLanguage(code)
                call.respond(language)
                DataControllerFactory.returnController(con)
            }
        }

        get("api/language"){
            val con = DataControllerFactory.getController()
            if(con == null) call.respond(HttpStatusCode.ServiceUnavailable)
            else {
                val languages = con.getLanguages()
                call.respond(languages)
                DataControllerFactory.returnController(con)
            }
        }
    }
}
