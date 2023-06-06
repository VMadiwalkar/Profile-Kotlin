package com.example.profile

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.ByteArrayOutputStream
import kotlin.math.log

class CustomAdaptor(private val context:Context,private var listData: MutableList<ProfileData>): RecyclerView.Adapter<CustomAdaptor.MyViewHolder>() {
//    private var listData = mutableListOf<Data>()
      var mImages: ArrayList<Bitmap> = arrayListOf()





    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)  {
        val fName:TextView = itemView.findViewById(R.id.firstName)
        val avatar: ImageView = itemView.findViewById(R.id.avatar)
        val parentView: CardView = itemView.findViewById(R.id.parent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_items,parent,false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.fName.text = listData[position].first_name
//        Glide.with(context)
//            .load(listData[position]!!.avatar)
//            .into(holder.avatar)


        Glide.with(context)
            .asBitmap()
            .load(listData[position].avatar)
            .into(object : CustomTarget<Bitmap>(100,100){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    mImages.add(resource)
                    holder.avatar.setImageBitmap(resource)

//
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

            })
        holder.parentView.setOnClickListener{
            val i :Intent = Intent(context,DetailsActivity::class.java)

            var bStream  =  ByteArrayOutputStream()
//            var bStream  =  VectorDrawableCompat.create(getResources(), mImages[position])
            val fName = listData[position].first_name
            val lName = listData[position].last_name
            val email = listData[position].email
            val id = listData[position].id
            Log.i("id",id.toString())
            mImages[position].compress(Bitmap.CompressFormat.JPEG, 100, bStream)
            val byteArray = bStream.toByteArray()
            i.putExtra("img", byteArray)
            i.putExtra("fname", fName)
            i.putExtra("lname", lName)
            i.putExtra("email", email)
            i.putExtra("id", id)


            context.startActivity(i)
        }



    }
}

