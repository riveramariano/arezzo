package com.arezzo.ui.sucursal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arezzo.databinding.FragmentSucursalBinding
import com.arezzo.viewmodel.SucursalViewModel

class SucursalFragment : Fragment() {

    private lateinit var sucursalViewModel: SucursalViewModel
    private var _binding: FragmentSucursalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sucursalViewModel = ViewModelProvider(this)[SucursalViewModel::class.java]
        _binding = FragmentSucursalBinding.inflate(inflater, container, false)
//        binding.fbAgregarLibro.setOnClickListener {
//            findNavController().navigate(R.id.action_nav_sucursal_to_addSucursalFragment)
//        }

        // Activar el RecyclerView
        // val libroAdapter = LibroAdapter()
        // val reciclador = binding.reciclador
        // reciclador.adapter = libroAdapter
        // reciclador.layoutManager = LinearLayoutManager(requireContext())

        sucursalViewModel = ViewModelProvider(this)[SucursalViewModel::class.java]
        // sucursalViewModel.getAllData.observe(viewLifecycleOwner) { libros ->
            // libroAdapter.setData(libros)
        // }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}