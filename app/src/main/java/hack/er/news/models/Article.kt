package hack.er.news.models

import com.squareup.moshi.Json

data class Article(
    val id: Long,
    val title: String,
    val points: Long? = null,
    val user: String? = null,
    val time: Long,
    @Json(name = "time_ago") val timeAgo: String,
    @Json(name = "comments_count") val commentsCount: Long,
    val type: String,
    val url: String,
    val domain: String? = null
)
