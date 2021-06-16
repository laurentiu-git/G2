package com.rld.g2esports.network

import com.rld.g2esports.data.models.news.NewsResponse
import com.rld.g2esports.data.models.shop.ShopResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ShopAPI {

    @GET("wp-json/dokan/v1/products/{latest}")
    suspend fun getShopItems(
            @Path("latest")
            latest: String = "latest",
    ): Response<ShopResponse>

    @GET("/wp-json/wp/v2/posts")
    suspend fun getNews()
    : Response<NewsResponse>


}
