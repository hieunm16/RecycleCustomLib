package com.example.recyclecustomlibrary

import adapter.AdmobNativeAdAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclecustomlibrary.adapter.MyAdapter
import com.example.recyclecustomlibrary.databinding.ActivityMainBinding
import com.example.recyclecustomlibrary.model.UserModel

class MainActivity : AppCompatActivity() {

    private val myAdapter by lazy { MyAdapter() }
    private lateinit var binding: ActivityMainBinding
    private var dataList = arrayOf("cam", "quýt", "táo", "chuối", "mít", "xà lách", "sầu riêng", "mãng cầu", "hanoi")
    private var data = mutableListOf<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            val admobNativeAdAdapter: AdmobNativeAdAdapter = AdmobNativeAdAdapter.Builder
                .with(
                    "ca-app-pub-3940256099942544/1044960115",  //Create a native ad id from admob console
                    myAdapter,  //The adapter you would normally set to your recyClerView
                    "medium" //Set it with "small","medium" or "custom"
                )
                .adItemIterval(2) //native ad repeating interval in the recyclerview
                .build()
            recyclerView.adapter = admobNativeAdAdapter
        }

        for (i in 0 until dataList.size){
            data.add(UserModel(dataList[i]))
        }

        myAdapter.differ.submitList(data)
    }
}