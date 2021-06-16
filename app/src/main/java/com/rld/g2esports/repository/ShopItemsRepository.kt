package com.rld.g2esports.repository

import com.rld.g2esports.data.local.ShopDatabase
import com.rld.g2esports.network.RetrofitInstance
import retrofit2.Retrofit
import javax.inject.Inject

class ShopItemsRepository @Inject constructor(
        val db: ShopDatabase
) {

    suspend fun getShopItems(itemToSearch: String) =
        RetrofitInstance.api.getShopItems(itemToSearch)

    suspend fun getNews() =
            RetrofitInstance.api.getNews()

}