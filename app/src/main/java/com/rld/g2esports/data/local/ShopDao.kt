package com.rld.g2esports.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rld.g2esports.data.models.shop.ShopResponseItem

@Dao
interface ShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun upsert(shopItem: ShopResponseItem): Long

    @Query("SELECT * FROM shop")
    fun getShopItems(): LiveData<List<ShopResponseItem>>

    @Delete
    suspend fun deleteShopItem(shopItem: ShopResponseItem)
}