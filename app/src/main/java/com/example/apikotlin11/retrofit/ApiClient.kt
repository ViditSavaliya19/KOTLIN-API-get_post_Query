package com.example.apikotlin11.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        var BASE_URL = "https://newsapi.org/v2/"

        fun getRetrofit() : Retrofit
        {
            var retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }


    }


}


/*
* https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=27dc0b3ab18a423798b03ce32cca08ae
*  BASE URL=============/Endpoint=====/Query===============================================================
*
* */