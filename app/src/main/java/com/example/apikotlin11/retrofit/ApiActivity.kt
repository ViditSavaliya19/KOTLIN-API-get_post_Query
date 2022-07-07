package com.example.apikotlin11.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apikotlin11.R
import com.example.apikotlin11.retrofit.ApiClient.Companion.getRetrofit
import com.example.apikotlin11.retrofit.adapter.MyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiActivity : AppCompatActivity() {

    var list = listOf<ArticlesItem?>()
    lateinit var rv_news :RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

       var US_btn=findViewById<Button>(R.id.US_btn)
       var UK_btn=findViewById<Button>(R.id.UK_btn)
       var IN_btn=findViewById<Button>(R.id.IN_btn)
       var AU_btn=findViewById<Button>(R.id.AU_btn)
        rv_news =findViewById<RecyclerView>(R.id.rv_news)




        US_btn.setOnClickListener {
            getNews("US","business")
        }

        UK_btn.setOnClickListener {
            getNews("UK","business")
        }


        AU_btn.setOnClickListener {
            getNews("AU","business")
        }

        IN_btn.setOnClickListener {
            getNews("IN","business")
        }
    }

    fun getNews(country :String, category: String)
    {
      var apiInterface =  getRetrofit().create(ApiInterface::class.java)
        apiInterface.getNews(country,category,"27dc0b3ab18a423798b03ce32cca08ae").enqueue(object : Callback<NewModel>{
            override fun onResponse(call: Call<NewModel>, response: Response<NewModel>) {

                var newsModel = response.body()

               list = newsModel?.articles!!;

                Log.e("TAG", "onResponse: ${response.body()}" )
                rvNewsSetup()

            }

            override fun onFailure(call: Call<NewModel>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}" )
            }
        })

    }


    fun rvNewsSetup()
    {
        var myAdapter = MyAdapter(this,list)
        var lm = LinearLayoutManager(this)
        rv_news.adapter = myAdapter
        rv_news.layoutManager = lm

    }


    override fun onStart() {
        super.onStart()

        getNews("in","business")

    }

}