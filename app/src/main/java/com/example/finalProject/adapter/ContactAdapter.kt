package com.example.finalProject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.finalProject.models.Contact
import com.example.finalProject.databinding.ListCellBinding
import com.example.finalProject.extensions.mapToVisibility
import com.squareup.picasso.Picasso

//    TODO Paso 4, Creamos el Adapter
class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private var _binding: ListCellBinding? = null
    private val binding get() = _binding!!

    //TODO Paso 5, creamos el set para la lista que va a alimentar el adapter (datasource)
    var contacts: List<Contact> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(name: String, imageUrl: String) {
            binding.textView.text = name




            Picasso.get().load(imageUrl).into(binding.imageView)
        }
    }

    //TODO Metodo encargado de inflar el layout/xml en cada celda
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        _binding = ListCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding.root)
    }

    // TODO Metodo encargado de pintar los datos para cada celda, dependiendo de la posicion
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        // Llamamos al metodo bind para la posicion especifica
        val contact = contacts[position]
        holder.bind(contact.name, contact.imageURL)
    }

    // TODO Metodo que determina la cantidad de elementos en la lista
    override fun getItemCount(): Int = contacts.size
}