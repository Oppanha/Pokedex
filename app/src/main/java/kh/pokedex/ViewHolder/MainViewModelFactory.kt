package kh.pokedex.ViewHolder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kh.pokedex.Repository.Repository

class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelMain(repository) as T
    }
}
