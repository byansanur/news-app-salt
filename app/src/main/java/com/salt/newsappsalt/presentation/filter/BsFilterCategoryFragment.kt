package com.salt.newsappsalt.presentation.filter

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.salt.newsappsalt.R
import com.salt.newsappsalt.databinding.FragmentBsFilterCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BsFilterCategoryFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBsFilterCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBsFilterCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSetFilter.setOnClickListener {
                setFiltering()
            }
        }
    }

    private fun setFiltering() {
        val valueChip = binding.chipGroup.children.toList()
            .filter { (it as Chip).isChecked }
            .joinToString { (it as Chip).text }
        val navController = findNavController()
        navController.previousBackStackEntry?.savedStateHandle?.set("category", valueChip)
        dismiss()
    }

}