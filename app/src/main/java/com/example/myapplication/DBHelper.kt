package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {

        /*
            CREATE TABLE ContactsTable(_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    _studentName TEXT NOT NULL.
                    _studentSurname TEXT NOT NULL);*/
        val sqlCode = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_IMAGE + " INT, " +
                KEY_NAME + " TEXT, " +
                KEY_AGE + " TEXT, " +
                KEY_PRICE + " TEXT);"
        sqLiteDatabase.execSQL(sqlCode)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, toVersion: Int, fromVersion: Int) {

        //only called when database file exists, the stored version number is lower than requested.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $DATABASE_TABLE")
        onCreate(sqLiteDatabase)
    }

    companion object {
        const val KEY_ID = "_id"
        const val KEY_NAME = "_toyname"
        const val KEY_AGE = "_toyage"
        const val KEY_IMAGE = "_toymage"
        const val KEY_PRICE = "_toyprıce"
        private const val DATABASE_NAME = "toytable"
        private const val DATABASE_TABLE = "ToyDataBase"
        private const val DATABASE_VERSION = 1 //version of database
    }
}