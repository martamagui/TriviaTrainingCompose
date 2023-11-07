package com.mmag.triviatraining

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.mmag.triviatraining.data.scheduled_updates.TriviaExpeditedWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class TriviaTrainingApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() = Configuration.Builder()
        .setWorkerFactory(workerFactory)
        .build()

    override fun onCreate() {
        super.onCreate()

        setScheduledUpdate()
    }

    private fun setScheduledUpdate() {
        val myConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val refreshCpnWork =
            PeriodicWorkRequest.Builder(TriviaExpeditedWorker::class.java, 24, TimeUnit.HOURS)
                .setInitialDelay(getScheduledInitialTime(), TimeUnit.SECONDS)
                .setConstraints(myConstraints)
                .addTag("updateQuizData")
                .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "updateQuizData",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, refreshCpnWork
        )
    }

    private fun getScheduledInitialTime(): Long {
        val currentDate = Calendar.getInstance()
        val dueDate = Calendar.getInstance()

        currentDate.timeInMillis
        // Set Execution around 03:00:00 AM
        dueDate.set(Calendar.HOUR_OF_DAY, 3)
        dueDate.set(Calendar.MINUTE, 0)
        dueDate.set(Calendar.SECOND, 0)
        if (dueDate.before(currentDate)) {
            dueDate.add(Calendar.HOUR_OF_DAY, 24)
        }
        return dueDate.timeInMillis
    }

}