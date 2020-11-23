package com.example.healthtrackerpraksa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.healthtrackerpraksa.MyApplication
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.ui.inputdialogs.IDialogInputListener
import com.example.healthtrackerpraksa.ui.inputdialogs.TemperatureDialog
import com.example.healthtrackerpraksa.ui.viewmodels.HealthTrackerViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {


    private val healthTrackerViewModel: HealthTrackerViewModel by viewModels()
    private lateinit var navigationHost: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationHost =
            supportFragmentManager.findFragmentById(R.id.nav_graph_host) as NavHostFragment
        navController = navigationHost.navController

        setupBottomNavMenu(navController)
        initPopupDialogButton(navController)

    }


    private fun initPopupDialogButton(navController: NavController) {
        val fabOpenPopup = findViewById<FloatingActionButton>(R.id.fab_open_add_popup)
        fabOpenPopup.setOnClickListener {
            showInputDialog(navController)
        }
    }

    private fun showInputDialog(navController: NavController) {
        when (navController.currentDestination?.id) {
            R.id.temperatureFragment -> showTemperatureDialog().show()

            R.id.bloodSugarFragment -> {

            }
        }

    }

    private fun showTemperatureDialog(): TemperatureDialog {
        return TemperatureDialog(
            this, object : IDialogInputListener<Temperature> {
                override fun onDialogValueSubmitted(value: Temperature) {
                    healthTrackerViewModel.insertTemperature(value)
                }
            }
        )
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation_menu)
        bottomNav?.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_graph_host))
                || super.onOptionsItemSelected(item)
    }


}


