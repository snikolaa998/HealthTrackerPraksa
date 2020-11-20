package com.example.healthtrackerpraksa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.example.healthtrackerpraksa.ui.inputdialogs.TemperaturePopupDialog
import com.example.healthtrackerpraksa.ui.viewmodels.MainViewModel
import com.example.healthtrackerpraksa.ui.viewmodels.MainViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), TemperaturePopupDialog.ITempDialog {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as MyApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_graph_host) as NavHostFragment?
                ?: return
        val navController = host.navController

        setupBottomNavMenu(navController)

        val fabOpenPopup = findViewById<FloatingActionButton>(R.id.fab_open_add_popup)
        fabOpenPopup.setOnClickListener {
            showInputDialog(navController)
        }

    }

    private fun showInputDialog(navController: NavController) {
        when (navController.currentDestination?.id) {
            R.id.temperatureFragment -> {
                val dialog = TemperaturePopupDialog(
                    this@MainActivity, this
                )
                dialog.show()
            }
            R.id.bloodSugarFragment -> {
                navController.navigate(R.id.bloodSugarPopupDialogFragment, null)
            }
        }

    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation_menu)
        bottomNav?.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_graph_host))
                || super.onOptionsItemSelected(item)
    }

    override fun tempDone(temperature: Temperature) {
        viewModel.insertTemperature(temperature)
    }


}


