package com.example.wens.ui.home

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.wens.R
import com.example.wens.model.objects.Articles
import com.example.wens.util.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {


    lateinit var tvTest: TextView

    private val  mHomeViewModel : HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        tvTest = findViewById(R.id.testText)
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


    private fun showToast(message: String): Toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)

}