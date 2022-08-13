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

        binding.btnAgregarSucursal.setOnClickListener { addSucursal() }

        return binding.root
    }

    private fun addSucursal() {
        var ubicacion = binding.etUbicacion.text.toString()
        var gerente = binding.etGerente.text.toString()
        var telefono = binding.etTelefono.text.toString()
        if (ubicacion.isNotEmpty() && gerente.isNotEmpty() && telefono.isNotEmpty()) {
            if (ubicacion.length > 20) {
                Toast.makeText(requireContext(), "Ubicación muy Larga", Toast.LENGTH_SHORT).show()
                return
            }
            if (telefono.length < 8 || telefono.length > 8) {
                Toast.makeText(requireContext(), "Teléfono no Válido", Toast.LENGTH_SHORT).show()
                return
            }
            if (gerente.length > 25) {
                Toast.makeText(requireContext(), "Nombre Gerente muy Largo", Toast.LENGTH_SHORT).show()
                return
            }
            var sucursal = Sucursal(
                "",
                ubicacion,
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
}