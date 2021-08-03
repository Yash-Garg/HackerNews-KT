package hack.er.news.models

data class Article(
    val id: Long,
    val title: String,
    val points: Long? = null,
    val user: String? = null,
    val time: Long,
    val timeAgo: String,
    val commentsCount: Long,
    val type: String,
    val url: String,
    val domain: String? = null
)
