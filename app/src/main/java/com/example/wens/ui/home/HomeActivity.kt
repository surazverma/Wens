package com.example.wens.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wens.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val  mHomeViewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupViews()
    }


    private fun setupViews() {
        val navController = findNavController(R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
//        setupActionBarWithNavController(navController)
        mHomeViewModel.getTopHeadlinesFromC("us")

    }

}