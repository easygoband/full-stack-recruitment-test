package com.fernando.zssn.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.fernando.zssn.databinding.ActivityNewSurvivorBinding
import com.fernando.zssn.data.network.request.ItemRequest
import com.fernando.zssn.core.ApiClient
import com.fernando.zssn.data.network.request.SurvivorRequest
import kotlinx.android.synthetic.main.form_new_survivor.view.*
import kotlinx.coroutines.launch

class NewSurvivorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityNewSurvivorBinding.inflate(layoutInflater)

        binding.formNewSurvivor.cancelSurvivorButton.setOnClickListener {
            navigateToHome()
        }

        nameFocusListener(binding)
        surnameFocusListener(binding)
        ageFocusListener(binding)
        latitudeFocusListener(binding)
        longitudeFocusListener(binding)
        waterFocusListener(binding)
        foodFocusListener(binding)
        medicationFocusListener(binding)
        ammunitionFocusListener(binding)

        binding.formNewSurvivor.saveSurvivorButton.setOnClickListener {
            if (
                binding.formNewSurvivor.latitudeInputSurvivor.text.toString().isEmpty() ||
                binding.formNewSurvivor.longitudeInputSurvivor.text.toString().isEmpty() ||
                binding.formNewSurvivor.nameInput.text.toString().isEmpty() ||
                binding.formNewSurvivor.surnameInput.text.toString().isEmpty() ||
                binding.formNewSurvivor.ageInput.text.toString().isEmpty() ||
                binding.formNewSurvivor.waterQuantityInput.text.toString().isEmpty() ||
                binding.formNewSurvivor.foodQuantityInput.text.toString().isEmpty() ||
                binding.formNewSurvivor.medicationQuantityInput.text.toString().isEmpty() ||
                binding.formNewSurvivor.ammunitionQuantityInput.text.toString().isEmpty()
            ) {
                Toast.makeText(this@NewSurvivorActivity, "Error de validación, revisa la información", Toast.LENGTH_SHORT).show()
            }else{
                val builder = AlertDialog.Builder(this@NewSurvivorActivity)
                builder.setMessage("¿Quieres continuar con esta operación?")
                    .setCancelable(false)
                    .setPositiveButton("Sí, quiero continuar") { dialog, id ->
                        lifecycleScope.launch{
                            val survivorRequest = SurvivorRequest()

                            val itemRequestList: MutableList<ItemRequest> = mutableListOf()

                            val waterRequest = ItemRequest()
                            val foodRequest = ItemRequest()
                            val medicationRequest = ItemRequest()
                            val ammunitionRequest = ItemRequest()

                            waterRequest.type = "Water"
                            waterRequest.quantity = binding.formNewSurvivor.waterQuantityInput.text.toString()

                            foodRequest.type = "Food"
                            foodRequest.quantity = binding.formNewSurvivor.foodQuantityInput.text.toString()

                            medicationRequest.type = "Medication"
                            medicationRequest.quantity = binding.formNewSurvivor.medicationQuantityInput.text.toString()

                            ammunitionRequest.type = "Ammunition"
                            ammunitionRequest.quantity = binding.formNewSurvivor.ammunitionQuantityInput.text.toString()

                            itemRequestList.add(waterRequest)
                            itemRequestList.add(foodRequest)
                            itemRequestList.add(medicationRequest)
                            itemRequestList.add(ammunitionRequest)

                            survivorRequest.name = binding.formNewSurvivor.nameInput.text.toString()
                            survivorRequest.surname = binding.formNewSurvivor.surnameInput.text.toString()
                            survivorRequest.age = binding.formNewSurvivor.ageInput.text.toString()
                            survivorRequest.latitude = binding.formNewSurvivor.latitudeInputSurvivor.text.toString()
                            survivorRequest.longitude = binding.formNewSurvivor.longitudeInputSurvivor.text.toString()
                            survivorRequest.items = itemRequestList

                            val survivorResponse = ApiClient.survivorService.createSurvivor(survivorRequest)

                            if (survivorResponse.code == 201){
                                navigateToHome()
                            }else{
                                Toast.makeText(this@NewSurvivorActivity, "Ocurrió un error inesperado", Toast.LENGTH_SHORT).show()
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
        setContentView(binding.root)
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun nameFocusListener(binding: ActivityNewSurvivorBinding) {
        binding.formNewSurvivor.nameInput.setOnFocusChangeListener{ _, focused ->
            if (!focused) {
                val text = binding.formNewSurvivor.nameInput.text.toString()
                binding.formNewSurvivor.nameInputContainer.helperText = validEmptyInput(text)
            }
        }
    }

    private fun surnameFocusListener(binding: ActivityNewSurvivorBinding) {
        binding.formNewSurvivor.surnameInput.setOnFocusChangeListener{ _, focused ->
            if (!focused) {
                val text = binding.formNewSurvivor.surnameInput.text.toString()
                binding.formNewSurvivor.surnameInputContainer.helperText = validEmptyInput(text)
            }
        }
    }

    private fun ageFocusListener(binding: ActivityNewSurvivorBinding) {
        binding.formNewSurvivor.ageInput.setOnFocusChangeListener{ _, focused ->
            if (!focused) {
                val text = binding.formNewSurvivor.ageInput.text.toString()
                binding.formNewSurvivor.ageInputContainer.helperText = validEmptyInput(text)
            }
        }
    }

    private fun latitudeFocusListener(binding: ActivityNewSurvivorBinding) {
        binding.formNewSurvivor.latitudeInputSurvivor.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                val text = binding.formNewSurvivor.latitudeInputSurvivor.text.toString()
                binding.formNewSurvivor.latitudeInputSurvivorContainer.helperText = validEmptyInput(text)
            }
        }
    }

    private fun longitudeFocusListener(binding: ActivityNewSurvivorBinding) {
        binding.formNewSurvivor.longitudeInputSurvivor.setOnFocusChangeListener{ _, focused ->
            if (!focused) {
                val text = binding.formNewSurvivor.longitudeInputSurvivor.text.toString()
                binding.formNewSurvivor.longitudeInputSurvivorContainer.helperText = validEmptyInput(text)
            }
        }
    }

    private fun waterFocusListener(binding: ActivityNewSurvivorBinding) {
        binding.formNewSurvivor.waterQuantityInput.setOnFocusChangeListener{ _, focused ->
            if (!focused) {
                val text = binding.formNewSurvivor.waterQuantityInput.text.toString()
                binding.formNewSurvivor.waterQuantityInputContainer.helperText = validEmptyInput(text)
            }
        }
    }

    private fun foodFocusListener(binding: ActivityNewSurvivorBinding) {
        binding.formNewSurvivor.foodQuantityInput.setOnFocusChangeListener{ _, focused ->
            if (!focused) {
                val text = binding.formNewSurvivor.foodQuantityInput.text.toString()
                binding.formNewSurvivor.foodQuantityInputContainer.helperText = validEmptyInput(text)
            }
        }
    }

    private fun medicationFocusListener(binding: ActivityNewSurvivorBinding) {
        binding.formNewSurvivor.medicationQuantityInput.setOnFocusChangeListener{ _, focused ->
            if (!focused) {
                val text = binding.formNewSurvivor.medicationQuantityInput.text.toString()
                binding.formNewSurvivor.medicationQuantityInputContainer.helperText = validEmptyInput(text)
            }
        }
    }

    private fun ammunitionFocusListener(binding: ActivityNewSurvivorBinding) {
        binding.formNewSurvivor.ammunitionQuantityInput.setOnFocusChangeListener{ _, focused ->
            if (!focused) {
                val text = binding.formNewSurvivor.ammunitionQuantityInput.text.toString()
                binding.formNewSurvivor.ammunitionQuantityInputContainer.helperText = validEmptyInput(text)
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