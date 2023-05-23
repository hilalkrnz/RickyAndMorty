package com.example.rickyandmorty.presentation.favorite.love

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rickyandmorty.R
import com.example.rickyandmorty.data.database.FavoriteCharacter
import com.example.rickyandmorty.databinding.FragmentLoveBinding
import com.example.rickyandmorty.databinding.LayoutAlertDialogBinding
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
        observeFavoriteCharacters()
        removeCharacterLoveList()
    }


    private fun setupRecyclerView() {
        binding.loveCharacterRecyclerview.adapter = loveCharacterAdapter
    }

    private fun observeFavoriteCharacters() {
        viewModel.getFavoriteCharacters.observe(viewLifecycleOwner) {
            handleFavoriteCharacters(it)
        }
    }

    private fun handleFavoriteCharacters(data: List<FavoriteCharacter>) {
        loveCharacterAdapter.updateList(data)
    }

    private fun removeCharacterLoveList() {
        loveCharacterAdapter.setOnItemLongClickListener {favoriteCharacter ->
            showAlertDialog(favoriteCharacter)
        }
    }

    private fun showAlertDialog(favoriteCharacter: FavoriteCharacter) {
        val alertDialogView = LayoutAlertDialogBinding.inflate(LayoutInflater.from(requireContext()))
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(alertDialogView.root)
            .create()

        alertDialog.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        alertDialogView.buttonYes.setOnClickListener {
            viewModel.removeFromFavorite(favoriteCharacter.characterId.toString())
            alertDialog.cancel()
        }

        alertDialogView.buttonNo.setOnClickListener {
            alertDialog.cancel()
        }
    }

}