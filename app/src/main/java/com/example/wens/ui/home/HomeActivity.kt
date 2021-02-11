package com.example.wens.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wens.R
import com.example.wens.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val mHomeViewModel: HomeViewModel by viewModels()
    lateinit var mBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        setupViews()
    }


    private fun setupViews() {
        val navController = findNavController(R.id.nav_host_fragment)
        mBinding.bottomNav.setupWithNavController(navController)
        mHomeViewModel.getTopStreamHeadlinesFromCountry("us")
    }

}