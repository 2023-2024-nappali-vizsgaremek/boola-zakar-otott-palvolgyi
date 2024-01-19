package com.boola.controllers

import at.favre.lib.crypto.bcrypt.BCrypt
import com.boola.models.Account
import com.boola.models.Currency
import com.boola.models.ExpenseList
import com.boola.models.Profile
import io.ktor.util.*
import java.security.MessageDigest
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.UUID
import kotlin.random.Random
import kotlin.text.StringBuilder
import kotlin.text.toCharArray

class DataController(private val connection: Connection) {

    private val getAccountStatement: PreparedStatement = connection.prepareStatement(
        "SELECT * FROM account WHERE email= ?")

    private val getAccountsStatement:PreparedStatement = connection.prepareStatement("SELECT * FROM account")

    private val addAccountStatement:PreparedStatement = connection.prepareStatement(
        "INSERT INTO account (email, passwordhash, name, salt) VALUES (?,?,?,?)")
    private val setAccountStatement:PreparedStatement = connection.prepareStatement(
        "UPDATE account SET email=?, passwordHash=?,name=? WHERE email=?")
    private val deleteAccountStatement:PreparedStatement=connection.prepareStatement(
        " DELETE FROM account WHERE email=?")
    private val getAccountSaltStatement:PreparedStatement=connection.prepareStatement(
        "SELECT salt from account WHERE email=?")
    private val getCurrencyStatement:PreparedStatement = connection.prepareStatement(
        "SELECT name from currency WHERE code = ?")

    private val getCurrenciesStatement:PreparedStatement = connection.prepareStatement("SELECT * FROM currency")

    private val getExpenseListStatement:PreparedStatement = connection.prepareStatement(
        "SELECT * FROM expenselist WHERE id = ?")

    private val getExpenseListsStatement:PreparedStatement = connection.prepareStatement(
        "SELECT * FROM expenselist")
    private val addExpenseListStatement:PreparedStatement=connection.prepareStatement(
    "INSERT INTO expenseList VALUE (?,?,?)")
    private val deleteExpenseListStatement:PreparedStatement=connection.prepareStatement(
        "DELETE  FROM expenselist WHERE id=?")

    private val getProfileStatement:PreparedStatement =connection.prepareStatement(
        "SELECT * FROM profile WHERE id=?")

    private val getProfilesStatement:PreparedStatement=connection.prepareStatement("SELECT * FROM  profile")

    private val addProfileStatement:PreparedStatement=connection.prepareStatement(

        "INSERT INTO profile (id, name, isbusiness, expenselistid, languagecode, accountemail)" +
                " VAlUES (?,?,?,?,?,?)")
    private val setProfileStatement:PreparedStatement=connection.prepareStatement(
        "UPDATE  profile SET id=?,name=?,isBusiness=?,languagecode=?,expenseListId=?,accountEmail=? WHERE id=?")
    private val deleteProfileStatement:PreparedStatement=connection.prepareStatement(
        "DELETE From profile where id=?")

    fun getDbStatus():Boolean {
        return connection.isValid(4)
    }

    fun getAccount(email: String): Account {
        getAccountStatement.setString(1, email)
        getAccountStatement.execute()
        val results = getAccountStatement.resultSet
        results.next()
        return Account(results.getString(1), results.getString(2),
            results.getString(3))
    }

    fun getAccountsAll():ArrayList<Account>{
        getAccountsStatement.execute()
        val results = getAccountsStatement.resultSet
        val accounts = ArrayList<Account>()
        while (results.next()) {
            accounts.add(Account(results.getString(1), results.getString(2),
                results.getString(3)))
        }
        return accounts
    }

    fun addAccount(accountToAdd:Account){
        addAccountStatement.run {
            setString(1,accountToAdd.email)
            val salt = StringBuilder()
            for(i in 0..8){
                val code = Random.nextInt(0, UShort.MAX_VALUE.toInt())
                println("Adding a $code to the salt")
                salt.append(Char(code))
            }
            /*val hashedPwBytes = MessageDigest.getInstance("SHA-256").digest((salt.append(accountToAdd.pwHash)
                .toString().toByteArray()))
            val hashedPwString:StringBuilder = StringBuilder()*/
            val hashedPwString = BCrypt.withDefaults().hashToString(6,(salt.toString() + accountToAdd.pwHash)
                .toCharArray())
            setString(2,hashedPwString.toString())
            setString(3,accountToAdd.name)
            setString(4,salt.toString())
            execute()
        }
    }
    fun deleteAccount(accountToAdd:Account){
        deleteAccountStatement.run {
            setString(1,accountToAdd.email)
            setString(2,accountToAdd.pwHash)
            setString(3,accountToAdd.name)
            execute()
        }
    }

