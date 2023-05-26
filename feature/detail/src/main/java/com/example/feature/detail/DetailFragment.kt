package com.example.feature.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core.common.MainViewModel
import com.example.core.common.utils.StatusState
import com.example.core.common.utils.FEMALE
import com.example.core.common.utils.MALE
import com.example.core.ui.CharacterUiData
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

        initCharacterUiComponent()
        initDetailToolbarUiComponent()
        observeDetailState()
        setBottomBarVisibility()

        toggleFavoriteStatus()
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
            }
        }
    }

    private fun toggleFavoriteStatus() {
        val character = args.characterArgument
        val favoriteId = character.id.toString()


        viewModel.checkFavoriteCharacter(favoriteId)
        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            binding.favoriteIcon.isChecked = isFavorite
        }

        binding.favoriteIcon.setOnClickListener {
            val isFavorite = binding.favoriteIcon.isChecked
            if (isFavorite) {
                viewModel.addToFavorite(character)
                println("Add ${character.name}")
            } else {
                viewModel.removeFromFavorite(favoriteId)
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

    private fun handleSuccessDetailUiState(characterUiData: CharacterUiData?) {
        if (characterUiData != null) {
            binding.characterComponent.setCharacterData(characterUiData)
            binding.characterNameTv.text = characterUiData.name
            binding.characterLocation.text = characterUiData.location

            val characterStatus = binding.characterStatus
            when (characterUiData.status) {
                StatusState.ALIVE.statusTitle -> characterStatus.setTextColor(Color.GREEN)
                StatusState.DEAD.statusTitle -> characterStatus.setTextColor(Color.RED)
                StatusState.UNKNOWN.statusTitle -> characterStatus.setTextColor(Color.BLUE)
            }
            characterStatus.text = characterUiData.status

            val characterGender = binding.characterGender
            when (characterUiData.gender) {
                String.FEMALE -> characterGender.setImageResource(R.drawable.gender_female_icon)
                String.MALE -> characterGender.setImageResource(R.drawable.gender_male_icon)
                else -> characterGender.setImageResource(R.drawable.unknown_gender)
            }
            binding.characterSpecies.text = characterUiData.species
        }
    }
}