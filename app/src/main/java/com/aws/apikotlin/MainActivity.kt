package com.aws.apikotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aws.apikotlin.RetrofitClient.initRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitAPI = initRetrofit()!!.create(RetrofitAPI::class.java)
        retrofitAPI.demoData().enqueue(object : Callback<DemoDataModel?> {
            override fun onResponse(
                call: Call<DemoDataModel?>,
                response: Response<DemoDataModel?>
            ) {
                if (response.code() == 200) {
                    Toast.makeText(this@MainActivity, "${response.message()}", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@MainActivity, "${response.body()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DemoDataModel?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }
}