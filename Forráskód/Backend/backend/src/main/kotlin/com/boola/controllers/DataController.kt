package com.boola.controllers

import com.boola.models.Account
import com.boola.models.Currency
import com.boola.models.ExpenseList
import com.boola.models.Profile
import kotlinx.coroutines.newFixedThreadPoolContext
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.UUID

class DataController(private val connection: Connection) {

    private val getAccountStatement: PreparedStatement = connection.prepareStatement(
        "SELECT * FROM account WHERE email= ?")

    private val getAccountsStatement:PreparedStatement = connection.prepareStatement("SELECT * FROM account")

    private val addAccountStatement:PreparedStatement = connection.prepareStatement(
        "INSERT INTO account VALUE (?,?,?)")

    private val setAccountStatement:PreparedStatement = connection.prepareStatement(
        "UPDATE account SET email=?, password=?,name=? WHERE email=?")

    private val deleteAccountStatement:PreparedStatement=connection.prepareStatement(
        " DELETE FROM account WHERE emial=?")

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
        "INSERT INTO profile VAlUE (?,?,?,?,?,?,?)")

    private val setPofileStatement:PreparedStatement=connection.prepareStatement(
        "UPDATE  profile SET id=?,name=?,isBusiness=?,languageId=?,expenseListId=?,accountEmail=? WHERE id=?")

    private val deleteProfileStatement:PreparedStatement=connection.prepareStatement(
        "DELETE From profile where id=?")

    fun getDbStatus():Boolean {
        return connection.isValid(4)
    }

    fun getAccount(email: String): Account {
        getAccountStatement.setString(1, email)
        getAccountStatement.execute()
        val results = getAccountStatement.resultSet
        results.first()
        return Account(results.getString(0), results.getString(1),
            results.getString(2))
    }

    fun getAccountsAll():ArrayList<Account>{
        getAccountsStatement.execute()
        val results = getAccountsStatement.resultSet
        results.first()
        val accounts = ArrayList<Account>()
        do {
            accounts.add(Account(results.getString(0), results.getString(1),
                results.getString(2)))
        } while (results.next())
        return accounts
    }

    fun addAccount(accountToAdd:Account){
        addAccountStatement.run {
            setString(0,accountToAdd.email)
            setString(1,accountToAdd.pwHash)    //todo: check model value order and name
            setString(2,accountToAdd.name)
            execute()
        }
    }
    fun deleteAccount(accountToAdd:Account){
        deleteAccountStatement.run {
            setString(0,accountToAdd.email)
            setString(1,accountToAdd.pwHash)
            setString(2,accountToAdd.name)
            execute()
        }
    }

    fun setAccount(accountEmail:String,newData:Account){
        setAccountStatement.run {
            setString(0,newData.email)
            setString(1,newData.pwHash)
            setString(2,newData.name)
            setString(3,accountEmail)
            execute()
        }
    }

    fun getCurrency(code:String):String {
        getCurrencyStatement.setString(1,code)
        getCurrencyStatement.execute()
        val results = getCurrencyStatement.resultSet
        results.first()
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
        return Profile( UUID.fromString(results.getString(0)),results.getString(1),results.getBoolean(2),results.getString(3),UUID.fromString(results.getString(4)),results.getString(5))
    }
    fun getAllprofile():ArrayList<Profile>{
        getProfilesStatement.execute()
        val Profiles=ArrayList<Profile>()
        val results=getProfilesStatement.resultSet
        while (results.next()){
            Profiles.add(Profile(UUID.fromString(results.getString("id")),results.getString("name"),results.getBoolean("isBusiness"),results.getString("languageId"),UUID.fromString(results.getString("expenseListId")),results.getString("accountEmail")))
        }
        return Profiles
    }
    fun setProfile(id:UUID,newData:Profile){
        setPofileStatement.run {
            setObject(0,newData.id)
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
            setObject(0,newData.id)
            setString(1,newData.name)
            setBoolean(2,newData.isBusiness)
            setString(3,newData.languageId)
            setObject(4,newData.expenseListId)
            setString(5,newData.accountEmail)
            execute()
        }
    }
    fun addProfile(newData:Profile){
        addProfileStatement.run {
            setObject(0,newData.id)
            setString(1,newData.name)
            setBoolean(2,newData.isBusiness)
            setString(3,newData.languageId)
            setObject(4,newData.expenseListId)
            setString(5,newData.accountEmail)
            execute()
        }
    }



    fun getExpenseList(id: UUID):ExpenseList{
        getExpenseListStatement.setObject(0,id)
        getExpenseListStatement.execute()

        val results = getExpenseListStatement.resultSet;
        results.first()
        return ExpenseList(
            UUID.fromString(results.getString(0)),results.getLong(1),
            results.getString(2))
    }

    fun getExpenseListsAll():ArrayList<ExpenseList>{
        getExpenseListsStatement.execute()
        val lists = ArrayList<ExpenseList>()
        val results = getExpenseListsStatement.resultSet
        results.first()
        do {
            lists.add(ExpenseList(
                UUID.fromString(results.getString(0)),results.getLong(1),
                results.getString(2)))
            results.next()
        } while (!results.isLast)
        return lists
    }
fun addExopenseList(newData:ExpenseList){
    addExpenseListStatement.run {
        setObject(0,newData.id)
        setLong(1, newData.balance)
        setString(  2,newData.currencyCode)
    }
}


}