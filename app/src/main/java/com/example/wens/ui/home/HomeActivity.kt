package com.example.wens.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wens.R
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.operation.remote.WensRemoteOperation
import com.example.wens.repository.WensRepository
import com.example.wens.status.Resource
import com.example.wens.status.ResourceState

class HomeActivity : AppCompatActivity() {

    lateinit var mHomeViewModel: HomeViewModel
    lateinit var tvTest: TextView
    lateinit var mHomeViewModelFactory: HomeViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val repository = WensRepository(WensRemoteOperation)
        mHomeViewModelFactory = HomeViewModelFactory(repository)

        mHomeViewModel =
            ViewModelProvider(this, mHomeViewModelFactory).get(HomeViewModel::class.java)

        tvTest = findViewById<TextView>(R.id.testText)
        setObservers()
    }

    private fun setObservers() {
        mHomeViewModel.getTopHeadlinesFromCountry("us")
            .observe(this, Observer<Resource<BaseListResponse<Articles>>>
            {
                when (it.status) {
                    ResourceState.LOADING -> {
                        showToast("Loading....")
                        tvTest.text = "Loading..."
                    }
                    ResourceState.SUCCESS -> {
                        showToast("Success")
                        tvTest.text = it.data?.data?.get(0)?.title ?: "Empty"
                    }
                    ResourceState.ERROR -> {
                        showToast("Error...")
                        tvTest.text = "Error"
                    }
                }

            })
    }

    private fun setButtonUp() {
        val button = findViewById<Button>(R.id.testButton)
        button.setOnClickListener {

        }
    }

    fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT)

}