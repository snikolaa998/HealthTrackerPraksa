package com.example.healthtrackerpraksa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.interfaces.DataIsReady
import com.example.healthtrackerpraksa.repository.Repository
import com.example.healthtrackerpraksa.viewModels.BloodPressureViewModel
import com.example.healthtrackerpraksa.viewModels.BloodPressureViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.StringBuilder

class MainActivity() : AppCompatActivity(), DataIsReady {

    private lateinit var bloodPressureViewModel: BloodPressureViewModel
    private lateinit var bloodPressureViewModelFactory: BloodPressureViewModelFactory

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
                val popUpDialog = BloodPressurePopUp(this)
                popUpDialog.show(supportFragmentManager, "fragment_blood_pressure")
            }
        }
    }

    override fun dataIsReady(bloodPressure: com.example.healthtrackerpraksa.persistence.model.BloodPressure) {
        val repo = Repository(application)
        bloodPressureViewModelFactory = BloodPressureViewModelFactory(repo)
        bloodPressureViewModel = ViewModelProvider(this, bloodPressureViewModelFactory).get(BloodPressureViewModel::class.java)
        bloodPressureViewModel.insert(bloodPressure)
        bloodPressureViewModel.allBloodPressure.observe(this, Observer {
            val stringBuilder = StringBuilder()
//            Toast.makeText(this, "Duzina database je: ${it.size}", Toast.LENGTH_SHORT).show()
            for (data in it) {
                stringBuilder.append(data.note).append("\n")
            }
            Log.d("DUZINA", stringBuilder.toString())
        })
    }
}