    fun setAccount(accountEmail:String,newData:Account){
        setAccountStatement.run {
            setString(1,newData.email)
            setString(2,newData.pwHash)
            setString(3,newData.name)
            setString(4,accountEmail)
            execute()
        }
    }

    fun getAccountSalt(accountEmail: String): String {
        getAccountSaltStatement.setString(1, accountEmail)
        getAccountSaltStatement.execute()
        val results = getAccountSaltStatement.resultSet
        results.next()
        return if(results.isLast)
            results.getString(1)
        else ""
    }

    fun getCurrency(code:String):String {
        getCurrencyStatement.setString(1,code)
        getCurrencyStatement.execute()
        val results = getCurrencyStatement.resultSet
        results.next()
        return results.getString(1)
    }

    fun getCurrenciesAll():ArrayList<Currency> {
        getCurrenciesStatement.execute()
        val currencies = ArrayList<Currency>()
        val results = getCurrenciesStatement.resultSet
        while (results.next()){
            currencies.add(Currency(results.getString("code"),results.getString("name")))
        }
        return currencies
    }
    fun getProfile(id:UUID):Profile{
        getProfileStatement.setObject(1,id)
        getProfileStatement.execute()
        val results=getProfileStatement.resultSet
        results.next()
        return Profile( UUID.fromString(results.getString(1)),results.getString(2),
            results.getBoolean(3),results.getString(4),
            UUID.fromString(results.getString(5)),results.getString(6))
    }
    fun getAllProfile():ArrayList<Profile>{
        getProfilesStatement.execute()
        val Profiles=ArrayList<Profile>()
        val results=getProfilesStatement.resultSet
        while (results.next()){
            Profiles.add(Profile(UUID.fromString(results.getString("id")),
                results.getString("name"),results.getBoolean("isBusiness"),
                results.getString("languageId"),
                UUID.fromString(results.getString("expenseListId")),
                results.getString("accountEmail")))
        }
        return Profiles
    }
    fun setProfile(id:UUID,newData:Profile){
        setProfileStatement.run {
            setObject(1,newData.id)
            setString(2,newData.name)
            setBoolean(3,newData.isBusiness)
            setString(4,newData.languageId)
            setObject(5,newData.expenseListId)
            setString(6,newData.accountEmail)
            setObject(7,id)
            execute()
        }
    }
    fun deleteProfile(newData: Profile){
        deleteProfileStatement.run {
            setObject(1,newData.id)
            setString(2,newData.name)
            setBoolean(3,newData.isBusiness)
            setString(4,newData.languageId)
            setObject(5,newData.expenseListId)
            setString(6,newData.accountEmail)
            execute()
        }
    }
    fun addProfile(newData:Profile){
        addProfileStatement.run {
            setObject(1,newData.id)
            setString(2,newData.name)
            setBoolean(3,newData.isBusiness)
            setString(4,newData.languageId)
            setObject(5,newData.expenseListId)
            setString(6,newData.accountEmail)
            execute()
        }
    }



    fun getExpenseList(id: UUID):ExpenseList{
        getExpenseListStatement.setObject(1,id)
        getExpenseListStatement.execute()

        val results = getExpenseListStatement.resultSet
        return ExpenseList(
            UUID.fromString(results.getString(1)),results.getLong(2),
            results.getString(3))
    }

    fun getExpenseListsAll():ArrayList<ExpenseList>{
        getExpenseListsStatement.execute()
        val lists = ArrayList<ExpenseList>()
        val results = getExpenseListsStatement.resultSet
        while(results.next()) {
            lists.add(ExpenseList(
                UUID.fromString(results.getString(1)),results.getLong(2),
                results.getString(3)))
            results.next()
        }
        return lists
    }
fun addExopenseList(newData:ExpenseList){
    addExpenseListStatement.run {
        setObject(0,newData.id)
        setLong(1, newData.balance)
        setString(  2,newData.currencyCode)
        execute()
    }

}
    fun  deleteExpenseList(newData: ExpenseList){
        addExpenseListStatement.run {
            setObject(0,newData.id)
            setLong(1, newData.balance)
            setString(  2,newData.currencyCode)
            execute()
        }
    }


}