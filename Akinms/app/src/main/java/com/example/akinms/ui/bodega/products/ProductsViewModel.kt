package com.example.akinms.ui.bodega.products

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akinms.domain.use_case.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.akinms.data.Result
import com.example.akinms.domain.use_case.GetProductsCategoriaUseCase
import com.example.akinms.domain.use_case.GetProductsNombreUseCase
import com.example.akinms.ui.home.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductsCategoriaUseCase: GetProductsCategoriaUseCase,
    private val getProductsNombreUseCase: GetProductsNombreUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){
    var state by mutableStateOf(ProductsState(isLoading = true))
        private set
    private  val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init{
    }

    fun getProducts(){
        savedStateHandle.get<Long>("id")?.let{ bodegaId ->
            viewModelScope.launch {
                getProductsUseCase(bodegaId).onEach { result ->
                    when(result){
                        is Result.Success -> {
                            state = state.copy(
                                productos = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Result.Error -> {
                            state = state.copy(
                                isLoading = false
                            )
                            _eventFlow.emit(
                                UIEvent.ShowSnackBar(
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
    fun getProductsCategoria(){
        savedStateHandle.get<Long>("id1")?.let{ bodegaId ->
            savedStateHandle.get<Long>("id2")?.let{ categoriaId ->
                viewModelScope.launch {
                    getProductsCategoriaUseCase(bodegaId,categoriaId).onEach { result ->
                        when(result){
                            is Result.Success -> {
                                state = state.copy(
                                    productos = result.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                            is Result.Error -> {
                                state = state.copy(
                                    isLoading = false
                                )
                                _eventFlow.emit(
                                    UIEvent.ShowSnackBar(
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
    fun getProductsNombre(id:Long,query:String) =viewModelScope.launch {
        state.isLoading = true
        getProductsNombreUseCase(id,query).onEach { result ->
            when(result){
                is Result.Success -> {
                    state = state.copy(
                        productos = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Result.Error -> {
                    state = state.copy(
                        isLoading = false
                    )
                    _eventFlow.emit(
                        UIEvent.ShowSnackBar(
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
    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}