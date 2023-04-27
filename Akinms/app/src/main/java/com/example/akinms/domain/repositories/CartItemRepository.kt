package com.example.akinms.domain.repositories

import com.example.akinms.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

typealias CartList = List<CartItem>

interface CartItemRepository {
    fun getCartListFromRoom(): Flow<CartList>
    fun getItemFromRoom(id:Int): CartItem
    fun addItemToRoom(cartItem: CartItem)
    fun updateItemInRoom(cartItem: CartItem)
    fun deleteItemFromRoom(cartItem: CartItem)
    fun deleteAllItemsFromRoom(id:Int)
}