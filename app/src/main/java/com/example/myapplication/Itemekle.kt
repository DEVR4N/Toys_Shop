package com.example.myapplication

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewex.CustomAdapter
import com.example.recyclerviewex.Items
import im.dino.dbinspector.activities.DbInspectorActivity

class Itemekle : AppCompatActivity() {

    lateinit var id_Text: TextView
    lateinit var name_Text: EditText
    lateinit var age_Text: EditText
    lateinit var price_Text: EditText
    lateinit var recycleA: RecyclerView
    lateinit var adapter: AdminAdapter
    lateinit var db: MyDB
    lateinit var but_add: Button
    lateinit var but_delete: Button
    lateinit var but_update: Button
    lateinit var list: ArrayList<adminItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemekle)

        id_Text = findViewById(R.id.idtext)
        name_Text = findViewById(R.id.toyNameA)
        age_Text = findViewById(R.id.toyAgeA)
        price_Text = findViewById(R.id.toyPriceA)
        recycleA = findViewById(R.id.recycleekle1)
        but_add = findViewById(R.id.Add_admin)
        but_delete = findViewById(R.id.remove_admin)
        but_update = findViewById(R.id.update_admin)



        db = MyDB(this@Itemekle) // write this here, it creates problems when created as a class value
        db.openDatabaseConnection()
        list = db.LoadDataAdmin() as ArrayList<adminItem>

        recycleA.layoutManager = LinearLayoutManager(this)
        adapter = AdminAdapter(list)
        recycleA.adapter = adapter

        but_add.setOnClickListener()
        {
            var toyname = name_Text.text.toString()
            var toyage = age_Text.text.toString()
            var toyprice = price_Text.text.toString()

            if (toyname.equals("") && toyage.equals("") && toyprice.equals(""))
            {
                Toast.makeText(this,"Invalid ID ! ",Toast.LENGTH_SHORT).show()
            }
            else
            {
                    var x = Items(R.drawable.toy4, toyname, toyage, toyprice)
                    var lastindex: Int
                    if (list.size == 0)
                        lastindex = 0
                    else
                        lastindex = list.size - 1


                    var lastid = list[lastindex].id + 1
                    var y = adminItem(lastid, R.drawable.toy4, toyname, toyage, toyprice)

                    if (db.insertRecords(x) > 0) {
                        Toast.makeText(this, "Success, please refresh the page", Toast.LENGTH_SHORT).show()
                        list.add(y)
                        adapter.notifyDataSetChanged()

                    }

                    clear()
                }
            11
        }


        but_delete.setOnClickListener()
        {

            var id=name_Text.text.toString()
            if(id.equals("")) {
                Toast.makeText(this,"İnvalid Enteries",Toast.LENGTH_SHORT).show()
            }else
            {
                var counter = 0
                var id = id_Text.text.toString()
                Toast.makeText(this, "hellooo" + id, Toast.LENGTH_SHORT).show()

                if (db.deletedata(id) > 0) {
                    Toast.makeText(this, "helllo be", Toast.LENGTH_SHORT).show()
                } else
                    Toast.makeText(this, "helllo berrr", Toast.LENGTH_SHORT).show()


                for (i in 0..list.size - 1) {
                    if (id.toInt() == list[i].id) {
                        break
                    } else counter++
                }

                list.removeAt(counter)
                adapter.notifyDataSetChanged()


                clear()

            }

        }



        but_update.setOnClickListener()
        {

            if (id_Text.text.equals(""))
            {
                Toast.makeText(this,"Invalid ID ! ",Toast.LENGTH_SHORT).show()
            }

            else {


                var id = id_Text.text.toString()
                var toyname = name_Text.text.toString()
                var toyage = age_Text.text.toString()
                var toyprice = price_Text.text.toString()
                if (db.update(id, toyname, toyage, toyprice) > 0)
                {
                    Toast.makeText(this, "Product is UPDATED" + id, Toast.LENGTH_SHORT).show()
                }

                var counter = 0
                for (i in 0..list.size - 1) {
                    if (id.equals(list[i].id.toString())) {
                        list[counter].toy_name = toyname
                        list[counter].toy_age = toyage
                        list[counter].toy_price = toyprice
                        break
                    } else counter++

                }
                adapter.notifyDataSetChanged()

            }
        }




        adapter.setOnItemClickListener(object : AdminAdapter.onItemClickListener {
            override fun ButtonSign(position: Int) {

                id_Text.text = list[position].id.toString()
                name_Text.setText(list[position].toy_name)
                age_Text.setText(list[position].toy_age)
                price_Text.setText(list[position].toy_price)

            }
        })

    }


    fun clear() {

        name_Text.setText("")
        age_Text.setText("")
        price_Text.setText("")
        id_Text.setText("")
    }

    fun checktext(): Boolean {
        var checked:Boolean
        if (name_Text.text.toString().equals(" ") && age_Text.text.toString().equals("") && price_Text.text.toString().equals(""))
        {
            checked=false
            Toast.makeText(this,"Texts are empty",Toast.LENGTH_SHORT).show()
        }
        else
        {
            checked = true


        }


        return checked


    }

}

