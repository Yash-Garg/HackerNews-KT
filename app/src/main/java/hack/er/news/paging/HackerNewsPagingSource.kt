package hack.er.news.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import hack.er.news.api.HNService
import hack.er.news.model.Article

class HackerNewsPagingSource(
    private val service: HNService
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int {
        return state.pages.size + 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val articles = service.getArticles(page)

            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
