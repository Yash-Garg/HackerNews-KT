package hack.er.news

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import hack.er.news.adapter.ArticleAdapter
import hack.er.news.databinding.ActivityMainBinding
import hack.er.news.repository.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val recyclerView = binding.recyclerView
        val loadingIndicator = binding.loadingIndicator
        val errorView = binding.errorView.root

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getArticles()

        viewModel.apiResponse.observe(this) { response ->
            if (response != null && response.isNotEmpty()) {
                loadingIndicator.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                recyclerView.adapter = ArticleAdapter(response)
            } else {
                loadingIndicator.visibility = View.GONE
                errorView.visibility = View.VISIBLE
            }
        }
    }
}
