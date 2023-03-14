package com.example.quizesfand

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.Call
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    lateinit var client: OkHttpClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url("https://api.github.com/users/MENasri23")
//            .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onResponse(call: Call, response: Response) {
//                val result = response.body?.string()
//                runOnUiThread {
//                    findViewById<TextView>(R.id.txt).text = result
//                }
//            }
//
//            override fun onFailure(call: Call, e: IOException) {
//                e.printStackTrace()
//            }
//        })

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GithubApiService::class.java)
        service.getUser().enqueue(object : Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {

            }
            override fun onResponse(
                call: retrofit2.Call<User>,
                response:Response<User>){
                val json = response.body()?.toString()
                runOnUiThread {
                    findViewById<TextView>(R.id.txt).text = json
                }
            }
        })

        val apiService = retrofit.create(GithubApiService::class.java)

        apiService.getUser().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                Glide.with(this@MainActivity)
                    .load(user?.avatarUrl)
                    .into(findViewById<ImageView>(R.id.imageView))
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
            }
        })





    }
}
