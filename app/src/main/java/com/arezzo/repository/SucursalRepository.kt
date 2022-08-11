package com.arezzo.repository

import androidx.lifecycle.MutableLiveData
import com.arezzo.data.SucursalDao
import com.arezzo.model.Sucursal

class SucursalRepository(private val sucursalDao: SucursalDao) {
    val getAllData: MutableLiveData<List<Sucursal>> = sucursalDao.getAllData()

    fun addSucursal(sucursal: Sucursal) {
        sucursalDao.addSucursal(sucursal)
    }

    fun updateSucursal(sucursal: Sucursal) {
        sucursalDao.updateSucursal(sucursal)
    }

    fun deleteLibro(sucursal: Sucursal) {
        sucursalDao.deleteSucursal(sucursal)
    }
}