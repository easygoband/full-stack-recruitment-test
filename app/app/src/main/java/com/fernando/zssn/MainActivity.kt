package com.fernando.zssn

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.fernando.zssn.databinding.ActivityMainBinding
import com.fernando.zssn.model.Survivor
import com.fernando.zssn.model.SurvivorClient
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var survivorsList: List<Survivor>
    lateinit var survivorsAdapter: SurvivorsAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        survivorsAdapter = SurvivorsAdapter(emptyList()) { survivor ->
            navigateTo(survivor)
        }

        binding.recycler.adapter = survivorsAdapter

        binding.bottomAppBar.navigationIcon = null

        binding.bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.stats -> {
                    navigateToCharts()
                    true
                }
                else -> false
            }
        }

        binding.buttonNewSurvivor.setOnClickListener {
            navigateToNewSurvivor()
        }

        lifecycleScope.launch{
            val survivors = SurvivorClient.service.listAllSurvivors("infectedReports<3")

            survivorsList = survivors.data.sortedByDescending { it.points }

            survivorsAdapter.survivors = survivorsList
            survivorsAdapter.notifyDataSetChanged()
        }

    }

    private fun navigateTo(survivor: Survivor) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra(ProfileActivity.EXTRA_SURVIVOR, survivor)
        startActivity(intent)
    }

    private fun navigateToCharts(){
        val intent = Intent(this, ChartsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToNewSurvivor() {
        val intent = Intent(this, NewSurvivorActivity::class.java)
        startActivity(intent)
    }

}