package com.example.akinms.ui.bodega.pedido

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.dto.pedido.Pedido
import com.example.akinms.domain.use_case.SetPedidoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PedidoViewModel @Inject constructor(
    private val setPedidoUseCase: SetPedidoUseCase
) : ViewModel() {
    var state by mutableStateOf(PedidoState())
        private set
    val idcliente: Long = 1
    fun setPedido(pedido: Pedido) {
        viewModelScope.launch {
            setPedidoUseCase(pedido).also { result ->
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