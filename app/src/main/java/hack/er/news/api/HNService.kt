package hack.er.news.api

import hack.er.news.model.Article
import retrofit2.http.GET
import retrofit2.http.Query

/** Simple interface defining an API for HackerNews */
interface HNService {
    @GET("news")
    suspend fun getArticles(@Query("page") page: Int): List<Article>

    companion object {
        const val BASE_URL = "https://api.hackerwebapp.com"
    }
}
