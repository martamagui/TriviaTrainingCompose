package com.mmag.triviatraining.data.scheduled_updates

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mmag.data.data_source.DataSourceRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class TriviaExpeditedWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val dataSourceRepository: DataSourceRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        try {
            dataSourceRepository.updateQuestionsFromRemote(null)
        } catch (e: Exception) {
            return Result.retry()
        }
        return Result.success()
    }
}

