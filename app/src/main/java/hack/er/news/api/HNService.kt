package hack.er.news.api

import hack.er.news.models.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HNService {
    @GET("news")
    suspend fun getArticles(
        @Query("page") page: Int
    ): Response<List<Article>>
}
