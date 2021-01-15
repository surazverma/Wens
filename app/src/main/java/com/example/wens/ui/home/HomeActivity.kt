package com.example.wens.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wens.R
import com.example.wens.model.objects.Articles
import com.example.wens.util.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val  mHomeViewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupViews()


        setObservers()
        mHomeViewModel.getTopHeadlinesFromC("us")
    }

    private fun setObservers() {
        mHomeViewModel.news
            .observe(this, Observer<ResultWrapper<List<Articles>>>
            {

            })
    }

    fun setupViews(){
        val navController = findNavController(R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
        setupActionBarWithNavController(navController)
    }

}