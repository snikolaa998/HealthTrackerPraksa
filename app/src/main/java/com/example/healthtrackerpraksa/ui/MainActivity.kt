package com.example.healthtrackerpraksa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.interfaces.BloodSugarDataIsReady
import com.example.healthtrackerpraksa.interfaces.DataIsReady
import com.example.healthtrackerpraksa.persistence.model.BloodSugar
import com.example.healthtrackerpraksa.ui.fragments.BloodPressureFragment
import com.example.healthtrackerpraksa.ui.fragments.BloodSugarFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity() : AppCompatActivity(), DataIsReady, BloodSugarDataIsReady {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph_host) as NavHostFragment? ?: return
        val navController = host.navController
        val addButton = findViewById<FloatingActionButton>(R.id.floating_add_btn)

        addButton.setOnClickListener {
            showPopUp()
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
    private fun showPopUp() {
        val currentFragment = findNavController(R.id.nav_graph_host).currentDestination
        when(currentFragment?.label) {
            "fragment_blood_pressure" -> {
                val popUpDialog = BloodPressurePopUp(this)
                popUpDialog.show(supportFragmentManager, "fragment_blood_pressure")
            }
            "fragment_blood_sugar" -> {
                val bloodSugarPopUp = BloodSugarPopUp(this)
                bloodSugarPopUp.show(supportFragmentManager, "fragment_blood_sugar")
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <F : Fragment> AppCompatActivity.getFragment(fragmentClass: Class<F>): F? {
        val navHostFragment = this.supportFragmentManager.fragments.first() as NavHostFragment

        navHostFragment.childFragmentManager.fragments.forEach {
            if (fragmentClass.isAssignableFrom(it.javaClass)) {
                return it as F
            }
        }
        return null
    }

    override fun dataIsReady(bloodPressure: com.example.healthtrackerpraksa.persistence.model.BloodPressure) {
        var fragment = getFragment(BloodPressureFragment::class.java)
        if (fragment != null) {
            (fragment as BloodPressureFragment).insert(bloodPressure)
        }
    }

    override fun bloodSugarDataIsReady(bloodSugar: BloodSugar) {
        var fragment = getFragment(BloodSugarFragment::class.java)
        if (fragment != null) {
            (fragment as BloodSugarFragment).insert(bloodSugar)
        }
    }
}