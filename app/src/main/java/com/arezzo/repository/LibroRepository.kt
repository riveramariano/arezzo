package com.arezzo.repository

import androidx.lifecycle.MutableLiveData
import com.arezzo.data.LibroDao
import com.arezzo.model.Libro

class LibroRepository(private val libroDao: LibroDao) {
    val getAllData: MutableLiveData<List<Libro>> = libroDao.getAllData()

    fun addLibro(libro: Libro) {
        libroDao.addLibro(libro)
    }

    fun updateLibro(libro: Libro) {
        libroDao.updateLibro(libro)
    }

    fun deleteLibro(libro: Libro) {
        libroDao.deleteLibro(libro)
    }
}