package com.mmag.triviatraining.presentation.views

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmag.triviatraining.data.data_source.DataSourceRepository
import com.mmag.triviatraining.data.db.model.CategoryLocal
import com.mmag.triviatraining.presentation.ui_model.QuizCategory
import com.mmag.triviatraining.presentation.ui_model.QuizQuestion
import com.mmag.triviatraining.presentation.ui_model.TriviaResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataSourceRepository: DataSourceRepository
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
                            val sublist = listCopy.subList(0, 9)
                            _questionList.update { sublist }
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