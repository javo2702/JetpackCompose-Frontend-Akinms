package com.example.akinms.data.repositories

import com.example.akinms.data.source.local.CartDao
import com.example.akinms.domain.model.CartItem
import com.example.akinms.domain.repositories.CartItemRepository
import com.example.akinms.domain.repositories.CartList
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(
    private val cartDao: CartDao
): CartItemRepository {
    override fun getCartListFromRoom(): Flow<CartList> = cartDao.getItems()

    override fun getItemFromRoom(id: Int): CartItem = cartDao.getItem(id)

    override fun addItemToRoom(cartItem: CartItem) = cartDao.addItem(cartItem)

    override fun updateItemInRoom(cartItem: CartItem) = cartDao.updateItem(cartItem)

    override fun deleteItemFromRoom(cartItem: CartItem) = cartDao.deleteItem(cartItem)

    override fun deleteAllItemsFromRoom(id:Int) = cartDao.deleteAllItems(id)
}