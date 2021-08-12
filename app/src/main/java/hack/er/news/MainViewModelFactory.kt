package hack.er.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hack.er.news.api.HNService

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val apiService: HNService) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(apiService) as T
    }
}
