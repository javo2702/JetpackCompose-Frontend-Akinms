package com.example.akinms.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akinms.domain.use_case.GetPedidosClienteUseCase
import com.example.akinms.data.Result
import com.example.akinms.domain.use_case.GetClienteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getPedidosClienteUseCase: GetPedidosClienteUseCase,
    private val getClienteUseCase: GetClienteUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(ProfileState())
        private set
    init{

    }

    fun getUsuario(id:Long){
        viewModelScope.launch {
            getClienteUseCase(id).also { result ->
                when (result) {
                    is Result.Success -> {
                        state = state.copy(
                            cliente = result.data,
                            isLoading = false
                        )
                    }
                    is Result.Error -> {
                        state = state.copy(
                            isLoading = false
                        )
                    }
                    is Result.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }
}