package com.example.myapplication


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.example.myapplication.DBHelper
import com.example.recyclerviewex.Items
import java.lang.Exception



class MyDB(context: Context) {
    private var dbHelper: DBHelper
    private val context: Context
    private var database: SQLiteDatabase

    private lateinit var updateName: String
    private lateinit var updateSurname: String


    companion object {
        const val KEY_ID = "_id"
        const val KEY_NAME = "_toyname"
        const val KEY_AGE = "_toyage"
        const val KEY_IMAGE = "_toymage"
        const val KEY_PRICE = "_toyprıce"
        private const val DATABASE_NAME = "toytable"
        private const val DATABASE_TABLE = "Toydatabase"
    }

    init {
        dbHelper = DBHelper(context)
        database = dbHelper.writableDatabase
        this.context = context
    }

    fun openDatabaseConnection() {
        dbHelper = DBHelper(context)
        database = dbHelper.writableDatabase
    }

    fun closeDatabaseConnection() {
        dbHelper.close()
    }

    fun clearAllRecords(): Boolean {
        return try {
            with(database) { execSQL("delete from $DATABASE_TABLE") }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun insertRecords(item:Items): Long {
        val cv = ContentValues()
        cv.put(KEY_IMAGE,item.image)
        cv.put(KEY_NAME,item.toy_name)
        cv.put(KEY_AGE, item.toy_age)
        cv.put(KEY_PRICE,item.toy_price)
        return database.insert(DATABASE_TABLE, null, cv)
    }



    fun LoadData():ArrayList<Items>
    {
        val toylist:ArrayList<Items> = ArrayList<Items>()

        val selectQuery="SELECT * FROM $DATABASE_TABLE"

        val db=dbHelper.readableDatabase

        var cursor: Cursor?=null

        try {
            cursor=db.rawQuery(selectQuery,null)
        }catch (e: SQLException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var image:Int
        var toyName:String
        var toyAge:String
        var toyPrice:String
        var x:Int

        if (cursor.moveToFirst()){
            do {
                image=cursor.getInt(cursor.getColumnIndexOrThrow(KEY_IMAGE))
                toyName=cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))
                toyAge=cursor.getString(cursor.getColumnIndexOrThrow(KEY_AGE))
                toyPrice=cursor.getString(cursor.getColumnIndexOrThrow(KEY_PRICE))

                val toy=Items(image,toyName,toyAge,toyPrice)
                toylist.add(toy)

            }while (cursor.moveToNext())

        }

        return toylist
    }
    fun LoadDataAdmin():ArrayList<adminItem>
    {
        val toylist:ArrayList<adminItem> = ArrayList<adminItem>()

        val selectQuery="SELECT * FROM $DATABASE_TABLE"

        val db=dbHelper.readableDatabase

        var cursor: Cursor?=null

        try {
            cursor=db.rawQuery(selectQuery,null)
        }catch (e: SQLException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id:Int
        var image:Int
        var toyName:String
        var toyAge:String
        var toyPrice:String
        var x:Int

        if (cursor.moveToFirst()){
            do {
                id=cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID))
                image=cursor.getInt(cursor.getColumnIndexOrThrow(KEY_IMAGE))
                toyName=cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))
                toyAge=cursor.getString(cursor.getColumnIndexOrThrow(KEY_AGE))
                toyPrice=cursor.getString(cursor.getColumnIndexOrThrow(KEY_PRICE))

                val toy=adminItem(id,image,toyName,toyAge,toyPrice)
                toylist.add(toy)

            }while (cursor.moveToNext())

        }

        return toylist
    }

    fun deletedata(rowid:String):Int
    {

        val db=dbHelper.writableDatabase
        var succes = db.delete(DATABASE_TABLE,KEY_ID+"="+rowid,null).toInt()

        return  succes

    }

    fun update(rowid: String,name:String?,age:String,price:String?):Int
    {
        val cv=ContentValues()
        cv.put(KEY_NAME,name)
        cv.put(KEY_AGE,age)
        cv.put(KEY_PRICE,price)

        return database.update(DATABASE_TABLE,cv,KEY_ID+"="+rowid,null).toInt()
    }



}