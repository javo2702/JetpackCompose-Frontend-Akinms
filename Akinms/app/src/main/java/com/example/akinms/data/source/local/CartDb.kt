package com.example.akinms.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.akinms.domain.model.CartItem

@Database(entities = [CartItem::class], version = 3, exportSchema = false)
abstract class CartDb: RoomDatabase() {
    abstract fun cartDao(): CartDao
}