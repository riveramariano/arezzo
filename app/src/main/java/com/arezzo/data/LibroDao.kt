package com.arezzo.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.arezzo.model.Libro

class LibroDao {

    // Inicialización de variables
    private var codigoUsuario: String
    private var firestore: FirebaseFirestore
    private var arezzoApp = "arezzoApp"
    private var miColeccion = "libros"

    init {
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "${usuario}"

        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    // Obtener la lista de libros de Firestore
    fun getAllData(): MutableLiveData<List<Libro>> {
        val listaLibros = MutableLiveData<List<Libro>>()
        firestore
            .collection(arezzoApp)
            .document(codigoUsuario)
            .collection(miColeccion)
            .addSnapshotListener{ snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    var lista = ArrayList<Libro>()
                    val libros = snapshot.documents

                    libros.forEach {
                        val lugar = it.toObject(Libro::class.java)
                        if (lugar != null) {
                            lista.add(lugar)
                        }
                    }

                    listaLibros.value = lista
                }
            }
        return listaLibros
    }

    // Agregar un nuevo libro
    fun addLibro(libro: Libro) {
        var document: DocumentReference
        if (libro.id.isEmpty()) {
            // Es un libro nuevo / documento nuevo
            document = firestore
                .collection(arezzoApp)
                .document(codigoUsuario)
                .collection(miColeccion)
                .document()
            libro.id = document.id
        } else {
            document = firestore
                .collection(arezzoApp)
                .document(codigoUsuario)
                .collection(miColeccion)
                .document(libro.id)
        }

        val set = document.set(libro)
        set
            .addOnSuccessListener {
                Log.d("AddLibro", "Libro Agregado - " + libro.id)
            }
            .addOnCanceledListener {
                Log.d("AddLibro", "Libro No Agregado")
            }
    }

    // Actualizar un libro existente
    fun updateLibro(libro: Libro) {
        addLibro(libro)
    }

    // Eliminar un libro de la base de datos
    fun deleteLibro(libro: Libro) {
        if (libro.id.isNotEmpty()) {
            firestore
                .collection(arezzoApp)
                .document(codigoUsuario)
                .collection(miColeccion)
                .document(libro.id)
                .delete()
                .addOnSuccessListener {
                    Log.d("DeleteLibro", "Libro Eliminado - " + libro.id)
                }
                .addOnCanceledListener {
                    Log.d("DeleteLibro", "Libro No Eliminado")
                }
        }
    }
}