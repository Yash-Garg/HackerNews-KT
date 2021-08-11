package hack.er.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import hack.er.news.adapter.ArticleAdapter
import hack.er.news.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        articleAdapter = ArticleAdapter()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.recyclerView.adapter = articleAdapter

        lifecycleScope.launch {
            viewModel.getArticles().collectLatest { pagingData ->
                articleAdapter.submitData(pagingData)
            }
        }
    }
}
