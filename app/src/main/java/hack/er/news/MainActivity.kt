package hack.er.news

import android.os.Bundle
import android.widget.Toast
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
        recyclerView.adapter = ArticleAdapter(listOf())

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getArticles()
        viewModel.apiResponse.observe(this) { response ->
            if (response.isSuccessful) {
                recyclerView.adapter = response.body()?.let { ArticleAdapter(it) }
            } else {
                Toast.makeText(this, "Error fetching data..", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
