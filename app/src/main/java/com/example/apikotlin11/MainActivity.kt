package com.example.apikotlin11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    var BASE_URL = "https://jsonplaceholder.typicode.com/posts"
    var BASE_URL1 = "https://jsonplaceholder.typicode.com/posts"
    var POST_DATA = "https://reqres.in/api/users";
    var requestQueue: RequestQueue? = null;
    var list = arrayListOf<ApiModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById<Button>(R.id.button)



        button.setOnClickListener {
          postApiCalling("Mishal","Android Developer")
        }

    }


    fun apiCalling() {

        requestQueue = Volley.newRequestQueue(this)
        var jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            BASE_URL,
            null,
            Response.Listener { response ->
//                Log.e("TAG", "apiCalling: ${response.getJSONArray(0)}")
                var i = 0;

                while (i < response.length()) {
                    var userid = response.getJSONObject(i).getString("userId")
                    var id = response.getJSONObject(i).getString("id")
                    var body = response.getJSONObject(i).getString("body")
                    var title = response.getJSONObject(i).getString("title")

                    var model = ApiModel(userid, id, title, body)
                    list.add(model)
                    Log.e("TAG", "apiCalling:$userid $id $body $title ")

                    i++
                }

            },
            Response.ErrorListener { error ->
                Log.e("TAG", "apiCalling: ${error.message}")
            })

        requestQueue?.add(jsonArrayRequest)

    }

    fun postApiCalling(name: String, job: String) {
        requestQueue = Volley.newRequestQueue(this)
        var stringRequest =object :  StringRequest(
            Request.Method.POST,
            POST_DATA,
            Response.Listener { response -> Toast.makeText(this,"Successfully Job Created !",Toast.LENGTH_SHORT).show()},
            Response.ErrorListener { error -> Log.e("TAG", "postApiCalling: ${error.message}" )},
        ){
            override fun getParams(): MutableMap<String, String>? {

                var map = HashMap<String,String>()
                map["name"] = "$name"
                map["job"] = "$job"
                return map
            }
        }

        requestQueue?.add(stringRequest)

        // ["name":"mishal","job":"Android"]

    }


}