package br.unisinos.grupo3machine.screens

import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.unisinos.grupo3machine.MachineRepository
import br.unisinos.grupo3machine.models.Machine
import br.unisinos.grupo3machine.models.ResponseMachine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class ConsultarViewModel : ViewModel() {
    private val async = viewModelScope

    /**
     * This object reflects ui state, it can be modified from this
     * class but is observed and cannot be modified by the UI directly
     * */
    private val _machines = MutableStateFlow(emptyList<Machine>())
    val machines = _machines

    init {
        async.launch {
            val response = MachineRepository().getAll()
            _machines.value = convertImage(response)
        }
    }

    private fun base64ToByteArray(base64String: String): ByteArray? {
        return Base64.decode(base64String, Base64.DEFAULT)
    }

    private fun convertImage(response: List<ResponseMachine>): List<Machine> {
        val newMachines = mutableListOf<Machine>()
        response.forEach { machine ->
            newMachines.add(
                Machine(
                    name = machine.name,
                    qrCode = base64ToByteArray(machine.qrCode!!),
                    dateInserted = machine.dateInserted
                )
            )

        }
        return newMachines
    }
}