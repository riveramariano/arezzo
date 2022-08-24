package com.arezzo.ui.libro

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.arezzo.R
import com.arezzo.databinding.FragmentAddLibroBinding
import com.arezzo.model.Libro
import com.arezzo.viewmodel.LibroViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*

class AddLibroFragment : Fragment() {
    private lateinit var libroViewModel: LibroViewModel

    private var _binding: FragmentAddLibroBinding? = null
    private val binding get() = _binding!!
    private var imageUri: Uri?=null
    private var imageString: String =" "
    private var PICK_IMAGE_REQUEST =71
    private var firebaseStore: FirebaseStorage?= null
    private var storageReference: StorageReference?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddLibroBinding.inflate(inflater, container, false)

        libroViewModel = ViewModelProvider(this).get(LibroViewModel::class.java)

        binding.btnAgregarLibro.setOnClickListener { addLibro() }

        binding.btnEscogerLibro.setOnClickListener{
            launchGallery()
        }

        binding.btnSubirPortada.setOnClickListener {
            SubirImagen()
        }

        return binding.root
    }

    private fun addLibro() {
        var nombre = binding.etNombre.text.toString()
        var unidades = binding.etUnidades.text.toString()
        var autor = binding.etAutor.text.toString()
        if (nombre.isNotEmpty() && unidades.isNotEmpty() && autor.isNotEmpty()) {
            if (nombre.length > 20) {
                Toast.makeText(requireContext(), "Nombre Libro muy Largo", Toast.LENGTH_SHORT).show()
                return
            }
            if (unidades.toInt() > 9999) {
                Toast.makeText(requireContext(), "Màximo 9999 Unidades", Toast.LENGTH_SHORT).show()
                return
            }
            if (autor.length > 25) {
                Toast.makeText(requireContext(), "Nombre Autor muy Largo", Toast.LENGTH_SHORT).show()
                return
            }
            var libro = Libro(
                "",
                nombre,
                unidades.toInt(),
                autor,
                "",
                ""
            )
            libroViewModel.addLibro(libro)
            Toast.makeText(requireContext(), "Libro Agregado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Faltan Datos", Toast.LENGTH_SHORT).show()
            return
        }
        findNavController().navigate(R.id.action_addLibroFragment_to_nav_libro)
    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Escoger Imagen"), PICK_IMAGE_REQUEST);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        this.imageUri = data?.data
        binding.PreviewLibrosAdd.setImageURI(this.imageUri)
    }

    private fun SubirImagen() {
        val name = binding.etNombre.toString()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = "_" + formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$name$fileName")

        this.imageUri?.let {
            storageReference
                .putFile(it)
                .addOnFailureListener {
                    Toast.makeText(requireContext(),getString(R.string.fail_save),Toast.LENGTH_SHORT).show()
                }.addOnSuccessListener { taskSnapshot ->
                    binding.PreviewLibrosAdd.setImageURI(null)
                    Toast.makeText(requireContext(),getString(R.string.success_save),Toast.LENGTH_SHORT).show()
                }
        }
    }
}