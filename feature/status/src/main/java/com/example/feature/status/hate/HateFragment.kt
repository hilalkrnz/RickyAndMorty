package com.example.feature.status.hate

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.core.ui.model.HateCharacter
import com.example.core.ui.utility.fragmentViewBinding
import com.example.feature.status.R
import com.example.feature.status.StatusUiState
import com.example.feature.status.databinding.FragmentHateBinding
import com.example.feature.status.databinding.LayoutAlertDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HateFragment : Fragment(R.layout.fragment_hate) {
    private val binding by fragmentViewBinding(FragmentHateBinding::bind)
    private val viewModel by viewModels<HateViewModel>()
    private val hateCharacterAdapter = HateCharacterRecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHateCharacters()

        setupRecyclerView()
        observeHateCharacters()
        removeCharacterHateList()

    }
    private fun setupRecyclerView() {
        binding.hateCharacterRecyclerview.adapter = hateCharacterAdapter
    }


    private fun observeHateCharacters() {
        viewModel.getHateCharacters.observe(viewLifecycleOwner) { statusUiState ->
            when (statusUiState) {
                is StatusUiState.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        getString(statusUiState.message),
                        Toast.LENGTH_LONG
                    ).show()
                }

                is StatusUiState.Loading -> {}
                is StatusUiState.Success -> {
                    statusUiState.data?.let { handleHateCharacters(it) }
                }
            }
        }
    }

    private fun handleHateCharacters(data: List<HateCharacter>) {
        hateCharacterAdapter.updateList(data)

    }

    private fun removeCharacterHateList() {
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
            println("Removed" + hateCharacter.characterName)
            alertDialog.cancel()
        }

        alertDialogView.buttonNo.setOnClickListener {
            alertDialog.cancel()
        }
    }
}