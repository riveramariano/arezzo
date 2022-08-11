package com.arezzo.ui.sucursal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.arezzo.R
import com.arezzo.databinding.FragmentAddSucursalBinding
import com.arezzo.model.Sucursal
import com.arezzo.viewmodel.SucursalViewModel

class AddSucursalFragment : Fragment() {
    private lateinit var sucursalViewModel: SucursalViewModel

    private var _binding: FragmentAddSucursalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddSucursalBinding.inflate(inflater, container, false)

        sucursalViewModel = ViewModelProvider(this).get(SucursalViewModel::class.java)

        // binding.btnAgregarLibro.setOnClickListener { addLibro() }

        return binding.root
    }

    /*
    private fun addSucursal() {
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
            var sucursal = Sucursal(
                "",
                gerente,
                telefono,
                ""
            )
            sucursalViewModel.addSucursal(sucursal)
            Toast.makeText(requireContext(), "Sucursal Agregada", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Faltan Datos", Toast.LENGTH_SHORT).show()
            return
        }
        findNavController().navigate(R.id.action_addSucursalFragment_to_nav_sucursal)
    }
     */
}