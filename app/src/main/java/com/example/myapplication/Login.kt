package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Login : AppCompatActivity() {

    private lateinit var txtLogUsername : EditText
    private lateinit var txtLogPassword : EditText
    private lateinit var btnLogClear : Button
    private lateinit var btnLogEnter : Button
    private lateinit var btnLogSign : Button

    private lateinit var DBConnection:myDatabaseClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        DBConnection = myDatabaseClass(this@Login)
        DBConnection.openDBConnection()
        //DBConnection.verileriTemizle()


        LogForm()

        btnLogEnter.setOnClickListener()
        {
            val Username = txtLogUsername.text.toString()
            val Password = txtLogPassword.text.toString()

            if(DBConnection.contorl(Username,Password,this@Login) )
            {
                val intent = Intent(this@Login,MainActivity::class.java)
                var newenter=false
                intent.putExtra("newenter0",newenter)
                startActivity(intent)
            }
            else if (Username.equals("admin") && Password.equals("admin"))
            {

                val intent = Intent(this@Login,AdminPanel::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this,"Wrong Username or Password",Toast.LENGTH_LONG).show()
            }

        }

        btnLogClear.setOnClickListener()
        {
            txtLogUsername.text.clear()
            txtLogPassword.text.clear()
            txtLogUsername.requestFocus()
        }

        btnLogSign.setOnClickListener()
        {
            val intent = Intent(this@Login,signIn::class.java)
            startActivity(intent)
        }
    }

    private fun LogForm()
    {
        txtLogPassword = findViewById(R.id.txtLogPassword)
        txtLogUsername = findViewById(R.id.txtLogUsername)
        btnLogClear = findViewById(R.id.btnLogClear)
        btnLogSign = findViewById(R.id.btnLogSign)
        btnLogEnter = findViewById(R.id.btnLogEnter)
    }
}