package com.example.akinms.ui.bodega.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akinms.domain.model.CartItem
import com.example.akinms.domain.repositories.CartItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repo: CartItemRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var cart by mutableStateOf(CartItem(0,0,"","",0.0,0, "",0))
        private set

    val items = repo.getCartListFromRoom()

    init {

    }
    fun addCartItem(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        repo.addItemToRoom(cartItem)
    }
    fun updateCartItem(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateItemInRoom(cartItem)
    }
    fun deleteCartItem(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteItemFromRoom(cartItem)
    }
    fun deleteCartList(id: Int) = viewModelScope.launch(Dispatchers.IO ) {
        repo.deleteAllItemsFromRoom(id)
    }
}