package com.mmag.triviatraining.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmag.domain.model.QuizCategory
import com.mmag.domain.model.QuizQuestion
import com.mmag.domain.model.TriviaResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataSourceRepository: com.mmag.data.data_source.DataSourceRepository
) : ViewModel() {

    private var _categoriesState: MutableStateFlow<TriviaResponse<List<QuizCategory>>> =
        MutableStateFlow(TriviaResponse.Loading())
    val categoriesState: StateFlow<TriviaResponse<List<QuizCategory>>> get() = _categoriesState

    private var _questionList: MutableStateFlow<List<QuizQuestion>?> = MutableStateFlow(null)
    val questionList: StateFlow<List<QuizQuestion>?> get() = _questionList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataSourceRepository.getCategories().collect { it ->
                _categoriesState.emit(it)
            }
        }
    }

    fun requestCategoryQuestions(category: QuizCategory) {
        viewModelScope.launch(Dispatchers.IO) {
            _questionList.update { null }
            dataSourceRepository.getQuestionsByCategory(category).collect { response ->
                when (response) {
                    is TriviaResponse.Success -> {
                        val list = response.data
                        if (list != null && list.size >= 10 && questionList.value == null) {
                            val listCopy = list.shuffled()
                            val sublist = listCopy.subList(0, 10)
                            _questionList.update { sublist }
                            this.coroutineContext.job.cancel()
                        } else if (questionList.value == null) {
                            _questionList.update { list }
                        }
                    }

                    else -> _questionList.update { it }
                }
            }
        }
    }
}