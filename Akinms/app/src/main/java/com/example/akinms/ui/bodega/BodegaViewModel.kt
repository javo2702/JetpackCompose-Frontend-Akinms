package com.example.akinms.ui.bodega

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
import com.example.akinms.domain.use_case.GetCategoriasBodegaUseCase
import com.example.akinms.ui.Maps.MapsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class BodegaViewModel @Inject constructor(
    private val getBodegaUseCase: GetBodegaUseCase,
    private val getCategoriasBodegaUseCase: GetCategoriasBodegaUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(BodegaState(isLoading = true))
        private set
    private val _eventFlow = MutableSharedFlow<MapsViewModel.UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    init {
        getBodega()
        getCategoriasBodega()
    }
    fun getBodega(){
        savedStateHandle.get<Long>("id")?.let { bodegaId ->
            viewModelScope.launch {
                getBodegaUseCase(bodegaId).also { result ->
                    when (result) {
                        is Result.Success -> {
                            state = state.copy(
                                bodega = result.data,
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
    fun getCategoriasBodega(){
        savedStateHandle.get<Long>("id")?.let { bodegaId ->
            viewModelScope.launch {
                getCategoriasBodegaUseCase(bodegaId).onEach { result ->
                    when(result){
                        is Result.Success -> {
                            state = state.copy(
                                categorias = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Result.Error -> {
                            state = state.copy(
                                isLoading = false
                            )
                            _eventFlow.emit(
                                MapsViewModel.UIEvent.ShowSnackBar(
                                    result.message ?: "Unknow Error"
                                )
                            )
                        }
                        is Result.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
            }
        }
    }

}