package hack.er.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hack.er.news.models.Article
import hack.er.news.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    val apiResponse: MutableLiveData<List<Article>> = MutableLiveData()

    fun getArticles(): Boolean {
        var isLoaded = false
        viewModelScope.launch {
            try {
                val response = repository.getArticles()
                apiResponse.value = response
            } catch (e: Exception) {
                isLoaded = true
            }
        }
        return isLoaded
    }
}
