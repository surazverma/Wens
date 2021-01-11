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
import com.example.wens.operation.remote.WensRemoteOperation
import com.example.wens.repository.WensRepository
import com.example.wens.util.ResultWrapper

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
        mHomeViewModel.getTopHeadlinesFromC("us")
    }

    private fun setObservers() {
        mHomeViewModel.news
            .observe(this, Observer<ResultWrapper<List<Articles>>>
            {
                when (it) {
                    is ResultWrapper.Loading -> {
                        showToast("Loading....")
                        tvTest.text = "Loading..."
                    }
                    is ResultWrapper.Success -> {
                        showToast("Success")
                        tvTest.text = it.value[0].title
                    }
                    is ResultWrapper.NetworkError -> {
                        showToast("Error...")
                        tvTest.text = it.error
                    }
                    is ResultWrapper.GenericError -> {
                        showToast("Error...")
                        tvTest.text = it.error
                    }
                    is ResultWrapper.ServerError -> {
                        showToast("Error...")
                        tvTest.text = it.error
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