package com.aws.apikotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aws.apikotlin.RetrofitClient.initRetrofit
import com.aws.apikotlin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var demoDataModel = ArrayList<DemoDataModelItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofitAPI = initRetrofit()!!.create(RetrofitAPI::class.java)
        retrofitAPI.demoData().enqueue(object : Callback<DemoDataModel?> {
            override fun onResponse(
                call: Call<DemoDataModel?>,
                response: Response<DemoDataModel?>
            ) {
                if (response.code() == 200) {

//                    for (i in demoDataModel.withIndex()){
//                        demoDataModel.add(response.body()!![i.index])
//                    }

                    demoDataModel.addAll(response.body()!!)

                    binding.recycler.adapter = DemoAdapter(this@MainActivity, demoDataModel)
                    Toast.makeText(this@MainActivity, "${response.message()}", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@MainActivity, "${response.body()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DemoDataModel?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })



//        val retrofitAPI = initRetrofit()!!.create(RetrofitAPI::class.java)
//        retrofitAPI.manishaApi("","").enqueue(object : Callback<ManishDataModel?> {
//            override fun onResponse(
//                call: Call<ManishDataModel?>,
//                response: Response<ManishDataModel?>
//            ) {
//                if (response.code() == 200) {
//                    Toast.makeText(this@MainActivity, "${response.message()}", Toast.LENGTH_SHORT).show()
//                    Toast.makeText(this@MainActivity, "${response.body()}", Toast.LENGTH_SHORT).show()
//
//
//                }
//            }
//
//            override fun onFailure(call: Call<ManishDataModel?>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
//            }
//
//        })
    }
}