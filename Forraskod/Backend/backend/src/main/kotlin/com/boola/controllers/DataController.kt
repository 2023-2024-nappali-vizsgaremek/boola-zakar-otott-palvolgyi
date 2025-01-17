package com.boola.controllers

import at.favre.lib.crypto.bcrypt.BCrypt
import com.boola.models.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.charsets.*
import java.sql.*
import java.util.UUID
import kotlin.math.exp
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

    private val getExpenseStatement:PreparedStatement = connection.prepareStatement("SELECT * FROM expense WHERE " +
            "id = ?")
    private val getExpensesStatement = connection.prepareStatement("SELECT * FROM expense WHERE listid = ?")

    private val addExpenseStatement:PreparedStatement = connection.prepareStatement("INSERT INTO expense " +
            "(id,title,category,exceptstats,tags,notes,status,date,payeeid,amount,listid) VALUES " +
            "(?,?,?,?,?,?,?,?,?,?,?)")
    private val setExpenseStatement = connection.prepareStatement("UPDATE expense SET title=?,category=?," +
            "exceptstats=?,tags=?,notes=?,status=?,date=?,payeeid=?,amount=? WHERE id=?")
    private val deleteExpenseStatement = connection.prepareStatement("DELETE FROM expense WHERE id=?")

    private val getExpenseListStatement:PreparedStatement = connection.prepareStatement(
        "SELECT * FROM expenselist WHERE id = ?")

    private val getExpenseListsStatement:PreparedStatement = connection.prepareStatement(
        "SELECT * FROM expenselist")
    private val addExpenseListStatement:PreparedStatement=connection.prepareStatement(
    "INSERT INTO expenseList (id,balance,currencycode) VALUES (?,?,?)")
    private val setExpenseListStatement:PreparedStatement = connection.prepareStatement("UPDATE expenselist SET id=?," +
            "balance=?,currencycode=? WHERE id=?")
    private val deleteExpenseListStatement:PreparedStatement=connection.prepareStatement(
        "DELETE  FROM expenselist WHERE id=?")

    private val getProfileStatement:PreparedStatement =connection.prepareStatement(
        "SELECT * FROM profile WHERE id=?::uuid")

    private val getProfilesStatement:PreparedStatement=connection.prepareStatement("SELECT * FROM  profile WHERE" +
            " accountEmail=?")

    private val addProfileStatement:PreparedStatement=connection.prepareStatement(
        "INSERT INTO profile (id,name, isbusiness, expenselistid, languagecode, accountemail)" +
                " VAlUES (?::uuid,?,?,?::uuid,?,?)")

    private val setProfileStatement:PreparedStatement=connection.prepareStatement(
        "UPDATE  profile SET name=?,isBusiness=?,languagecode=?,expenseListId=?::uuid,accountEmail=? WHERE " +
                "id=?::uuid")
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
    private val getLanguagesStatement:PreparedStatement = connection.prepareStatement("SELECT * FROM language")
    private val getLanguageStatement:PreparedStatement = connection.prepareStatement(
        "SELECT * FROM language WHERE code=?")

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
        deleteAccountStatement.setString(1,accountToAdd.email)
        deleteAccountStatement.execute()
    }

    fun setAccount(accountEmail:String,newData:Account){
        val salt = getAccountSalt(accountEmail)
        val hashedPwArray = BCrypt.withDefaults().hash(6,salt.toString().toByteArray(),newData.pwHash
            .toByteArray())
        setAccountStatement.run {
            setString(1,newData.email)
            setString(2,String(hashedPwArray, charset("utf-8")))
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
        return Profile( UUID.fromString(results.getString("id")),results.getString("name"),
            results.getBoolean("isbusiness"),results.getString("languagecode"),
            UUID.fromString(results.getString("expenselistid")),results.getString("accountemail"))
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
    fun deleteProfile(id:UUID){
        deleteProfileStatement.run {
            setObject(1,id)
            execute()
        }
    }
    fun addProfile(newData:Profile){
        addProfileStatement.run {
            setObject(1,newData.id)
            setString(2,newData.name)
            setBoolean(3,newData.isBusiness)
            setObject(4,newData.expenseListId)
            setString(5,newData.languageId)
            setString(6,newData.accountEmail)
            execute()
        }
    }



    fun getExpenseList(id: UUID):ExpenseList{
        getExpenseListStatement.setObject(1,id)
        getExpenseListStatement.execute()

        val results = getExpenseListStatement.resultSet
        results.next()
        return ExpenseList(
            UUID.fromString(results.getString(1)),results.getDouble(2),
            results.getString(3))
    }

    fun getExpenseListsAll():ArrayList<ExpenseList>{
        getExpenseListsStatement.execute()
        val lists = ArrayList<ExpenseList>()
        val results = getExpenseListsStatement.resultSet
        while(results.next()) {
            lists.add(ExpenseList(
                UUID.fromString(results.getString(1)),results.getDouble(2),
                results.getString(3)))
        }
        return lists
    }

fun addExopenseList(newData:ExpenseList){
    addExpenseListStatement.run {
        setObject(1,newData.id)
        setDouble(2, newData.balance)
        setString(3,newData.currencyCode)
        execute()
    }

}

    fun setExpenseList(id:UUID,newData: ExpenseList){
        setExpenseListStatement.run{
            setObject(1,newData.id)
            setDouble(2,newData.balance)
            setString(3,newData.currencyCode)
            setObject(4,id)
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
        val results = getCategoriesStatement.resultSet
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

    fun getExpense(id:UUID):Expense{
        getExpenseStatement.setObject(1,id)
        getExpenseStatement.execute()
        val results = getExpenseStatement.resultSet
        results.next()
        return makeExpense(results)
    }

    fun getExpensesAll(listId:UUID):ArrayList<Expense>{
        getExpensesStatement.setObject(1,listId)
        getExpensesStatement.execute()
        val results = getExpensesStatement.resultSet
        val expenses = ArrayList<Expense>()
        while(results.next()){
            expenses.add(makeExpense(results))
        }
        return expenses
    }

    fun addExpense(expenseToAdd:Expense){
        addExpenseStatement.run{
            setObject(1,expenseToAdd.id)
            setString(2,expenseToAdd.name)
            setInt(3,expenseToAdd.categoryId)
            setBoolean(4,expenseToAdd.statException)
            setString(5,expenseToAdd.tags)
            setString(6,expenseToAdd.note)
            setBoolean(7,expenseToAdd.status)
            setDate(8,Date(expenseToAdd.date.time))
            setInt(9,expenseToAdd.payeeId.toInt())
            setDouble(10,expenseToAdd.amount)
            setObject(11,expenseToAdd.listId)
            execute()
        }
    }
    fun setExpense(id:UUID,newExpense:Expense){
        setExpenseStatement.run{
            setString(1,newExpense.name)
            setInt(2,newExpense.categoryId)
            setBoolean(3,newExpense.statException)
            setString(4,newExpense.tags)
            setString(5,newExpense.note)
            setBoolean(6,newExpense.status)
            setDate(7,Date(newExpense.date.time))
            setInt(8,newExpense.payeeId.toInt())
            setDouble(9,newExpense.amount)
            setObject(10,newExpense.id)
            execute()
        }
    }

    fun deleteExpense(id:UUID){
        deleteExpenseStatement.setObject(1,id)
        deleteExpenseStatement.execute()
    }


    private fun makeExpense(results: ResultSet): Expense {
        return Expense(
            results.getObject("id") as UUID, results.getString("title"),
            results.getBoolean("status"), results.getDate("date"),
            results.getByte("payeeid"), results.getDouble("amount"),
            results.getInt("category"), results.getString("tags"),
            results.getBoolean("exceptstats"), results.getString("notes"),
            results.getObject("listid") as UUID
        )
    }

    fun getLanguage(code:String):Language{
        getLanguageStatement.setString(1,code)
        getLanguageStatement.execute()
        val results = getLanguageStatement.resultSet
        results.next()
        return Language(results.getString("code"),results.getString("name"))
    }

    fun getLanguages():ArrayList<Language>{
        getLanguagesStatement.execute()
        val results = getLanguagesStatement.resultSet
        val languages:ArrayList<Language> = ArrayList()
        while (results.next()){
            languages.add(Language(results.getString("code"),results.getString("name")))
        }
        return languages
    }

}