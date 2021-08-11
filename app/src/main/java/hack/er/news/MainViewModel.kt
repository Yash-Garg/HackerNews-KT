package hack.er.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import hack.er.news.api.HNService
import hack.er.news.models.Article
import hack.er.news.repository.HackerNewsPagingSource
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val service: HNService) : ViewModel() {
    fun getArticles(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { HackerNewsPagingSource(service) }
        ).flow.cachedIn(viewModelScope)
    }
}
