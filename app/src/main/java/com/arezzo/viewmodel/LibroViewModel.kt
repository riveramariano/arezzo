package com.arezzo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.arezzo.data.LibroDao
import com.arezzo.model.Libro
import com.arezzo.repository.LibroRepository

class LibroViewModel(application: Application) : AndroidViewModel(application) {
    val getAllData: MutableLiveData<List<Libro>>

    private val repository: LibroRepository = LibroRepository(LibroDao())

    init {
        getAllData = repository.getAllData
    }

    fun addLibro(libro: Libro) {
        repository.addLibro(libro)
    }

    fun updateLibro(libro: Libro) {
        repository.updateLibro(libro)
    }

    fun deleteLibro(libro: Libro) {
        repository.deleteLibro(libro)
    }
}