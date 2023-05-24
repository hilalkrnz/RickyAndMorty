package com.example.rickyandmorty.presentation.status.love

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rickyandmorty.R
import com.example.rickyandmorty.data.database.LoveCharacter
import com.example.rickyandmorty.databinding.FragmentLoveBinding
import com.example.rickyandmorty.databinding.LayoutAlertDialogBinding
import com.example.rickyandmorty.presentation.status.StatusUiState
import com.example.rickyandmorty.utility.fragmentViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoveFragment : Fragment(R.layout.fragment_love) {
    private val binding by fragmentViewBinding(FragmentLoveBinding::bind)
    private val viewModel by viewModels<LoveViewModel>()
    private val loveCharacterAdapter = LoveCharacterRecyclerViewAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeLoveCharacters()
        removeCharacterLoveList()
    }


    private fun setupRecyclerView() {
        binding.loveCharacterRecyclerview.adapter = loveCharacterAdapter
    }

    private fun observeLoveCharacters() {
        viewModel.getLoveCharacters.observe(viewLifecycleOwner) { statusUiState ->
           when(statusUiState) {
               is StatusUiState.Failure -> {
                   Toast.makeText(
                       requireContext(),
                       getString(statusUiState.message),
                       Toast.LENGTH_LONG
                   ).show()
               }
               is StatusUiState.Loading -> {}
               is StatusUiState.Success -> {
                   statusUiState.data?.let { handleFavoriteCharacters(it) }
               }
           }
        }
    }

    private fun handleFavoriteCharacters(data: List<LoveCharacter>) {
        loveCharacterAdapter.updateList(data)
    }

    private fun removeCharacterLoveList() {
        loveCharacterAdapter.setOnItemLongClickListener {loveCharacter ->
            showAlertDialog(loveCharacter)
        }
    }

    private fun showAlertDialog(loveCharacter: LoveCharacter) {
        val alertDialogView = LayoutAlertDialogBinding.inflate(LayoutInflater.from(requireContext()))
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(alertDialogView.root)
            .create()

        alertDialog.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        alertDialogView.buttonYes.setOnClickListener {
            viewModel.removeFromLove(loveCharacter.characterId.toString())
            alertDialog.cancel()
        }

        alertDialogView.buttonNo.setOnClickListener {
            alertDialog.cancel()
        }
    }

}