package hack.er.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hack.er.news.models.Article
import hack.er.news.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    val apiResponse: MutableLiveData<Response<List<Article>>> = MutableLiveData()

    fun getArticles() {
        viewModelScope.launch {
            val response = repository.getArticles()
            apiResponse.value = response
        }
    }
}
