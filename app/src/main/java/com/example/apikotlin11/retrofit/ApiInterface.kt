package com.example.apikotlin11.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("top-headlines")
    fun getNews(@Query("country")country :String,@Query("category") category :String,@Query("apiKey") key :String) : Call<NewModel>

}

/*
* https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=27dc0b3ab18a423798b03ce32cca08ae
*  BASE URL=============/Endpoint=====/Query===============================================================
*
* */