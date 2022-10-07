package com.reactnativear

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

class ActivityReceiver : BroadcastReceiver() {
  private val activityType = arrayListOf(
    "Trip Started",
    "ON_BICYCLE",
    "ON_FOOT",
    "STILL",
    "UNKNOWN",
    "TILTING",
    "WALKING",
    "RUNNING"
  )

  companion object {
    val activityState = MutableLiveData<String>()
  }

  private var inEntryState = false
  override fun onReceive(context: Context, intent: Intent) {
      // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
    Toast.makeText(context, "Received something in receiver", Toast.LENGTH_LONG).show();

    val serviceIntent = Intent(context, ActivityService::class.java)
    serviceIntent.putExtra("entryState", "true");
  }
}
