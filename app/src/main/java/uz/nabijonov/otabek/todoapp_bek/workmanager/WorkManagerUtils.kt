package uz.nabijonov.otabek.todoapp_bek.workmanager

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.UUID
import java.util.concurrent.TimeUnit

fun cancelWork(context: Context, workId: UUID) {
    WorkManager.getInstance(context).cancelWorkById(workId)
}

fun setWork(
    context: Context,
    date: String,
    time: String,
    title: String,
    desc: String,
    workId: UUID
) {

    val constraint = Constraints.Builder()
        .setRequiresCharging(false)
        .setRequiresBatteryNotLow(true)
        .build()

    val userSelectedDateTime = Calendar.getInstance()

    val chosenYear = date.substring(0, 4).toInt()
    val chosenMonth = date.substring(5, 7).toInt()
    val chosenDay = date.substring(8).toInt()

    val chosenHour = time.substring(0, 2).toInt()
    val chosenMin = time.substring(3).toInt()

    userSelectedDateTime.set(
        chosenYear,
        chosenMonth,
        chosenDay,
        chosenHour,
        chosenMin
    )
    val todayDateTime = Calendar.getInstance()
    todayDateTime.set(
        LocalDateTime.now().year,
        LocalDate.now().monthValue,
        LocalDateTime.now().dayOfMonth,
        LocalDateTime.now().hour, LocalDateTime.now().minute
    )

    val delayInSeconds =
        (userSelectedDateTime.timeInMillis / 1000L) - (todayDateTime.timeInMillis / 1000L)

    val request = OneTimeWorkRequestBuilder<MyWorker>()
        .setConstraints(constraint)
        .setId(workId)
        .setInputData(workDataOf("title" to title, "desc" to desc))
        .setInitialDelay(delayInSeconds, TimeUnit.SECONDS)
        .build()

    WorkManager.getInstance(context)
        .enqueueUniqueWork(workId.toString(), ExistingWorkPolicy.REPLACE, request)
}