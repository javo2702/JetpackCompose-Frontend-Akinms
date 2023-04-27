package com.example.akinms.data.source.local

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.akinms.domain.model.CartItem
import com.example.akinms.domain.repositories.CartList
import com.example.akinms.util.CART_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM $CART_TABLE ORDER BY 'id' ASC")
    fun getItems(): Flow<CartList>

    @Query("SELECT * FROM $CART_TABLE WHERE 'id' = :id")
    fun getItem(id: Int): CartItem

    @Insert(onConflict = IGNORE)
    fun addItem(item: CartItem)

    @Update
    fun updateItem(item: CartItem)

    @Delete
    fun deleteItem(item: CartItem)

    @Query("DELETE FROM $CART_TABLE WHERE idBodega = :id")
    fun deleteAllItems(id: Int)
}