package com.fernando.zssn

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.fernando.zssn.databinding.ProfileActivityBinding
import com.fernando.zssn.model.Item
import com.fernando.zssn.model.LocationRequest
import com.fernando.zssn.model.Survivor
import com.fernando.zssn.model.SurvivorClient
import kotlinx.android.synthetic.main.form_edit_location.view.*
import kotlinx.android.synthetic.main.item_location.view.*
import kotlinx.android.synthetic.main.item_profile_inventory.view.*
import kotlinx.android.synthetic.main.item_report.view.*
import kotlinx.android.synthetic.main.profile_activity.view.*
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SURVIVOR = "ProfileActivity:survivor"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ProfileActivityBinding.inflate(layoutInflater)

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
                        lifecycleScope.launch{
                            val survivorResponse = SurvivorClient.service.reportSurvivor(survivor.id)

                            if (survivorResponse.code == 200){
                                finish()
                                startActivity(intent)
                            }else{
                                Toast.makeText(this@ProfileActivity, "Ocurrió un error inesperado", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    .setNegativeButton("No, cancelar") { dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }
        }

        if (survivor != null) {


            lifecycleScope.launch {
                val survivorDb = SurvivorClient.service.fetchSingleSurvivor(survivor.id)

                binding.survivorId.text = "Superviviente # "+survivorDb.data.id
                binding.survivorInformation.survivorName.text = survivorDb.data.name+" "+survivorDb.data.surname
                binding.survivorInformation.survivorAge.text = survivorDb.data.age.toString()+" Años"
                binding.survivorInformation.survivorReports.text = survivorDb.data.infectedReports.toString()+" reportes"
                binding.survivorInformation.survivorPoints.text = "Puntaje inventario: "+ survivorDb.data.points.toString()
                binding.inventory.survivorLocation.text = survivorDb.data.latitude.toString()+", "+survivorDb.data.longitude.toString()
                if (survivorDb.data.isInfected.toString() == "true") {
                    binding.survivorInformation.survivorInfected.text = "Infectado"
                }else {
                    binding.survivorInformation.survivorInfected.text = "No infectado"
                }

                val items : List<Item> = survivorDb.data.items

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

                if (survivorDb.data.isInfected.toString() == "true") {
                    binding.inventory.visibility = View.GONE
                }

            }
        }



        setContentView(binding.root)
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