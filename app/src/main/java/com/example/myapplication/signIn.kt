package com.example.myapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.recyclerviewex.Items
import java.util.*

class signIn : AppCompatActivity() {

    private lateinit var DBConnection : myDatabaseClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Form
        val btnSignDate = findViewById<Button>(R.id.btnSignDate)
        val btnSignSubmit2 = findViewById<Button>(R.id.btnSignSubmit2)
        val btnSignClear = findViewById<Button>(R.id.btnSignClear)
        //Texts
        val txtSignName = findViewById<EditText>(R.id.txtSignName)
        val txtSignPass = findViewById<EditText>(R.id.txtSignPass)
        val txtSignMail = findViewById<EditText>(R.id.txtSignMail)
        val txtSignPhone = findViewById<EditText>(R.id.txtSignPhone)
        val txtSignAddress = findViewById<EditText>(R.id.txtSignAddress)
        val txtSignDateRes = findViewById<TextView>(R.id.txtSignDateResult)
        val txtSignText = findViewById<TextView>(R.id.txtSignTEXT)

        //DatePicker START
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        btnSignDate.setOnClickListener() // DatePicker
        {
            val datepicker = DatePickerDialog(
                this,DatePickerDialog.OnDateSetListener{view,mYear,mMonth,mDay ->
                    txtSignDateRes.setText(""+ mDay + "/" + mMonth + "/" + mYear) },year,month,day )
            datepicker.show()
        }



        DBConnection = myDatabaseClass(this@signIn)
        DBConnection.openDBConnection()


        btnSignSubmit2.setOnClickListener() // Create User Button
        {

            val name = txtSignName.text.toString()
            val password = txtSignPass.text.toString()
            val mail = txtSignMail.text.toString()
            val birtdate = txtSignDateRes.text.toString()
            val phone = txtSignPhone.text.toString()
            val address = txtSignAddress.text.toString()

            var CusInformation = CustomerItems(name,password,mail,birtdate,phone,address)
            try {
                DBConnection.openDBConnection()
                DBConnection.insertData(CusInformation)
                Toast.makeText(this,"User Created ", Toast.LENGTH_SHORT).show()
                DBConnection.closeDBConnection()

                txtSignName.text.clear()
                txtSignPass.text.clear()
                txtSignMail.text.clear()
                txtSignPhone.text.clear()
                txtSignAddress.text.clear()
                txtSignDateRes.text = ""

                txtSignName.requestFocus()

            }
            catch (e:Exception)
            {
                Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()
            }

        }

        btnSignClear.setOnClickListener() // Clear Button
        {
            txtSignName.text.clear()
            txtSignPass.text.clear()
            txtSignMail.text.clear()
            txtSignPhone.text.clear()
            txtSignAddress.text.clear()
            txtSignDateRes.text = ""

            txtSignName.requestFocus()
        }

    }
}