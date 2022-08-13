package com.arezzo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.arezzo.databinding.SucursalFilaBinding
import com.arezzo.model.Sucursal
import com.arezzo.ui.sucursal.SucursalFragmentDirections


class SucursalAdapter: RecyclerView.Adapter<SucursalAdapter.SucursalViewHolder>() {

    // Una lista para almacenar la información de las sucursales
    private var listaSucursales = emptyList<Sucursal>()

    inner class SucursalViewHolder(private val itemBinding: SucursalFilaBinding):
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(sucursal: Sucursal) {
            itemBinding.tvUbicacionSucursal.text = sucursal.ubicacion
            itemBinding.tvTelefonoSucursal.text = sucursal.telefono
            itemBinding.tvGerenteSucursal.text = sucursal.gerente
            itemBinding.vistaFilaSucursal.setOnClickListener {
                val action = SucursalFragmentDirections.actionNavSucursalToUpdateSucursalFragment(sucursal)
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SucursalViewHolder {
        val itemBinding = SucursalFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SucursalViewHolder(itemBinding)
    }

    // Dibuja la lista de sucursales
    override fun onBindViewHolder(holder: SucursalViewHolder, position: Int) {
        val sucursalActual = listaSucursales[position]
        holder.bind(sucursalActual)
    }

    override fun getItemCount(): Int {
        return listaSucursales.size
    }

    fun setData(sucursales: List<Sucursal>) {
        this.listaSucursales = sucursales
        notifyDataSetChanged() // Provoca que se redibuje la lista
    }
}