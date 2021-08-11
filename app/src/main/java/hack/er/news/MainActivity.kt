package hack.er.news

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import hack.er.news.adapter.ArticleAdapter
import hack.er.news.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loadingIndicator = binding.loadingIndicator
        val errorView = binding.errorView.root
        val recyclerView = binding.recyclerView

        articleAdapter = ArticleAdapter()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        lifecycleScope.launch {
            try {
                viewModel.getArticles().collectLatest { pagingData ->
                    loadingIndicator.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    recyclerView.adapter = articleAdapter
                    articleAdapter.submitData(pagingData)
                }
            } catch (e: Exception) {
                loadingIndicator.visibility = View.GONE
                errorView.visibility = View.VISIBLE
            }
        }
    }
}
