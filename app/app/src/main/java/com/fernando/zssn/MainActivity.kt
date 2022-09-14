package com.fernando.zssn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fernando.zssn.databinding.ActivityMainBinding
import com.fernando.zssn.model.Survivor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val survivorsAdapter = SurvivorsAdapter(
            listOf(
                Survivor("Fernando", "Ordaz"),
                Survivor("Fernando1", "Ordaz1"),
                Survivor("Fernando2", "Ordaz2"),
                Survivor("Fernando3", "Ordaz3"),
                Survivor("Fernando4", "Ordaz4"),
                Survivor("Fernando5", "Ordaz5"),
                Survivor("Fernando6", "Ordaz6"),
                Survivor("Fernando7", "Ordaz7"),
                Survivor("Fernando8", "Ordaz8"),
                Survivor("Fernando9", "Ordaz9"),
                Survivor("Fernando10", "Ordaz10"),
            )
        ) { survivor ->
            Toast.makeText(this@MainActivity, survivor.name, Toast.LENGTH_SHORT).show()
        }

        binding.recycler.adapter = survivorsAdapter
    }
}