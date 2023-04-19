package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {

        val sqlCode = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  KEY_NAME + " TEXT NOT NULL, " +
                KEY_PASSWORD + " TEXT NOT NULL," + KEY_MAIL + " TEXT NOT NULL, " + KEY_PHONE + " TEXT NOT NULL, " +
                KEY_ADDRESS + " TEXT NOT NULL, " + KEY_BIRTHDATE + " TEXT NOT NULL);"

        sqLiteDatabase.execSQL(sqlCode)

    }
    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, toVersion: Int, fromVersion: Int) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $DATABASE_TABLE")
        onCreate(sqLiteDatabase)
    }

    companion object
    {
        const val KEY_ID = "customerID"
        const val KEY_NAME = "customerName"
        const val KEY_PASSWORD = "customerPassword"
        const val KEY_MAIL = "customerMail"
        const val KEY_PHONE = "customerPhone"
        const val KEY_ADDRESS = "customerAddress"
        const val KEY_BIRTHDATE = "customerBirthDate"

        //DATABASE CONTENTS
        private const val DATABASE_NAME = "myCustomers"
        private const val DATABASE_TABLE = "customersTable"
        private const val DATABASE_VERSION = 1
    }
}