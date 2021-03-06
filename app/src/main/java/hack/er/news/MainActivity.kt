package hack.er.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import hack.er.news.adapter.ArticleAdapter
import hack.er.news.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        val shimmerLoading = binding.shimmerLayout
        val errorView = binding.errorView.root
        val swipeRefreshLayout = binding.swipeLayout

        swipeRefreshLayout.setOnRefreshListener {
            articleAdapter.refresh()
            swipeRefreshLayout.isRefreshing = false
        }

        /** Start shimmer loading animation **/
        shimmerLoading.startLayoutAnimation()

        lifecycleScope.launch {
            mainViewModel.getArticles().collectLatest { pagingData ->
                recyclerView.adapter = articleAdapter
                articleAdapter.submitData(pagingData)
            }
        }

        /** Separate launch scope for states
        because it was getting blocked due
        to [submitData] launch scope **/
        lifecycleScope.launch {
            articleAdapter.loadStateFlow.collectLatest { loadStates ->
                shimmerLoading.isVisible = loadStates.refresh is LoadState.Loading
                errorView.isVisible = loadStates.refresh is LoadState.Error
                recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
            }
        }
    }
}
