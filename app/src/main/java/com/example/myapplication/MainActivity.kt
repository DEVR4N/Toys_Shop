package com.example.myapplication

import android.content.ClipData
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewex.CustomAdapter
import com.example.recyclerviewex.Items
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    lateinit var cartdata: ArrayList<Items>
    lateinit var recyclerview:RecyclerView
    lateinit var adapter:CustomAdapter
    lateinit var buttonskip: Button
    private lateinit var db:MyDB
    lateinit var list:ArrayList<Items>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ekle() // BURADA URUNLER EKLENMEKTEDIR.
        // UYGULAMA BİR KEZ ÇALIŞTIKTAN SONRA BU EKLE FONKSIYONU KALDIRILMADIR, AKSI TAKDIRDE SUREKLI UYGULAMALAR EKLENECEKTIR


        DeclareThings()

        NewEnter()

        LoadData()

        Adapter()

        cartdata = ArrayList<Items>()


        cardTiktok()

        //db.clearAllRecords()



        //Buttonlar

        adapter.setOnItemClickListener(object : CustomAdapter.onItemClickListener {

            override fun ButtonClick(position: Int)
            {

                var newenter= intent.extras?.getBoolean("newenter0")

                if( newenter==true || newenter==null )
                {
                    var intent= Intent (this@MainActivity,Login::class.java)
                    startActivity(intent)
                }
                else
                   addItems(position)

            }

            override fun ButtonSign() {
                var newenter= intent.extras?.getBoolean("newenter0")

                if( newenter==true || newenter==null )
                {
                    var intent= Intent (this@MainActivity,Login::class.java)
                    startActivity(intent)
                }


            }
        })

        buttonskip.setOnClickListener()
        {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            intent.putParcelableArrayListExtra("cartdata", cartdata)
            startActivity(intent)
        }


        //Fonksiyonlar

    }

    fun addItems(position: Int) {

        var x = Items(list[position].image, list[position].toy_name, list[position].toy_age, list[position].toy_price)

        if (x in cartdata) {

            var builder= AlertDialog.Builder(this)
            builder.setTitle("Toys Shop")
            builder.setMessage("This Item Already in Shopping Cart !\n\nDo You Want To Add 1 More ?")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, id->
                dialog.cancel()
                cartdata.add(x)
            })
            builder.setNegativeButton("No", DialogInterface.OnClickListener{ dialog, id->
                dialog.cancel()
            })

            var message=builder.create()
            message.show()


        } else {
            cartdata.add(x)
            val addtoast = Toast.makeText(
                this@MainActivity,
                list[position].toy_name + " is Added To The Cart",
                Toast.LENGTH_SHORT
            )
            addtoast.setGravity(Gravity.BOTTOM, 0, 13)
            addtoast.show()
        }

    }

    fun DeclareThings()
    {
        recyclerview = findViewById<RecyclerView>(R.id.recycleView)
        buttonskip= findViewById(R.id.ekle)
    }

    fun NewEnter()
    {
        var newenter= intent.extras?.getBoolean("newenter")


        if( newenter==true || newenter==null )
        {
            Toast.makeText(this,"Welcome to Toys Shop Customer",Toast.LENGTH_LONG).show()
        }
    }
    fun LoadData()
    {
        db = MyDB(this@MainActivity) // write this here, it creates problems when created as a class value
        db.openDatabaseConnection()
        list=db.LoadData() as ArrayList<Items>

    }
    fun cardTiktok()
    {
        cartdata = ArrayList<Items>()
        var c= intent.extras?.getParcelableArrayList<Items>("1") as ArrayList<Items>?
        if(c==null)
        {
            c=ArrayList<Items>()
            cartdata=c
        }
        else{
            cartdata=c
        }

    }
    fun Adapter()
    {
        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(list)
        recyclerview.adapter = adapter

    }
    fun ekle()
    {

        var data = ArrayList<Items>()
        var images = intArrayOf(R.drawable.araba, R.drawable.araba2, R.drawable.araba3, R.drawable.spiderman, R.drawable.batman, R.drawable.ron_man, R.drawable.spidermant)
        var toynames = arrayOf("RacerCar X019", "Car Tr 2023", "BlueShark Q67", "Spiderman", "Batman", "Ironman", "Spiderman Gun")
        var ages = arrayOf("4-5 Ages", "7-9 Ages", "10-11 Ages", "4-9 Ages", "2-11 Ages", "8-15 Ages","2-14 Ages")
        var prices = arrayOf("4", "7", "10", "4", "7", "10", "10")
        for (i in 0..6) {
            data.add(Items(images[i], toynames[i], ages[i], prices[i]))
        }

        for(i in 0..data.size-1)
        {

            val x=Items(data[i].image,data[i].toy_name,data[i].toy_age,data[i].toy_price)
            db.insertRecords(x)

        }

    }

}



