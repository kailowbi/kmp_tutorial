import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _greetingList = MutableStateFlow<List<String>>(listOf())
    val greetingList: StateFlow<List<String>> get() {
        return _greetingList
    }

    init {
        viewModelScope.launch {
            Greeting().greetFlow().collect { str ->
                println("str:${str}")
                _greetingList.update { list ->
                    list + str
                }
            }
        }
    }
}