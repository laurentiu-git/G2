package com.rld.g2esports.di

import android.content.Context
import com.rld.g2esports.data.local.ShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun proivdeShopDatabaseInstance(
        @ApplicationContext context: Context
    ) = ShopDatabase(context)
}