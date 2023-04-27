package com.example.akinms.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akinms.data.Result
import com.example.akinms.domain.use_case.GetDetallePedidoClienteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleViewModel @Inject constructor(
    private val getDetallePedidoClienteUseCase: GetDetallePedidoClienteUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(DetalleState())
        private set
    init{
        getPedido()
    }
    fun getPedido() {
        savedStateHandle.get<Long>("id_cliente")?.let { clienteId ->
            savedStateHandle.get<Long>("id_pedido")?.let { pedidoId ->
                viewModelScope.launch {
                    getDetallePedidoClienteUseCase(clienteId, pedidoId).also { result ->
                        when (result) {
                            is Result.Success -> {
                                state = state.copy(
                                    pedidos = result.data,
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
    }
}