package com.arezzo.ui.sucursal

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arezzo.R
import com.arezzo.databinding.FragmentUpdateSucursalBinding
import com.arezzo.model.Sucursal
import com.arezzo.viewmodel.SucursalViewModel

class UpdateSucursalFragment : Fragment() {
    private var _binding: FragmentUpdateSucursalBinding? = null
    private val binding get() = _binding!!
    private lateinit var sucursalViewModel: SucursalViewModel

    private val args by navArgs<UpdateSucursalFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateSucursalBinding.inflate(inflater, container, false)

        sucursalViewModel = ViewModelProvider(this).get(SucursalViewModel::class.java)

        binding.etUbicacion.setText(args.sucursal.ubicacion)
        binding.etGerente.setText(args.sucursal.gerente)
        binding.etTelefono.setText(args.sucursal.telefono)

        binding.btnActualizarSucursal.setOnClickListener { updateSucursal() }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteSucursal()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateSucursal() {
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
                args.sucursal.id,
                ubicacion,
                gerente,
                telefono,
                ""
            )
            sucursalViewModel.updateSucursal(sucursal)
            Toast.makeText(requireContext(), "Sucursal Actualiza", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Faltan Datos", Toast.LENGTH_SHORT).show()
            return
        }
        findNavController().navigate(R.id.action_updateSucursalFragment_to_nav_sucursal)
    }

    private fun deleteSucursal() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)) { _, _ ->
            sucursalViewModel.deleteSucursal(args.sucursal)
            Toast.makeText(requireContext(), getString(R.string.deleted_sucursal) + " de ${args.sucursal.ubicacion}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateSucursalFragment_to_nav_sucursal)
        }

        builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
        builder.setTitle(R.string.delete_sucursal)
        builder.setMessage(getString(R.string.seguro_borrar_sucursal) + "?")
        builder.create().show()
    }
}