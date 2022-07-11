package com.arezzo.ui.libro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arezzo.R
import com.arezzo.databinding.FragmentLibroBinding
import com.arezzo.viewmodel.LibroViewModel

class LibroFragment : Fragment() {

    private lateinit var libroViewModel: LibroViewModel
    private var _binding: FragmentLibroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        libroViewModel = ViewModelProvider(this)[LibroViewModel::class.java]
        _binding = FragmentLibroBinding.inflate(inflater, container, false)
//        binding.fbAgregar.setOnClickListener {
//            findNavController().navigate(R.id.action_nav_lugar_to_addLugarFragment)
//        }
//
//        // Activar el RecyclerView
//        val lugarAdapter = LugarAdapter()
//        val reciclador = binding.reciclador
//        reciclador.adapter = lugarAdapter
//        reciclador.layoutManager = LinearLayoutManager(requireContext())
//
//        libroViewModel = ViewModelProvider(this)[LibroViewModel::class.java]
//        libroViewModel.getAllData.observe(viewLifecycleOwner) { libros ->
//            lugarAdapter.setData(libros)
//        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}