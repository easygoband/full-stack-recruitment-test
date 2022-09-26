package com.fernando.zssn.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.fernando.zssn.R
import com.fernando.zssn.databinding.ActivityMainBinding
import com.fernando.zssn.data.model.Survivor
import com.fernando.zssn.ui.view.adapter.SurvivorsAdapter
import com.fernando.zssn.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var survivorsList: List<Survivor>
    lateinit var survivorsAdapter: SurvivorsAdapter

    private val mainViewModel: MainViewModel by viewModels()

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

        mainViewModel.onCreate()

        mainViewModel.survivors.observe(this, Observer { survivors ->
            survivorsList = survivors.sortedByDescending { survivor ->
                survivor.points
            }

            survivorsAdapter.survivors = survivorsList
            survivorsAdapter.notifyDataSetChanged()
        })

        mainViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })

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