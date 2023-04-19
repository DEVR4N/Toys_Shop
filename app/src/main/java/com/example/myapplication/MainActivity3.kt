package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.recyclerviewex.Items
import java.util.ArrayList
import kotlin.properties.Delegates

class MainActivity3 : AppCompatActivity() {

    lateinit var text1:TextView
    lateinit var text2:TextView
    lateinit var but_Pay:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

         Declare()
         checkoutText()


        but_Pay.setOnClickListener{
            payDialog()
        }
    }




    //Fonksiyonlar...


    fun checkoutText()
    {

        var cartdata1=intent.getParcelableArrayListExtra<Items>("cartdata1") as ArrayList<Items>

        var x =cartdata1.size.toInt()

        var cost=0

        var text_pay=""

        for(i in 0..x-1)
        {
            text_pay += cartdata1[i].toy_name.toString()+"          " +cartdata1[i].toy_price+"$" + "\n \n"
            cost += cartdata1[i].toy_price.toInt()
        }

        text1.text=text_pay
        text2.text=cost.toString()+"$"
    }
    fun Declare()
    {
        text1=findViewById(R.id.items_)
        text2=findViewById(R.id.items_price)
        but_Pay=findViewById(R.id.buttonpay)

    }

    fun payDialog()
    {

            var builder=AlertDialog.Builder(this)
            builder.setTitle("Toys Shop")
            builder.setMessage("Do You Want To Finish Shopping ?")
            builder.setPositiveButton("Yes",DialogInterface.OnClickListener{dialog,id->
                Toast.makeText(this,"Thanks For Shopping",Toast.LENGTH_SHORT).show()
                dialog.cancel()
                var intent=Intent(this,MainActivity::class.java)
                var newenter=false
                intent.putExtra("newenter",newenter)
                startActivity(intent)
            })
            builder.setNegativeButton("No",DialogInterface.OnClickListener{dialog,id->
                dialog.cancel()
            })

            var message=builder.create()
            message.show()

    }

}