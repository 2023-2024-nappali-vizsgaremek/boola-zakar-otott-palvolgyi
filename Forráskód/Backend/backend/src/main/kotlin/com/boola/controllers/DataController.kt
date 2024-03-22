package com.boola.controllers

import at.favre.lib.crypto.bcrypt.BCrypt
import com.boola.models.*
import io.ktor.utils.io.charsets.*
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLType
import java.sql.Types
import java.util.UUID
import kotlin.random.Random
import kotlin.text.StringBuilder

class DataController internal constructor(private val connection: Connection) {

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
    private val getCategoryStatement:PreparedStatement = connection.prepareStatement(
        "SELECT name from category WHERE id = ?")
    private val getCategoriesStatement:PreparedStatement = connection.prepareStatement("SELECT * FROM category")

    private val getExpenseListStatement:PreparedStatement = connection.prepareStatement(
        "SELECT * FROM expenselist WHERE id = ?")

    private val getExpenseListsStatement:PreparedStatement = connection.prepareStatement(
        "SELECT * FROM expenselist")
    private val addExpenseListStatement:PreparedStatement=connection.prepareStatement(
    "INSERT INTO expenseList (id,balance,currencycode) VALUES (?,?,?)")
    private val deleteExpenseListStatement:PreparedStatement=connection.prepareStatement(
        "DELETE  FROM expenselist WHERE id=?")

    private val getProfileStatement:PreparedStatement =connection.prepareStatement(
        "SELECT * FROM profile WHERE id=?::uuid")

    private val getProfilesStatement:PreparedStatement=connection.prepareStatement("SELECT * FROM  profile WHERE" +
            " accountEmail=?")

    private val addProfileStatement:PreparedStatement=connection.prepareStatement(
        "INSERT INTO profile (name, isbusiness, expenselistid, languagecode, accountemail)" +
                " VAlUES (?,?,?::uuid,?,?)")

    private val setProfileStatement:PreparedStatement=connection.prepareStatement(
        "UPDATE  profile SET name=?,isBusiness=?,languagecode=?,expenseListId=?::uuid,accountEmail=? WHERE id=?::uuid")
    private val deleteProfileStatement:PreparedStatement=connection.prepareStatement(
        "DELETE From profile where id=?::uuid")
    private val getPartnersStatement:PreparedStatement = connection.prepareStatement(
        "SELECT * FROM partner")
    private val getPartnerStatement:PreparedStatement = connection.prepareStatement(
        "SELECT * FROM partner WHERE id=?")
    private val addPartnerStatement:PreparedStatement = connection.prepareStatement(
        "INSERT INTO partner (id,name) VALUES (?,?)")
    private val setPartnerStatement:PreparedStatement = connection.prepareStatement(
    "UPDATE partner SET name=? WHERE= id=?")
    private val deletePartnerStatement:PreparedStatement = connection.prepareStatement(
    "DELETE FROM partner WHERE id=?")

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
            for(i in 0..15){
                val code = Random.nextInt(1, Byte.MAX_VALUE.toInt())
                println("Adding a $code to the salt")
                salt.append(Char(code))
            }
            val hashedPwArray = BCrypt.withDefaults().hash(6,salt.toString().toByteArray(),accountToAdd.pwHash
                .toByteArray())
            setString(2,String(hashedPwArray,Charset.forName("utf-8")))
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
    fun getAllProfile(email: String):ArrayList<Profile>{
        getProfilesStatement.setString(1,email)
        getProfilesStatement.execute()
        val profiles=ArrayList<Profile>()
        val results=getProfilesStatement.resultSet
        while (results.next()){
            profiles.add(Profile(UUID.fromString(
                results.getString("id")),
                results.getString("name"),
                results.getBoolean("isbusiness"),
                results.getString("languagecode"),
                UUID.fromString(results.getString("expenselistid")),
                results.getString("accountemail")))
        }
        return profiles
    }
    fun setProfile(id:UUID,newData:Profile){
        setProfileStatement.run {
            setString(1,newData.name)
            setBoolean(2,newData.isBusiness)
            setString(3,newData.languageId)
            setObject(4,newData.expenseListId)
            setString(5,newData.accountEmail)
            setObject(6,id)
            execute()
        }
    }
    fun deleteProfile(newData: Profile){
        deleteProfileStatement.run {
            setString(3,newData.name)
            setBoolean(4,newData.isBusiness)
            setString(5,newData.languageId)
            setObject(6,newData.expenseListId)
            setString(7,newData.accountEmail)
            execute()
        }
    }
    fun addProfile(newData:Profile){
        addProfileStatement.run {
            setString(1,newData.name)
            setBoolean(2,newData.isBusiness)
            setObject(3,newData.expenseListId, Types.OTHER)
            setString(4,newData.languageId)
            setString(5,newData.accountEmail)
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
        }
        return lists
    }

fun addExopenseList(newData:ExpenseList){
    addExpenseListStatement.run {
        setObject(1,newData.id)
        setLong(2, newData.balance)
        setString(  3,newData.currencyCode)
        execute()
    }

}
    fun  deleteExpenseList(newData: UUID){
        deleteExpenseListStatement.run {
            setObject(1,newData)

            execute()
        }
    }


    fun getCategory(id:Int):String {
        getCategoryStatement.setInt(1,id)
        getCategoryStatement.execute()
        val results = getCategoryStatement.resultSet

        results.next()
        return results.getString(1)
    }

    fun getCategoriesAll():ArrayList<Category> {
        getCategoriesStatement.execute()
        val categories = ArrayList<Category>()
        val results = getCurrenciesStatement.resultSet
        while (results.next()){
            categories.add(Category(results.getInt("id"),results.getString("name")))
        }
        return categories
    }

    fun getPartnersAll():ArrayList<Partner>{
        getPartnersStatement.execute()
        val partners = ArrayList<Partner>()
        val results = getPartnersStatement.resultSet
        while (results.next()){
            partners.add(Partner(results.getByte(1),results.getString(2)))
        }
        return partners
    }

    fun getPartner(id:Byte):Partner{
        getPartnerStatement.setByte(1,id)
        val results = getPartnerStatement.resultSet
        results.next()
        return Partner(results.getByte(1),results.getString(2))
    }

    fun addPartner(partnerToAdd:Partner){
        addPartnerStatement.run {
            setByte(1,partnerToAdd.id)
            setString(2,partnerToAdd.name)
            execute()
        }
    }

    fun setPartner(newData:Partner,id:Byte){
        setPartnerStatement.run{
            setString(1,newData.name)
            setByte(2,id)
            execute()
        }
    }
    fun deletePartner(id:Byte){
        deletePartnerStatement.setByte(1,id)
        deletePartnerStatement.execute()
    }

}