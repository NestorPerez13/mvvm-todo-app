package com.nestor.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.nestor.todotasks.R
import com.nestor.todotasks.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalStateException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding
    private val navController: NavController by lazy {
        val host =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
                ?: throw IllegalStateException("NavHost is null")
        host.findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.materialToolbar)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}