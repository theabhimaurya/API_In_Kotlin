package com.aws.apikotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.aws.apikotlin.RetrofitClient.initRetrofit
import com.aws.apikotlin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var demoDataModel = ArrayList<DemoDataModelItem>()
    private lateinit var demoFragment: DemoFragment
    private lateinit var demo2Fragment: Demo2Fragment
    private lateinit var demoJavaFragment: DemoJavaFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        fragment init
        demoFragment = DemoFragment()
        demo2Fragment = Demo2Fragment()
        demoJavaFragment = DemoJavaFragment()

        changeFragment(demoFragment)

        binding.textView.setOnClickListener {
            changeFragment(demoJavaFragment)
            CustomBottomSheetDialogFragment().show(supportFragmentManager, "Dialog")
        }



        val retrofitAPI = initRetrofit()!!.create(RetrofitAPI::class.java)
        retrofitAPI.demoData().enqueue(object : Callback<DemoDataModel?> {
            override fun onResponse(
                call: Call<DemoDataModel?>,
                response: Response<DemoDataModel?>
            ) {
                if (response.code() == 200) {

                    demoDataModel.addAll(response.body()!!)

                    binding.recycler.adapter = DemoAdapter(this@MainActivity, demoDataModel)
                    Toast.makeText(this@MainActivity, "${response.message()}", Toast.LENGTH_SHORT)
                        .show()
                    Toast.makeText(this@MainActivity, "${response.body()}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<DemoDataModel?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun changeFragment(fragment: Fragment) {
        try {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            transaction.replace(R.id.mainFrameLayout, fragment)
            transaction.commitAllowingStateLoss()
//            supportFragmentManager.executePendingTransactions()
        } catch (e: Exception) {
            Log.d("TAG", "changeFragment: exception in change fragment ${e.message}")
        }
    }
}