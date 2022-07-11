package com.arezzo.ui.libro

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

class AddLibroFragment : Fragment() {
    private lateinit var libroViewModel: LibroViewModel

    private var _binding: FragmentAddLibroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddLibroBinding.inflate(inflater, container, false)

        libroViewModel = ViewModelProvider(this).get(LibroViewModel::class.java)

        binding.btnAgregarLibro.setOnClickListener { addLibro() }

        return binding.root
    }

    private fun addLibro() {
        var nombre = binding.etNombre.text.toString()
        var unidades = binding.etUnidades.text.toString()
        var autor = binding.etAutor.text.toString()
        if (nombre.isNotEmpty() && unidades.isNotEmpty() && autor.isNotEmpty()) {
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
        }
        findNavController().navigate(R.id.action_addLibroFragment_to_nav_libro)
    }
}