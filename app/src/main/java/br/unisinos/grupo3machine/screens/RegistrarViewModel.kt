package br.unisinos.grupo3machine.screens

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.unisinos.grupo3machine.MachineRepository
import br.unisinos.grupo3machine.models.Machine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class RegistrarViewModel : ViewModel() {
    private val mongoRepository = MachineRepository()
    private val async = viewModelScope

    /**
     * This object reflects ui state, it can be modified from this
     * class but is observed and cannot be modified by the UI directly
     * */
    private val _machine = MutableStateFlow(Machine())
    val machine = _machine

    fun updateName(newName: String) {
        _machine.value = _machine.value.copy(name = newName)
    }

    fun save() {
        async.launch {
            mongoRepository.save(
                _machine.value
            )
        }
    }

    /**
     * Image is downloaded as ByteArray and is converted to Bitmap
     */
    fun getQrCode() {
        async.launch {
            val response : ByteArray = mongoRepository.getQrCode(machine.value.name)
            _machine.value = _machine.value.copy(
                qrCode = response
            )
        }
    }

    fun transformToImage(qrCode: ByteArray): ImageBitmap {
       return BitmapFactory.decodeByteArray(qrCode, 0,qrCode.size).asImageBitmap()
    }
}