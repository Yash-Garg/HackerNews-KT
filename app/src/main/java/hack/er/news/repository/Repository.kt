package hack.er.news.repository

import hack.er.news.api.RetrofitInstance
import hack.er.news.models.Article

class Repository {
    suspend fun getArticles(): List<Article> {
        return RetrofitInstance.api.getArticles()
    }
}
