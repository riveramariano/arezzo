package com.arezzo.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.arezzo.model.Sucursal

class SucursalDao {

    // Inicialización de variables
    private var codigoUsuario: String
    private var firestore: FirebaseFirestore
    private var arezzoApp = "arezzoApp"
    private var miColeccion = "sucursales"

    init {
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "${usuario}"

        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    // Obtener la lista de sucursales de Firestore
    fun getAllData(): MutableLiveData<List<Sucursal>> {
        val listaSucursales = MutableLiveData<List<Sucursal>>()
        firestore
            .collection(arezzoApp)
            .document(codigoUsuario)
            .collection(miColeccion)
            .addSnapshotListener{ snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    var lista = ArrayList<Sucursal>()
                    val sucursales = snapshot.documents

                    sucursales.forEach {
                        val sucursal = it.toObject(Sucursal::class.java)
                        if (sucursal != null) {
                            lista.add(sucursal)
                        }
                    }

                    listaSucursales.value = lista
                }
            }
        return listaSucursales
    }

    // Agregar un nueva sucursal
    fun addSucursal(sucursal: Sucursal) {
        var document: DocumentReference
        if (sucursal.id.isEmpty()) {
            // Es un sucursal nueva / documento nuevo
            document = firestore
                .collection(arezzoApp)
                .document(codigoUsuario)
                .collection(miColeccion)
                .document()
            sucursal.id = document.id
        } else {
            document = firestore
                .collection(arezzoApp)
                .document(codigoUsuario)
                .collection(miColeccion)
                .document(sucursal.id)
        }

        val set = document.set(sucursal)
        set
            .addOnSuccessListener {
                Log.d("AddSucursal", "Sucursal Agregado - " + sucursal.id)
            }
            .addOnCanceledListener {
                Log.d("AddSucursal", "Sucursal No Agregado")
            }
    }

    // Actualizar una sucursal existente
    fun updateSucursal(sucursal: Sucursal) {
        addSucursal(sucursal)
    }

    // Eliminar una sucursal de la base de datos
    fun deleteSucursal(sucursal: Sucursal) {
        if (sucursal.id.isNotEmpty()) {
            firestore
                .collection(arezzoApp)
                .document(codigoUsuario)
                .collection(miColeccion)
                .document(sucursal.id)
                .delete()
                .addOnSuccessListener {
                    Log.d("DeleteSucursal", "Sucursal Eliminada - " + sucursal.id)
                }
                .addOnCanceledListener {
                    Log.d("DeleteSucursal", "Sucursal No Eliminada")
                }
        }
    }
}