package com.example.feature.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.core.common.MainViewModel
import com.example.core.common.utils.NameState
import com.example.core.common.utils.StatusState
import com.example.core.ui.utility.PagingLoadStateAdapter
import com.example.core.ui.utility.ToolbarTitle
import com.example.core.ui.utility.fragmentViewBinding
import com.example.feature.home.adapter.HomeRecyclerViewAdapter
import com.example.feature.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.core.ui.R as coreUiRes

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by fragmentViewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()
    private val characterAdapter by lazy { HomeRecyclerViewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel.setBottomBarVisibility(true)

        initHomeToolbarUiComponent()
        onClickCharacterItem()
        filter()
        observeLoadState()
        setupScrollToTopButton()

    }

    override fun onResume() {
        super.onResume()
        setSwitchMode()
    }

    private fun onClickCharacterItem() {
        characterAdapter.setOnItemClickListener { characterUiData ->
            val action =
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(characterUiData)
            val navOptions = NavOptions.Builder()
                .setEnterAnim(coreUiRes.anim.slide_in)
                .setExitAnim(coreUiRes.anim.fade_out)
                .setPopEnterAnim(coreUiRes.anim.fade_in)
                .setPopExitAnim(coreUiRes.anim.slide_out)
                .build()
            findNavController().navigate(action, navOptions)
        }
    }

    private fun filter() {
        setupNameSpinner()
        setupStatusSpinner()
    }

    private fun setupNameSpinner() {
        val nameValues = NameState.values()
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, nameValues)
        binding.spinnerName.adapter = adapter
        binding.spinnerName.setSelection(nameValues.indexOf(NameState.NAME))
        binding.spinnerName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedNameValue = nameValues[position]
                val selectedStatusValue =
                    StatusState.values()[binding.spinnerStatus.selectedItemPosition]
                collectUiState(selectedNameValue, selectedStatusValue)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupStatusSpinner() {
        val statusValues = StatusState.values()
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, statusValues)
        binding.spinnerStatus.adapter = adapter
        binding.spinnerStatus.setSelection(statusValues.indexOf(StatusState.STATUS))
        binding.spinnerStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedStatusValue = statusValues[position]
                val selectedNameValue =
                    NameState.values()[binding.spinnerName.selectedItemPosition]
                collectUiState(selectedNameValue, selectedStatusValue)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun collectUiState(
        nameValue: NameState,
        statusValue: StatusState
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAllCharacters(nameValue, statusValue).collectLatest {
                characterAdapter.submitData(it)
                binding.characterRv.adapter = characterAdapter.withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(characterAdapter),
                    footer = PagingLoadStateAdapter(characterAdapter)
                )
            }
        }
    }


    private fun observeLoadState() {
        val swipeRefreshLayout = binding.swipeRefreshLayout
        lifecycleScope.launch {
            swipeRefreshLayout.setOnRefreshListener {
                characterAdapter.refresh()
                binding.spinnerName.setSelection(
                    NameState.values().indexOf(
                        NameState.NAME
                    )
                )
                binding.spinnerStatus.setSelection(
                    StatusState.values().indexOf(
                        StatusState.STATUS
                    )
                )
                collectUiState(
                    NameState.NAME,
                    StatusState.STATUS
                )
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun initHomeToolbarUiComponent() {
        binding.homeToolbarComponent.setOnlyToolbarTitleVisibilityAndTitleText(
            true,
            ToolbarTitle.HOME.toolbarTitle
        )
    }

    private fun setSwitchMode() {
        val switchMode = binding.switchMode
        switchMode.setOnCheckedChangeListener { _, _ ->
            if (switchMode.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun setupScrollToTopButton() {
        val scrollToTopButton = binding.fabScrollUp

        binding.characterRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    scrollToTopButton.show()
                } else {
                    scrollToTopButton.hide()
                }
            }
        })
        scrollToTopButton.setOnClickListener {
            binding.characterRv.scrollToPosition(0)
        }
    }

//    private fun setupNameSpinner() {
//        val nameValues = NameState.values()
//        val nameAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, nameValues)
//        binding.autoCompleteName.setAdapter(nameAdapter)
//        binding.autoCompleteName.onItemClickListener =
//            AdapterView.OnItemClickListener { parent, view, position, id ->
//                val selectedNameValue = nameValues[position]
//                // val selectedStatusValue = StatusState.values() [binding.autoCompleteStatus.listSelection]
//                println(selectedNameValue)
//                // println(selectedStatusValue)
//
//            }
//    }
//
//    private fun setupStatusSpinner() {
//        val statusValues = StatusState.values()
//        val statusAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, statusValues)
//        binding.autoCompleteStatus.setAdapter(statusAdapter)
//        binding.autoCompleteStatus.onItemSelectedListener =
//            object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//                    val selectedStatusValue = StatusState.values()[position]
//                    val selectedNameValue = NameState.values()[binding.autoCompleteName.listSelection]
//                    collectUiState(selectedNameValue, selectedStatusValue)
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {}
//            }
//    }

}
