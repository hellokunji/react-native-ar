package com.reactnativear.dummyActivity

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.reactnativear.ActivityReceiver
import com.reactnativear.ActivityService
import com.reactnativear.R


class DummyActivity : AppCompatActivity() {
  private var receiver: ActivityReceiver? = null

  @RequiresApi(Build.VERSION_CODES.N)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_dummy)

    findViewById<Button>(R.id.back_btn).setOnClickListener {
      finish()
    }

    findViewById<Button>(R.id.start_trip).setOnClickListener {
      val serviceIntent = Intent(this, ActivityService::class.java);
      serviceIntent.putExtra("state", "enter")
      serviceIntent.putExtra("activityType", "Trip started")
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startForegroundService(serviceIntent)
      }
    }

    findViewById<Button>(R.id.end_trip).setOnClickListener {
      val serviceIntent = Intent(applicationContext, ActivityService::class.java)
      applicationContext.stopService(serviceIntent)
    }
  }


  override fun onResume() {
    super.onResume()
  }


  override fun onPause() {
    super.onPause()
  }

  override fun onDestroy() {
    super.onDestroy()
  }

}
