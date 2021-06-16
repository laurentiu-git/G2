package com.rld.g2esports.data.local

import android.content.Context
import androidx.room.*
import com.rld.g2esports.data.Converters
import com.rld.g2esports.data.models.news.NewsResponseItem
import com.rld.g2esports.data.models.shop.ShopResponseItem

@Database(
    entities = [ShopResponseItem::class, NewsResponseItem::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ShopDatabase : RoomDatabase() {

    abstract fun getShopDao(): ShopDao

    companion object{
        @Volatile
        private var instance: ShopDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShopDatabase::class.java,
                "shopItem_db.db"
            ).build()


    }
}