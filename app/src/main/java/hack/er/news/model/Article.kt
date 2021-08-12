package hack.er.news.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Article(
    val id: Long,
    val title: String,
    val points: Long? = 0,
    val user: String? = "Unknown",
    val time: Long,
    @Json(name = "time_ago") val timeAgo: String,
    @Json(name = "comments_count") val commentsCount: Long,
    val type: String,
    val url: String,
    val domain: String? = null
)
