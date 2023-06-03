package uz.gita.todoappexam.workmanager

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import uz.gita.todoappexam.R

class MyWorker(val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {

        val title = inputData.getString("title").toString()
        val desc = inputData.getString("desc").toString()

        Log.d("AAA", "MyWorker worked! $title")
        Log.d("AAA", "MyWorker worked! $desc")

        //MediaPlayer.create(applicationContext, R.raw.wifi_on).start()

        NotificationHelper(applicationContext).createNotification(title, desc)

        return Result.success()
    }
}