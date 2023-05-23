package com.example.rickyandmorty.presentation.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.rickyandmorty.databinding.LayoutToolbarBinding
import com.example.rickyandmorty.domain.model.CharacterUiData

class ToolbarUiComponent @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttributeSet: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttributeSet) {

    private val binding = LayoutToolbarBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
    }

    fun setOnlyBackIconVisibility(isVisible: Boolean) {
        binding.toolbarBackIcon.isVisible = isVisible
    }

    fun setOnBackIconClickListener(onClick: () -> Unit) {
        binding.toolbarBackIcon.setOnClickListener {
            onClick()
        }
    }




    fun setOnlyToolbarTitleVisibilityAndTitleText(isVisible: Boolean, toolbarTitle: String) {
        binding.toolbarTitle.isVisible = isVisible
        binding.toolbarTitle.text = toolbarTitle
    }


}