package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewex.Items

class CustomCartAdapter(private val mlist :List<Items>):RecyclerView.Adapter<CustomCartAdapter.cart_ViewHolder>()
{

    private lateinit var mlistener: onItemClickListener

    interface  onItemClickListener {


        fun ButtonClick(position: Int)

    }

    fun setOnItemClickListener (listener: onItemClickListener){

        mlistener = listener
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomCartAdapter.cart_ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_rcycleview_layout, parent, false)

        return cart_ViewHolder(view,mlistener)
    }

    override fun onBindViewHolder(holder: CustomCartAdapter.cart_ViewHolder, position: Int) {

        val ItemsViewModel = mlist[position]

        // sets the image to the imageview from our itemHolder class
        holder.cartImage.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.c_textName.text = ItemsViewModel.toy_name
        holder.c_textAge.text = ItemsViewModel.toy_age
        holder.c_textPrice.text = ItemsViewModel.toy_price+"$"


    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    class cart_ViewHolder(ItemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {


        val cartImage:ImageView=ItemView.findViewById(R.id.imageviewCart)
        val c_textName:TextView=ItemView.findViewById(R.id.c_textName)
        val c_textAge:TextView=ItemView.findViewById(R.id.c_textAge)
        val c_textPrice:TextView=ItemView.findViewById(R.id.c_textPrice)
        val Button1:Button=ItemView.findViewById(R.id.butDelete)


        init {

            Button1.setOnClickListener()
            {

                listener.ButtonClick(adapterPosition)
            }


        }
    }


}