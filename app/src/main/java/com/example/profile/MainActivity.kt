package com.example.profile

import android.graphics.Bitmap
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.profile.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var res :ArrayList<ProfileData>
    lateinit var rvList : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        res = arrayListOf()
         rvList= findViewById<RecyclerView>(R.id.reList)
//        getUserData()

        getUserData()

    }






//        val rvList = findViewById<RecyclerView>(R.id.reList)

//        rvList.adapter = CustomAdaptor(this@MainActivity,res)



    private fun getUserData(){
        val pB = findViewById<ProgressBar>(R.id.progressBar)

        val retrofitBuilder= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/api/")
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData()


        retrofitData.enqueue(object : Callback<UserData?> {
            override fun onResponse(call: Call<UserData?>, response: Response<UserData?>) {
                Log.i("res",response.body().toString())
                res = response.body()!!.data
                Log.i("res", res.toString())


                rvList.layoutManager = LinearLayoutManager(this@MainActivity)
                rvList.adapter = CustomAdaptor(this@MainActivity,res)
                pB.visibility = View.GONE




            }

            override fun onFailure(call: Call<UserData?>, t: Throwable) {
                Log.i("res",t.toString())
            }
        })




    }
}