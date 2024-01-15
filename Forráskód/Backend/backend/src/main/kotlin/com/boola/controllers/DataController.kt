package com.boola.controllers

import com.boola.models.Account
import com.boola.models.Currency
import com.boola.models.ExpenseList
import com.boola.models.Profile
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.UUID

class DataController(private val connection: Connection) {

    private val getAccountStatement: PreparedStatement = connection.prepareStatement(
        "SELECT * FROM account WHERE email= ?")
    private val getAccountsStatement:PreparedStatement = connection.prepareStatement("SELECT * FROM account")
    private val getCurrencyStatement:PreparedStatement = connection.prepareStatement(
        "SELECT name from currency WHERE code = ?")
    private val getCurrenciesStatement:PreparedStatement = connection.prepareStatement("SELECT * FROM currency")
    private val getExpenseListStatement:PreparedStatement = connection.prepareStatement(
        "SELECT * FROM expenselist WHERE id = ?")
    private val getExpenseListsStatement:PreparedStatement = connection.prepareStatement(
        "SELECT * FROM expenselist")

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

}