package com.fernando.zssn.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.fernando.zssn.databinding.ProfileActivityBinding
import com.fernando.zssn.data.model.Item
import com.fernando.zssn.data.model.Survivor
import com.fernando.zssn.ui.viewmodel.ProfileViewModel
import com.fernando.zssn.ui.viewmodel.ProfileViewModelFactory
import kotlinx.android.synthetic.main.item_location.view.*
import kotlinx.android.synthetic.main.item_profile_inventory.view.*
import kotlinx.android.synthetic.main.item_report.view.*

class ProfileActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SURVIVOR = "ProfileActivity:survivor"
    }

    private lateinit var binding: ProfileActivityBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ProfileActivityBinding.inflate(layoutInflater)

        binding.backButton.setOnClickListener{
            navigateTo()
        }

        val survivor = intent.getParcelableExtra<Survivor>(EXTRA_SURVIVOR)

        binding.inventory.updateLocationButton.setOnClickListener{
            if (survivor != null) {
                navigateToUpdateLocation(survivor)
            }
        }

        binding.inventory.reportSurvivorButton.setOnClickListener {
            if (survivor != null) {
                val builder = AlertDialog.Builder(this@ProfileActivity)
                builder.setMessage("¿Estás seguro de reportar este superviviente?")
                    .setCancelable(false)
                    .setPositiveButton("Sí, reportar") { dialog, id ->

                        val profileViewModel : ProfileViewModel by viewModels{ ProfileViewModelFactory(survivor.id) }

                        profileViewModel.reportSurvivor()

                        profileViewModel.survivorReportedResponse.observe(this, Observer { reportedResponse ->
                            if (reportedResponse.code == 200) {
                                finish()
                                startActivity(intent)
                            }else{
                                Toast.makeText(this@ProfileActivity, "Ocurrió un error inesperado", Toast.LENGTH_SHORT).show()
                            }
                        })

                        profileViewModel.isLoading.observe(this, Observer {
                            binding.loading.isVisible = it
                        })
                    }
                    .setNegativeButton("No, cancelar") { dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }
        }

        if (survivor != null) {

            val profileViewModel: ProfileViewModel by viewModels{ ProfileViewModelFactory(survivor.id) }

            profileViewModel.getSingleSurvivor()

            profileViewModel.survivor.observe(this, Observer { survivorDb ->

                setViewData(survivorDb)

            })

            profileViewModel.isLoading.observe(this, Observer {
                binding.loading.isVisible = it
            })
        }

        setContentView(binding.root)
    }

    @SuppressLint("SetTextI18n")
    private fun setViewData(survivorDb: Survivor) {
        binding.survivorId.text = "Superviviente # "+survivorDb.id
        binding.survivorInformation.survivorName.text = survivorDb.name+" "+survivorDb.surname
        binding.survivorInformation.survivorAge.text = survivorDb.age.toString()+" Años"
        binding.survivorInformation.survivorReports.text = survivorDb.infectedReports.toString()+" reportes"
        binding.survivorInformation.survivorPoints.text = "Puntaje inventario: "+ survivorDb.points.toString()
        binding.inventory.survivorLocation.text = survivorDb.latitude.toString()+", "+survivorDb.longitude.toString()
        if (survivorDb.isInfected.toString() == "true") {
            binding.survivorInformation.survivorInfected.text = "Infectado"
        }else {
            binding.survivorInformation.survivorInfected.text = "No infectado"
        }

        val items : List<Item> = survivorDb.items

        for (item in items){
            when(item.type){
                "Food" -> {
                    binding.inventory.foodPoints.text = "Puntaje: "+item.points.toString()
                    binding.inventory.foodItems.text = "Número de items: "+item.quantity.toString()
                }
                "Water" -> {
                    binding.inventory.waterPoints.text = "Puntaje: "+item.points.toString()
                    binding.inventory.waterItems.text = "Número de items: "+item.quantity.toString()
                }
                "Medication" -> {
                    binding.inventory.medicationPoints.text = "Puntaje: "+item.points.toString()
                    binding.inventory.medicationItems.text = "Número de items: "+item.quantity.toString()
                }
                "Ammunition" -> {
                    binding.inventory.ammunationPoints.text = "Puntaje: "+item.points.toString()
                    binding.inventory.ammunationItems.text = "Número de items: "+item.quantity.toString()
                }
            }
        }

        if (survivorDb.isInfected.toString() == "true") {
            binding.inventory.visibility = View.GONE
        }
    }

    private fun navigateTo() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToUpdateLocation(survivor: Survivor) {
        val intent = Intent(this, EditLocationActivity::class.java)
        intent.putExtra(EditLocationActivity.EXTRA_SURVIVOR, survivor)
        startActivity(intent)
    }

}