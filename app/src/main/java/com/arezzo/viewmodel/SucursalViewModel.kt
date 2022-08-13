package com.arezzo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.arezzo.data.SucursalDao
import com.arezzo.model.Sucursal
import com.arezzo.repository.SucursalRepository

class SucursalViewModel(application: Application) : AndroidViewModel(application) {
    val getAllData: MutableLiveData<List<Sucursal>>

    private val repository: SucursalRepository = SucursalRepository(SucursalDao())

    init {
        getAllData = repository.getAllData
    }

    fun addSucursal(sucursal: Sucursal) {
        repository.addSucursal(sucursal)
    }

    fun updateSucursal(sucursal: Sucursal) {
        repository.updateSucursal(sucursal)
    }

    fun deleteSucursal(sucursal: Sucursal) {
        repository.deleteSucursal(sucursal)
    }
}