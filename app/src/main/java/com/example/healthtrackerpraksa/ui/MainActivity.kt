package com.example.healthtrackerpraksa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.healthtrackerpraksa.R
import com.google.android.material.bottomnavigation.BottomNavigationView

//Totalno bezveze komentar
//Totalno bezveze komentar2
// comment1
//comment 2
//comment 3
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Komentar
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph_host) as NavHostFragment? ?: return
        val navController = host.navController
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
}