package com.reactnativear
import android.content.Intent
import android.os.Build
import android.widget.Toast
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.reactnativear.dummyActivity.DummyActivity

class ArModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "Ar"
    }

    @ReactMethod
    fun multiply(a: Int, b: Int, promise: Promise) {
          promise.resolve(a * b)
    }

   @ReactMethod
   fun showToast(msg: String) {
     Toast.makeText(reactApplicationContext, msg, Toast.LENGTH_LONG).show()
   }

   @ReactMethod
   fun startARActivity() {
      // val appContext: Context = reactApplicationContext
      // makeStatusNotification("Starting the work manager", appContext)
     val intent = Intent(reactApplicationContext, DummyActivity::class.java)
     intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
     reactApplicationContext.startActivity(intent)
   }

   @ReactMethod
   fun startTrip() {
     val serviceIntent = Intent(reactApplicationContext, ActivityService::class.java);
     serviceIntent.putExtra("state", "enter")
     serviceIntent.putExtra("activityType", "Trip started")
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
       reactApplicationContext.startForegroundService(serviceIntent)
     }
   }

   @ReactMethod
   fun endTrip() {
     val serviceIntent = Intent(reactApplicationContext, ActivityService::class.java)
     reactApplicationContext.stopService(serviceIntent)
   }

}
