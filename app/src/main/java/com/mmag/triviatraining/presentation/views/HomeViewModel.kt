package com.mmag.triviatraining.presentation.views

import androidx.lifecycle.ViewModel
import com.mmag.triviatraining.data.network.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val networkRepository: NetworkRepository): ViewModel() {
}