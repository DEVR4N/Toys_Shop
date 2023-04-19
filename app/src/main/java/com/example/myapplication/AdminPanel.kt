package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.signIn

class AdminPanel : AppCompatActivity() {

    private lateinit var btnAdminInsertCustomer : Button
    private lateinit var btnAdminInsertProduct : Button
    private lateinit var btnAdminListCustomers : Button
    private lateinit var btnAdminExit : Button

    private lateinit var DBConnection : myDatabaseClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel)

        Buttons()
        DBConnection = myDatabaseClass(this@AdminPanel)
        DBConnection.openDBConnection()

        btnAdminInsertCustomer.setOnClickListener()
        {
            val intent = Intent(this@AdminPanel, signIn::class.java)
            startActivity(intent)
        }


        btnAdminListCustomers.setOnClickListener()
        {
            val intent = Intent(this@AdminPanel,customer_list::class.java)
            startActivity(intent)
        }


        btnAdminInsertProduct.setOnClickListener()
        {
            val intent22 = Intent(this@AdminPanel,Itemekle::class.java)
            startActivity(intent22)
        }


        btnAdminExit.setOnClickListener()
        {
            val intent = Intent(this@AdminPanel,Login::class.java)
            startActivity(intent)
        }

    }

    private fun Buttons()
    {
        btnAdminInsertCustomer = findViewById(R.id.btnAdminInsertCustomer)
        btnAdminInsertProduct = findViewById(R.id.btnAdminInsertProduct)
        btnAdminListCustomers = findViewById(R.id.btnAdminListCustomer)
        btnAdminExit = findViewById(R.id.btnAdminExit)
    }
}