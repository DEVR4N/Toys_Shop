package com.example.myapplication

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.widget.Toast
import com.example.myapplication.CustomerItems
import com.example.recyclerviewex.Items
import java.lang.Exception


class myDatabaseClass(context: Context)
{
    private  var sqlHelper: DatabaseHelper
    private  val context: Context
    private  var database: SQLiteDatabase

    companion object
    {
        private const val DATABASE_TABLE = "customersTable"
        const val KEY_ID = "customerID"
        const val KEY_NAME = "customerName"
        const val KEY_PASSWORD = "customerPassword"
        const val KEY_MAIL = "customerMail"
        const val KEY_PHONE = "customerPhone"
        const val KEY_ADDRESS = "customerAddress"
        const val KEY_BIRTHDATE = "customerBirthDate"

    }

    init
    {
        sqlHelper = DatabaseHelper(context)
        database = sqlHelper.writableDatabase
        this.context = context
    }

    fun verileriTemizle(): Boolean {
        return try {
            with(database) { execSQL("delete from $DATABASE_TABLE") }
            true
        } catch (e: Exception) {
            false
        }
    }
    fun openDBConnection()
    {
        sqlHelper = DatabaseHelper(context)
        database = sqlHelper.writableDatabase
    }

    fun  closeDBConnection()
    {
        sqlHelper.close()
    }

    fun insertData(CustomerItems: CustomerItems): Long
    {
        val CV = ContentValues()
        CV.put(KEY_NAME,CustomerItems.CusName)
        CV.put(KEY_PASSWORD,CustomerItems.CusPassword)
        CV.put(KEY_MAIL,CustomerItems.CusMail)
        CV.put(KEY_BIRTHDATE,CustomerItems.CusBirtdate)
        CV.put(KEY_PHONE,CustomerItems.CusPhone)
        CV.put(KEY_ADDRESS,CustomerItems.CusAddress)
        return database.insert(DATABASE_TABLE,null,CV)
    }



    fun listele():ArrayList<custom1>
    {
        val toylist:ArrayList<custom1> = ArrayList<custom1>()

        val selectQuery="SELECT * FROM $DATABASE_TABLE"

        val db=sqlHelper.readableDatabase

        var cursor: Cursor?=null

        try {
            cursor=db.rawQuery(selectQuery,null)
        }catch (e: SQLException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id:Int
        var name:String
        var password:String
        var mail:String
        var birtdate:String
        var address:String
        var phone:String



        if (cursor.moveToFirst()){
            do {
                id=cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID))
                name=cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))
                password=cursor.getString(cursor.getColumnIndexOrThrow(KEY_PASSWORD))
                mail=cursor.getString(cursor.getColumnIndexOrThrow(KEY_MAIL))
                birtdate=cursor.getString(cursor.getColumnIndexOrThrow(KEY_BIRTHDATE))
                phone=cursor.getString(cursor.getColumnIndexOrThrow(KEY_PHONE))
                address=cursor.getString(cursor.getColumnIndexOrThrow(KEY_ADDRESS))

                val toy=custom1(id,name,password,mail,birtdate,phone,address)
                toylist.add(toy)

            }while (cursor.moveToNext())

        }

        return toylist
    }


    fun contorl(name:String,pass:String,context: Context):Boolean
    {
        val columns = arrayOf(KEY_ID, KEY_NAME, KEY_PASSWORD, KEY_MAIL,
            KEY_PHONE, KEY_ADDRESS, KEY_BIRTHDATE)

        val cursor = database.query(DATABASE_TABLE,columns,null,null,null,null,null)
        var result = ""
        val col_ID = cursor.getColumnIndex(KEY_ID)
        val col_NAME = cursor.getColumnIndex(KEY_NAME)
        val col_PASSWORD = cursor.getColumnIndex(KEY_PASSWORD)
        val col_MAIL = cursor.getColumnIndex(KEY_MAIL)
        val col_PHONE = cursor.getColumnIndex(KEY_PHONE)
        val col_ADDRESS = cursor.getColumnIndex(KEY_ADDRESS)
        val col_BIRTHDATE = cursor.getColumnIndex(KEY_BIRTHDATE)
        cursor.moveToFirst()

        var valid=false
        while (!cursor.isAfterLast)
        {

            var x=cursor.getString(col_NAME)
            var y=cursor.getString(col_PASSWORD)
            if(x.equals(name) && y.equals(pass))
            {
                Toast.makeText(context,"Hello $x",Toast.LENGTH_SHORT).show()
                valid=true
                break
            }
            else {
                valid=false
            }
            cursor.moveToNext()
        }

        cursor.close()

        return valid

    }

    fun deleteCustomer(rowid:String):Int
    {
        val db=sqlHelper.writableDatabase
        var succes = db.delete(myDatabaseClass.DATABASE_TABLE, KEY_ID +"="+rowid,null).toInt()

        return  succes
    }

}