package com.mmag.triviatraining.presentation.views

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmag.triviatraining.data.data_source.DataSourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataSourceRepository: DataSourceRepository
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataSourceRepository.getQuestionsByCategory(null).collect { list ->
                list.forEach { item ->
                    Log.d("Lista", "Item: $item")
                }
            }
        }
    }
}