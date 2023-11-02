package com.mmag.triviatraining.presentation.views

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmag.triviatraining.data.data_source.DataSourceRepository
import com.mmag.triviatraining.data.network.NetworkResponse
import com.mmag.triviatraining.data.network.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataSourceRepository: DataSourceRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            var response = dataSourceRepository.getQuestions()
            if(response== null || response.isEmpty()){
                dataSourceRepository.updateFromRemote()
                response = dataSourceRepository.getQuestions()
            }
            Log.i("List", "Response de la db: $response")
        }
    }
}