package com.rld.g2esports.data.models.shop

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "shop"
)
data class ShopResponseItem(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        var average_rating: String,
        var description: String,
        var has_options: Boolean,
        var images: List<Image>,
        var is_in_stock: Boolean,
        var is_on_backorder: Boolean,
        var is_purchasable: Boolean,
        var name: String,
        var on_sale: Boolean,
        var parent: Int,
        var permalink: String,
        var price_html: String,
        var prices: Prices,
        var price: String,
        var quantity_limit: Int,
        var review_count: Int,
        var short_description: String,
        var sku: String,
        var sold_individually: Boolean,
        var type: String,
        var variation: String,
): Serializable