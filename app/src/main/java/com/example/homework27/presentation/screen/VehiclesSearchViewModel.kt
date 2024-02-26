package com.example.homework27.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework27.domain.result_wrapper.ResultWrapper
import com.example.homework27.domain.usecase.GetVehiclesListUseCase
import com.example.homework27.presentation.event.VehicleSearchPageEvents
import com.example.homework27.presentation.mappers.toUI
import com.example.homework27.presentation.state.VehiclesSearchPageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VehiclesSearchViewModel @Inject constructor(
    private val getVehiclesListUseCase: GetVehiclesListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(VehiclesSearchPageState())
    val state: StateFlow<VehiclesSearchPageState> = _state.asStateFlow()

    private val oneTimeEventChannel = Channel<String>()
    val oneTimeEventFlow = oneTimeEventChannel.receiveAsFlow() //takes as a hot flow (not like stateFlow,but like a sharedFlow)

    fun onEvent(event: VehicleSearchPageEvents) {
        when (event) {
            is VehicleSearchPageEvents.LoadVehicles -> loadVehicles(event.title)
        }
    }

    private fun loadVehicles(title: String) {
        viewModelScope.launch {
            getVehiclesListUseCase(title).collect{result->
                when(result){
                    is ResultWrapper.Error -> {
                        oneTimeEventChannel.send(result.errorMessage?:"")
                    }
                    is ResultWrapper.Loading -> {
                        _state.update {
                            it.copy(isLoading = result.loading)
                        }
                    }
                    is ResultWrapper.Success -> {
                        _state.update {
                            val storyList = result.data!!.map {vehicle->
                                vehicle.toUI()
                            }
                            it.copy(vehiclesList = storyList)
                        }
                    }
                }
            }
        }
    }
}