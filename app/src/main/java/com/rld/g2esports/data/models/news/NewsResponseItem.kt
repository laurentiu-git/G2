package com.rld.g2esports.data.models.news

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
        tableName = "news"
)
data class NewsResponseItem(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
    var author: Int,
    var comment_status: String,
    var date: String,
    var date_gmt: String,
    var excerpt: Excerpt,
    var featured_media: Int,
    var format: String,
    var link: String,
    var modified: String,
    var modified_gmt: String,
    var ping_status: String,
    var slug: String,
    var status: String,
    var sticky: Boolean,
    var template: String,
    var title: Title,
    var type: String
)