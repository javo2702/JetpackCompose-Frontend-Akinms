package com.example.akinms.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akinms.domain.use_case.GetBodegaUseCase
import com.example.akinms.ui.bodega.products.ProductsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.dto.cliente.Cliente
import com.example.akinms.domain.use_case.GetCategoriasBodegaUseCase
import com.example.akinms.domain.use_case.GetClienteUseCase
import com.example.akinms.domain.use_case.buscarClienteUseCase
import com.example.akinms.ui.Maps.MapsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val buscarClienteUseCase: buscarClienteUseCase,
    private val getClienteUseCase: GetClienteUseCase
) : ViewModel() {
    var state by mutableStateOf(LoginState(isLoading = true))
        private set

    fun buscarCliente(cliente: Cliente){
        viewModelScope.launch {
            buscarClienteUseCase(cliente).also { result ->
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
    fun getCliente(id: Long){
        viewModelScope.launch {
            getClienteUseCase(id).also { result ->

            }
        }
    }
}