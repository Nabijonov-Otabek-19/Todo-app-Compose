package uz.nabijonov.otabek.todoapp_bek.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {

        val title = inputData.getString("title").toString()
        val desc = inputData.getString("desc").toString()

        NotificationHelper(applicationContext).createNotification(title, desc)

        return Result.success()
    }
}