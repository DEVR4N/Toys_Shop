package com.example.myapplication



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdminAdapter(private val mlist: ArrayList<adminItem>):RecyclerView.Adapter<AdminAdapter.cart_ViewHolder>() {

    private lateinit var mlistener: onItemClickListener

    interface onItemClickListener {


        fun ButtonSign(position: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener) {

        mlistener = listener
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdminAdapter.cart_ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_admin, parent, false)

        return cart_ViewHolder(view, mlistener)
    }

    override fun onBindViewHolder(holder: AdminAdapter.cart_ViewHolder, position: Int) {

        val ItemsViewModel = mlist[position]

        // sets the image to the imageview from our itemHolder class
        holder.cartImage.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.c_textName.text = ItemsViewModel.toy_name
        holder.c_textAge.text = ItemsViewModel.toy_age
        holder.c_textPrice.text = ItemsViewModel.toy_price + "$"
        holder.c_textid.text=ItemsViewModel.id.toString()


    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    class cart_ViewHolder(ItemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(ItemView) {


        val cartImage: ImageView = ItemView.findViewById(R.id.imageviewCartA)
        val c_textName: TextView = ItemView.findViewById(R.id.c_textNameA)
        val c_textAge: TextView = ItemView.findViewById(R.id.c_textAgeA)
        val c_textPrice: TextView = ItemView.findViewById(R.id.c_textPriceA)
        val c_textid: TextView = itemView.findViewById(R.id.c_textİdA)


        init {

            ItemView.setOnClickListener()
            {

                listener.ButtonSign(adapterPosition)
            }


        }

    }

}
