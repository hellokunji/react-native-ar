package com.reactnativear

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.reactnativear.dummyActivity.DummyActivity

class ActivityService : Service() {

  override fun onCreate() {
    super.onCreate()

    val notificationIntent =
      Intent(this, DummyActivity::class.java)
    val pendingIntent: PendingIntent = PendingIntent.getActivity(
      this,
      0, notificationIntent, 0
    )
    createChannelId("fdsafdsa", "fdsafdsa", this);
    val notification = NotificationCompat.Builder(this.applicationContext, "fdsafdsa")
      .setSmallIcon(R.drawable.android_cupcake)
      .setColorized(true)
      .setContentTitle("Trip started")
      .setOngoing(true)
      .setContentText("")
      .setContentIntent(pendingIntent)
      .build()
    startForeground(1, notification)
  }

  override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
    try {
      val activityType = intent.getStringExtra("activityType")
      val state = intent.getStringExtra("state")

      if (state.equals("enter")) {

      } else {

      }
      Toast.makeText(this.applicationContext, activityType.toString(), Toast.LENGTH_LONG).show()
    } catch (e: Exception) {
      e.printStackTrace()
    }

    return START_STICKY
  }

  override fun onBind(intent: Intent): IBinder? {
    return null
  }
}
