package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.app.DatePickerDialog
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.CustomerItems
import org.w3c.dom.Text
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class customer_list : AppCompatActivity() {

    private lateinit var DBConnection : myDatabaseClass



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list)

        val delid = findViewById<Button>(R.id.btnListDelete)
        val srcid = findViewById<TextView>(R.id.txtListDel)
        val txtgoster = findViewById<TextView>(R.id.uyeler)

        DBConnection = myDatabaseClass(this@customer_list)
        DBConnection.openDBConnection()


        var MusteriList = DBConnection.listele() as ArrayList<custom1>
        var showResult = ""
        for (t in 0..MusteriList.size-1)
        {
            showResult +=
                    " ID          : " + MusteriList[t].id.toString() + "\n"+
                    " Name      : " +MusteriList[t].name + "\n"+
                    " Password  : " + MusteriList[t].password + "\n"+
                    " Mail      : "  +  MusteriList[t].mail + "\n"+
                    " Birthdate : " + MusteriList[t].birtdate + "\n"+
                    " Phone     : " +  MusteriList[t].phone + "\n"+
                    " Address   : " + MusteriList[t].address + "\n \n"
        }
        txtgoster.text = showResult



        delid.setOnClickListener()
        {
            val textId = srcid.text.toString()

            var reloadRes = ""

            for (i in 0..MusteriList.size-1)
            {
                if (textId.equals(MusteriList[i].id.toString()))
                {
                    DBConnection.deleteCustomer(textId)
                }
                else
                {
                    Toast.makeText(this,"Not found !",Toast.LENGTH_SHORT).show()
                }
            }


            var x = DBConnection.listele()

            for (t in 0..x.size-1)
            {
                reloadRes +=  " ID          : " + x[t].id.toString() + "\n"+
                        " Name      : " +x[t].name + "\n"+
                        " Password  : " + x[t].password + "\n"+
                        " Mail      : "  +  x[t].mail + "\n"+
                        " Birthdate : " + x[t].birtdate + "\n"+
                        " Phone     : " +  x[t].phone + "\n"+
                        " Address   : " + x[t].address + "\n \n"
            }
            txtgoster.text = reloadRes

        }


    }
}