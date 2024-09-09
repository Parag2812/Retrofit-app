package com.example.myretroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

  lateinit var recyclerView: RecyclerView
   lateinit var myAdapter: myAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewForProduct)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<myData?> {
            override fun onResponse(call: Call<myData?>, response: Response<myData?>) {
                    val responseBody = response.body()
                    val productList = responseBody?.products!!

                myAdapter = myAdapter( productList, this@MainActivity)
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager =  LinearLayoutManager(this@MainActivity)

            }

            override fun onFailure(call: Call<myData?>, t: Throwable) {
                Log.d("Main activity", "onFailure: "+t.message)
            }
        })
    }
}