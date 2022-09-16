package com.fernando.zssn

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.fernando.zssn.databinding.ActivityEditLocationBinding
import com.fernando.zssn.model.LocationRequest
import com.fernando.zssn.model.Survivor
import com.fernando.zssn.model.SurvivorClient
import kotlinx.android.synthetic.main.form_edit_location.view.*
import kotlinx.coroutines.launch

class EditLocationActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SURVIVOR = "EditLocationActivity:survivor"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditLocationBinding.inflate(layoutInflater)

        val survivor = intent.getParcelableExtra<Survivor>(EXTRA_SURVIVOR)

        binding.backButton.setOnClickListener{
            if (survivor != null) {
                navigateToProfile(survivor)
            }
        }

        binding.formEditLocation.cancelLocationButton.setOnClickListener{
            if (survivor != null) {
                navigateToProfile(survivor)
            }
        }

        latitudeFocusListener(binding)
        longitudeFocusListener(binding)

        binding.formEditLocation.saveLocationButton.setOnClickListener{

            if (binding.formEditLocation.latitudeInput.text.toString().isEmpty() || binding.formEditLocation.longitudeInput.text.toString().isEmpty()) {
                Toast.makeText(this@EditLocationActivity, "Error de validación, revisa la información", Toast.LENGTH_SHORT).show()
            }else{
                val builder = AlertDialog.Builder(this@EditLocationActivity)
                builder.setMessage("¿Quieres continuar con esta operación?")
                    .setCancelable(false)
                    .setPositiveButton("Sí, quiero continuar") { dialog, id ->
                        if (survivor != null) {
                            lifecycleScope.launch{
                                val locationRequest = LocationRequest()
                                locationRequest.latitude = binding.formEditLocation.latitudeInput.text.toString()
                                locationRequest.longitude = binding.formEditLocation.longitudeInput.text.toString()

                                val survivorResponse = SurvivorClient.service.updateSurvivorLocation(survivor.id, locationRequest)

                                if (survivorResponse.code == 200){
                                    navigateToProfile(survivor)
                                }else{
                                    Toast.makeText(this@EditLocationActivity, "Ocurrió un error inesperado", Toast.LENGTH_SHORT).show()
                                }
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
                if (survivorDb.data.isInfected.toString() == "1") {
                    binding.survivorInformation.survivorInfected.text = "Infectado"
                }else {
                    binding.survivorInformation.survivorInfected.text = "No infectado"
                }
                binding.formEditLocation.latitudeInput.setText(survivorDb.data.latitude.toString())
                binding.formEditLocation.longitudeInput.setText(survivorDb.data.longitude.toString())
            }
        }

        setContentView(binding.root)
    }

    private fun navigateToProfile(survivor: Survivor) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra(ProfileActivity.EXTRA_SURVIVOR, survivor)
        startActivity(intent)
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun latitudeFocusListener(binding: ActivityEditLocationBinding) {
        binding.formEditLocation.latitudeInput.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                val text = binding.formEditLocation.latitudeInput.text.toString()
                binding.formEditLocation.latitudeInputContainer.helperText = validEmptyInput(text)
            }
        }
    }

    private fun longitudeFocusListener(binding: ActivityEditLocationBinding) {
        binding.formEditLocation.longitudeInput.setOnFocusChangeListener{ _, focused ->
            if (!focused) {
                val text = binding.formEditLocation.longitudeInput.text.toString()
                binding.formEditLocation.longitudeInputContainer.helperText = validEmptyInput(text)
            }
        }
    }

    private fun validEmptyInput(string: String): String?
    {
        if (TextUtils.isEmpty(string)) {
            return "Este campo es requerido"
        }else{
            return null
        }
    }
}