package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewex.CustomAdapter
import com.example.recyclerviewex.Items
import java.util.ArrayList

class MainActivity2 : AppCompatActivity() {



    lateinit var cartdata:ArrayList<Items>
    lateinit var adapter: CustomCartAdapter
    lateinit var recyclerview:RecyclerView
    lateinit var btncheckout:Button
    lateinit var btn_geri:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Declare()
        LoadCart()


        btn_geri.setOnClickListener()
        {

            var intent=Intent(this,MainActivity::class.java)
            intent?.putParcelableArrayListExtra("1",cartdata)
            startActivity(intent)

        }


        adapter.setOnItemClickListener(object : CustomCartAdapter.onItemClickListener{

            override fun ButtonClick(position: Int) {
                cartdata.removeAt(position)
                adapter.notifyDataSetChanged()
                if(cartdata.size==0)
                {
                    var message=Toast.makeText(this@MainActivity2,"No Items in Shopping Cart ",Toast.LENGTH_SHORT)
                    message.setGravity(Gravity.CENTER,0,120)
                    message.show()
                }
                else
                    Toast.makeText(this@MainActivity2,cartdata[position].toy_name+" is Remove From Shopping Cart ",Toast.LENGTH_SHORT).show()

            }
        })

        btncheckout.setOnClickListener()
        {

            if(cartdata.size==0) {
                var builder= AlertDialog.Builder(this)
                builder.setTitle("Toys Shop")
                builder.setMessage("Shopping Cart is Empty !\n\nDo You Want To Go Shopping Part ?")
                builder.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, id->
                    dialog.cancel()
                    var intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                })
                builder.setNegativeButton("No", DialogInterface.OnClickListener{ dialog, id->
                    dialog.cancel()
                })

                var message=builder.create()
                message.show()

            }
            else{

                var intent = Intent(this, MainActivity3::class.java)
                intent.putParcelableArrayListExtra("cartdata1", cartdata)
                startActivity(intent)
            }

        }

    }

    fun LoadCart()
    {
        recyclerview.layoutManager = LinearLayoutManager(this)
        cartdata=intent.getParcelableArrayListExtra<Items>("cartdata") as ArrayList<Items>
        adapter = CustomCartAdapter(cartdata)
        recyclerview.adapter = adapter

    }
    fun Declare()
    {


        recyclerview = findViewById(R.id.cart_recycleView)
        btncheckout=findViewById(R.id.btn_checkout)
        btn_geri=findViewById(R.id.buttongeri)

    }


}