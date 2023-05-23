package com.example.rickyandmorty.presentation.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.rickyandmorty.data.database.FavoriteCharacter
import com.example.rickyandmorty.data.database.HateCharacter
import com.example.rickyandmorty.databinding.LayoutCharacterBinding
import com.example.rickyandmorty.domain.model.CharacterUiData
import com.example.rickyandmorty.utility.loadImage

class CharacterUiComponent @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

    private val binding = LayoutCharacterBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
    }

    fun setCharacterData(characterUiData: CharacterUiData) {
        binding.characterImage.loadImage(characterUiData.image)
        binding.characterName.text = characterUiData.name
    }

    fun setFavoriteCharacterData(favoriteCharacter: FavoriteCharacter) {
        binding.characterName.text = favoriteCharacter.characterName
        binding.characterImage.loadImage(favoriteCharacter.characterImage)
    }

    fun setHateCharacterData(hateCharacter: HateCharacter) {
        binding.characterName.text = hateCharacter.characterName
        binding.characterImage.loadImage(hateCharacter.characterImage)
    }

    fun setGradientViewAndCharacterNameVisibility(isVisible: Boolean) {
        binding.gradientView.isVisible = isVisible
        binding.characterName.isVisible = isVisible
    }

}