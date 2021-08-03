package hack.er.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hack.er.news.models.Article
import hack.er.news.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    val apiResponse: MutableLiveData<List<Article>> = MutableLiveData()

    fun getArticles(): LiveData<Boolean> {
        val hasError = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response = repository.getArticles()
                apiResponse.value = response
                hasError.value = false
            } catch (e: Exception) {
                hasError.value = true
            }
        }
        return hasError
    }
}
