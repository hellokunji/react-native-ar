package com.reactnativear

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

fun createChannelId(channelId: String = "fdsafdsa", channelName: String = "somechannel", content: Context) {
  val description = "channel description"
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    val importance = NotificationManager.IMPORTANCE_HIGH
    val channel = NotificationChannel(channelId, channelName, importance)
    channel.description = description

    // Add the channel
    val notificationManager =
      content.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

    notificationManager?.createNotificationChannel(channel)
  }
}

fun makeStatusNotification(title: String, message: String, context: Context) {
  createChannelId("fdsafdsa", "fdsafdsa", context);

  val builder = NotificationCompat.Builder(context, "fdsafdsa")
    .setSmallIcon(R.drawable.android_cupcake)
    .setContentTitle(title)
    .setContentText(message)
    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

  with(NotificationManagerCompat.from(context)) {
    // notificationId is a unique int for each notification that you must define
    notify((1000..10000000).random(), builder.build())
  }
}
