package com.example.homework27.presentation.screen

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homework27.R
import com.example.homework27.databinding.FragmentVehiclesSearchBinding
import com.example.homework27.presentation.base.BaseFragment
import com.example.homework27.presentation.event.VehicleSearchPageEvents
import com.example.homework27.presentation.extensions.showSnackBar
import com.example.homework27.presentation.screen.adapter.VehiclesSuggestionsRecyclerAdapter
import com.example.homework27.presentation.state.VehiclesSearchPageState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@AndroidEntryPoint
class SearchCategoryFragment : BaseFragment<FragmentVehiclesSearchBinding>(FragmentVehiclesSearchBinding::inflate) {

    private val viewModel: VehiclesSearchViewModel by viewModels()
    private lateinit var myAdapter: VehiclesSuggestionsRecyclerAdapter

    override fun bind() {
        myAdapter = VehiclesSuggestionsRecyclerAdapter()
        binding.vehiclesRecyclerView.adapter = myAdapter
        setupSearchEditTextListener()
    }

    private fun setupSearchEditTextListener() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {

            var delay: Long = 1000 // Delay in milliseconds
            var timer = Timer()

            override fun afterTextChanged(s: Editable?) {
                // Nothing needed here
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Nothing needed here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                timer.cancel() // Cancel any previous timer tasks
                timer.purge() // Remove cancelled tasks from the timer's task queue

                // If the text is not null or empty, schedule the event
                if (!s.isNullOrBlank()) {
                    timer = Timer() // Assign a new Timer instance
                    timer.schedule(object : TimerTask() {
                        override fun run() {
                            // This code will be executed after the user has stopped typing for 1000ms (1 second)
                            viewModel.onEvent(VehicleSearchPageEvents.LoadVehicles(s.toString()))
                        }
                    }, delay)
                }
            }
        })
    }

    override fun bindObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    handleState(state)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.oneTimeEventFlow.collect { message ->
                    showErrorMessage(message)
                }
            }
        }
    }

    private fun handleState(state: VehiclesSearchPageState) {

        showLoader(state.isLoading)

        state.vehiclesList?.let { list ->
            myAdapter.submitList(list)
            if (list.isEmpty()) {
                showErrorMessage(getString(R.string.nothing_was_found))
            }
        }
    }

    private fun showErrorMessage(errorMessage: String) {
        if (errorMessage.isNotEmpty()) {
            binding.root.showSnackBar(errorMessage)
            // After showing the error, reset the message in ViewModel if needed
            // viewModel.onEvent(VehicleSearchPageEvents.ResetErrorMessageToNull)
        }
    }

    private fun showLoader(loading: Boolean) {
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    override fun onStop() {
        super.onStop()
        binding.searchEditText.text?.clear()
    }
}