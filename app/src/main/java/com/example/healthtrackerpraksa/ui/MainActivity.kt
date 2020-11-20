package com.example.healthtrackerpraksa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.ui.fragments.BloodPressure
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

//Totalno bezveze komentar
//Totalno bezveze komentar2
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph_host) as NavHostFragment? ?: return
        val navController = host.navController
        val addButton = findViewById<FloatingActionButton>(R.id.floating_add_btn)
        addButton.setOnClickListener {
            getCurrentFragment()
        }
        setupBottomNavMenu(navController)

    }
    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation_menu)
        bottomNav?.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_graph_host))
                || super.onOptionsItemSelected(item)
    }
    private fun getCurrentFragment() {
        val currentFragment = findNavController(R.id.nav_graph_host).currentDestination
        when(currentFragment?.label) {
            "fragment_blood_pressure" -> {
                val popUpDialog = BloodPressurePopUp()
                popUpDialog.show(supportFragmentManager, "fragment_blood_pressure")
            }
        }
    }
}