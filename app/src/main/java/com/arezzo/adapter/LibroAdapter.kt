package com.arezzo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.arezzo.databinding.LibroFilaBinding
import com.arezzo.model.Libro
import com.arezzo.ui.libro.LibroFragmentDirections

class LibroAdapter: RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {

    // Una lista para almacenar la información de los libros
    private var listaLibros = emptyList<Libro>()

    inner class LibroViewHolder(private val itemBinding: LibroFilaBinding):
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(libro: Libro) {
            itemBinding.tvNombreLibro.text = libro.nombre
            itemBinding.tvAutorLibro.text = libro.autor
            itemBinding.tvUnidadesLibro.text = libro.unidades.toString()
            itemBinding.vistaFila.setOnClickListener {
                val action = LibroFragmentDirections.actionNavLibroToUpdateLibroFragment(libro)
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val itemBinding = LibroFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibroViewHolder(itemBinding)
    }

    // Dibuja la lista de libros
    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libroActual = listaLibros[position]
        holder.bind(libroActual)
    }

    override fun getItemCount(): Int {
        return listaLibros.size
    }

    fun setData(libros: List<Libro>) {
        this.listaLibros = libros
        notifyDataSetChanged() // Provoca que se redibuje la lista
    }
}