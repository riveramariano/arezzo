package com.arezzo.ui.libro

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arezzo.R
import com.arezzo.databinding.FragmentUpdateLibroBinding
import com.arezzo.model.Libro
import com.arezzo.viewmodel.LibroViewModel

class UpdateLibroFragment : Fragment() {
    private var _binding: FragmentUpdateLibroBinding? = null
    private val binding get() = _binding!!
    private lateinit var libroViewModel: LibroViewModel

    private val args by navArgs<UpdateLibroFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateLibroBinding.inflate(inflater, container, false)

        libroViewModel = ViewModelProvider(this).get(LibroViewModel::class.java)

        binding.etNombre.setText(args.libro.nombre)
        binding.etUnidades.setText(args.libro.unidades.toString())
        binding.etAutor.setText(args.libro.autor)

        binding.btnActualizarLibro.setOnClickListener { updateLibro() }



        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteLibro()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateLibro() {
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
                args.libro.id,
                nombre,
                unidades.toInt(),
                autor,
                "",
                ""
            )
            libroViewModel.updateLibro(libro)
            Toast.makeText(requireContext(), "Libro Actualizado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Faltan Datos", Toast.LENGTH_SHORT).show()
            return
        }
        findNavController().navigate(R.id.action_updateLibroFragment_to_nav_libro)
    }

    private fun deleteLibro() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)) { _, _ ->
            libroViewModel.deleteLibro(args.libro)
            Toast.makeText(requireContext(), getString(R.string.deleted) + " ${args.libro.nombre}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateLibroFragment_to_nav_libro)
        }

        builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
        builder.setTitle(R.string.delete)
        builder.setMessage(getString(R.string.seguro_borrar) + " ${args.libro.nombre}?")
        builder.create().show()
    }
}