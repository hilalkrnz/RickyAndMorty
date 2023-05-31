package com.example.feature.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core.ui.MainViewModel
import com.example.core.ui.model.CharacterUiData
import com.example.core.ui.utility.fragmentViewBinding
import com.example.feature.detail.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by fragmentViewBinding(FragmentDetailBinding::bind)
    private val viewModel by viewModels<DetailViewModel>()
    private val activityViewModel by activityViewModels<MainViewModel>()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCharacterById(args.characterArgument.id.toString())

        setBottomBarVisibility()
        initCharacterUiComponent()
        initDetailToolbarUiComponent()
        observeDetailState()

        toggleLoveStatus()
        toggleHateStatus()

    }


    private fun setBottomBarVisibility() {
        activityViewModel.setBottomBarVisibility(false)
    }


    private fun initCharacterUiComponent() {
        binding.characterComponent.setGradientViewAndCharacterNameVisibility(false)
    }

    private fun initDetailToolbarUiComponent() {
        binding.detailToolbarComponent.setOnlyBackIconVisibility(true)
        binding.detailToolbarComponent.setOnBackIconClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeDetailState() {
        viewModel.rickAndMortyDetailUiState.observe(viewLifecycleOwner) { detailUiState ->
            when (detailUiState) {
                is DetailUiState.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        getString(detailUiState.message),
                        Toast.LENGTH_LONG
                    ).show()
                }

                is DetailUiState.Loading -> {}
                is DetailUiState.Success -> {
                    handleSuccessDetailUiState(detailUiState.data)
                }

                DetailUiState.Loading -> Log.d("TAG", "Loading detail UI state")
            }
        }
    }

    private fun handleSuccessDetailUiState(characterUiData: CharacterUiData?) {
        if (characterUiData != null) {
            binding.characterComponent.setCharacterData(characterUiData)
            binding.characterNameTv.text = characterUiData.name
            binding.characterLocation.text = characterUiData.location

            val characterStatus = binding.characterStatus
            characterStatus.setStatusTextColor(characterUiData.status)
            characterStatus.text = characterUiData.status

            binding.characterGender.setGenderImage(characterUiData.gender)
            binding.characterSpecies.text = characterUiData.species
        }
    }

    private fun toggleLoveStatus() {
        val character = args.characterArgument
        val loveId = character.id.toString()

        viewModel.checkLoveCharacter(loveId)
        viewModel.isLove.observe(viewLifecycleOwner) { isLove ->
            binding.loveIcon.isChecked = isLove
        }

        binding.loveIcon.setOnClickListener {
            val isLove = binding.loveIcon.isChecked
            if (isLove) {
                viewModel.addToLove(character)
                println("Add ${character.name}")
            } else {
                viewModel.removeFromLove(loveId)
                println("Remove ${character.name}")
            }
        }
    }

    private fun toggleHateStatus() {
        val character = args.characterArgument
        val hateId = character.id.toString()

        viewModel.checkHateCharacter(hateId)
        viewModel.isHate.observe(viewLifecycleOwner) { isHate ->
            binding.hateIcon.isChecked = isHate
        }

        binding.hateIcon.setOnClickListener {
            val isHate = binding.hateIcon.isChecked
            if (isHate) {
                viewModel.addToHate(character)
                println("Add ${character.name}")
            } else {
                viewModel.removeFromHate(hateId)
                println("Remove ${character.name}")
            }
        }
    }

}