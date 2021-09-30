package com.example.android_task4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android_task4.databinding.FragmentAddUpgradeBinding

class AddEditFragment:Fragment() {
    private var _binding: FragmentAddUpgradeBinding? = null
    private val binding: FragmentAddUpgradeBinding get() = requireNotNull(_binding)


    private lateinit var bookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddUpgradeBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        /*binding.buttonAddUpgrade.setOnClickListener {
           addDataToDB()
        }*/

        binding.buttonClose.setOnClickListener {
            findNavController().navigate(R.id.action_addEditFragment_to_fragmentMainRV)
    }
    }


    private fun addDataToDB(){
        if (inputChek(
                binding.titleFieldFr.text.toString(),
                binding.authorFieldFr.text.toString(),
                binding.releaseYearFieldFr.text.toString())
        ) {
            val title = binding.titleFieldFr.text.toString()
            val author = binding.authorFieldFr.text.toString()
            val year = binding.releaseYearFieldFr.text.toString()
            val book = Book(title,author,year)
            bookViewModel.addBook(book)
            Toast.makeText(requireContext(),
                R.string.data_successfully_added,
                Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_addEditFragment_to_fragmentMainRV)
        } else Toast.makeText(requireContext(),
            "Please fill out all fields",
            Toast.LENGTH_LONG).show()

    }

    private fun inputChek(title: String, author: String, year: String): Boolean{
        return !(title.isBlank() || author.isBlank() || year.isBlank())
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}