package com.rld.g2esports.data

import androidx.room.TypeConverter
import com.rld.g2esports.data.models.news.Excerpt
import com.rld.g2esports.data.models.news.Title
import com.rld.g2esports.data.models.shop.Image
import com.rld.g2esports.data.models.shop.Prices

class Converters {

    @TypeConverter
    fun fromImage(image: List<Image>): String {
        return image.get(0).src
    }

    @TypeConverter
    fun toImage(image: String): List<Image> {
        return mutableListOf(Image("",1,"","", image, "",""))
    }

    @TypeConverter
    fun fromPrice(price: Prices): String {
        return price.price
    }

    @TypeConverter
    fun toPrice(price: String): Prices {
        return Prices("","",1,
            "","","","",price,"","")

    }

    @TypeConverter
    fun fromExcerpt(excerpt: Excerpt): String {
        return excerpt.rendered
    }

    @TypeConverter
    fun toExcerpt(render: String): Excerpt {
        return Excerpt(render)
    }

    @TypeConverter
    fun fromTitle(title: Title): String {
        return title.rendered
    }

    @TypeConverter
    fun toTitle(title: String): Title {
        return Title(title)
    }

}