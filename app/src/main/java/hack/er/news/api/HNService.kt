package hack.er.news.api

import hack.er.news.models.Article
import retrofit2.Response
import retrofit2.http.GET

interface HNService {
    @GET("news?page=1")
    suspend fun getArticles(): Response<List<Article>>
}
