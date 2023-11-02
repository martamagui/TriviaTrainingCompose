package com.mmag.triviatraining.presentation.views

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmag.triviatraining.data.network.NetworkResponse
import com.mmag.triviatraining.data.network.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val networkRepository: NetworkRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            networkRepository.getQuestions(10).collect { response ->
                when (response) {
                    is NetworkResponse.Error -> Log.e("request", "error de carga")
                    is NetworkResponse.Loading -> Log.i("request", "Cargando ... ")
                    is NetworkResponse.Success -> Log.i("request", "Recibido: ${response.data} ")
                }
            }
        }
    }
}