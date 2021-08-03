package hack.er.news.repository

import hack.er.news.api.RetrofitInstance
import hack.er.news.models.Article
import retrofit2.Response

class Repository {
    suspend fun getArticles(): Response<List<Article>> {
        return RetrofitInstance.api.getArticles()
    }
}
