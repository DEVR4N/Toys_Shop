package com.example.recyclerviewex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class CustomAdapter(private val mList: List<Items>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener





    interface  onItemClickListener {



        fun ButtonClick(position: Int)
        fun ButtonSign()

    }

    fun setOnItemClickListener (listener: onItemClickListener){

        mListener = listener
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_layout, parent, false)

        return ViewHolder(view,mListener)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text =  "Name  "+ItemsViewModel.toy_name
        holder.textView2.text = "Age   "+ItemsViewModel.toy_age
        holder.textView3.text = "Price  "+ItemsViewModel.toy_price+"$"



    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView1)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
        val textView3: TextView = itemView.findViewById(R.id.textView3)
        val Button1:Button=itemView.findViewById(R.id.butADD)


        init {



            Button1.setOnClickListener()
            {

                listener.ButtonClick(adapterPosition)
            }

            ItemView.setOnClickListener()
            {

                listener.ButtonSign()
            }


        }
    }
}