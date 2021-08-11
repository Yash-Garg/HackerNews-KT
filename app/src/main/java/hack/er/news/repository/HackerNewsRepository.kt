package hack.er.news.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import hack.er.news.api.HNService
import hack.er.news.models.Article
import kotlinx.coroutines.flow.Flow

class HackerNewsRepository(private val service: HNService) {
    suspend fun getArticles(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { HackerNewsPagingSource(service) }
        ).flow
    }
}
