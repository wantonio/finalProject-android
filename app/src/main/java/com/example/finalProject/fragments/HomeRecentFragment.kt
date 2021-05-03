package com.example.finalProject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalProject.adapter.ContactAdapter
import com.example.finalProject.databinding.HomeAllFragmentBinding
import com.example.finalProject.models.Contact
import androidx.fragment.app.Fragment



class HomeRecentFragment : Fragment() {
    private var _binding: HomeAllFragmentBinding? = null
    private val binding get() = _binding!!
    // TODO Creamos variable de tipo adapter (ContactAdapter)
    private val adapter = ContactAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeAllFragmentBinding.inflate(inflater, container, false)
        binding.contactsRecyclerView.adapter = adapter
        adapter.contacts = getDummyContacts()
        return binding.root
    }




    private fun getDummyContacts() : List<Contact> {
        return mutableListOf(
            Contact("Pokemon reciente 1", "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c325.png"),
            Contact("Pokemon reciente 2", "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c32a.png"),
            Contact("Pokemon reciente 3", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c31a.png"),
            Contact("Pokemon reciente 4", "https://pm1.narvii.com/6270/a66f81df957afcda3bbb6b1723b5b3928436a8dc_hq.jpg"),
            Contact("Pokemon reciente 5", "https://i.pinimg.com/originals/4d/88/eb/4d88ebc2b8b3a26b8d698ff189f340b3.png")
        )
    }
}