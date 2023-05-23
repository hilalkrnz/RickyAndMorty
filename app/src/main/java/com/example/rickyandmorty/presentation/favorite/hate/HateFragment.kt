package com.example.rickyandmorty.presentation.favorite.hate

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rickyandmorty.R
import com.example.rickyandmorty.data.database.HateCharacter
import com.example.rickyandmorty.databinding.FragmentHateBinding
import com.example.rickyandmorty.databinding.LayoutAlertDialogBinding
import com.example.rickyandmorty.utility.fragmentViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HateFragment : Fragment(R.layout.fragment_hate) {

    private val binding by fragmentViewBinding(FragmentHateBinding::bind)
    private val viewModel by viewModels<HateViewModel>()
    private val hateCharacterAdapter by lazy { HateCharacterRecyclerViewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeHateCharacters()
        removeCharacterLoveList()
    }

    private fun setupRecyclerView() {
        binding.hateCharacterRecyclerview.adapter = hateCharacterAdapter
    }

    private fun observeHateCharacters() {
        viewModel.getHateCharacters.observe(viewLifecycleOwner) {
            handleHateCharacters(it)
        }
    }

    private fun handleHateCharacters(data: List<HateCharacter>) {
        hateCharacterAdapter.updateList(data)
    }

    private fun removeCharacterLoveList() {
        hateCharacterAdapter.setOnItemLongClickListener { hateCharacter ->
            showAlertDialog(hateCharacter)
        }
    }

    private fun showAlertDialog(hateCharacter: HateCharacter) {
        val alertDialogView =
            LayoutAlertDialogBinding.inflate(LayoutInflater.from(requireContext()))
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(alertDialogView.root)
            .create()

        alertDialog.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        alertDialogView.buttonYes.setOnClickListener {
            viewModel.removeFromHate(hateCharacter.characterId.toString())
            alertDialog.cancel()
        }

        alertDialogView.buttonNo.setOnClickListener {
            alertDialog.cancel()
        }
    }

}