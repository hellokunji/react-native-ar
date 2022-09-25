package com.reactnativear
import android.content.Context
import android.widget.Toast
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import makeStatusNotification

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
   fun startWorkManager() {
     val appContext: Context = reactApplicationContext
     makeStatusNotification("Starting the work manager", appContext)
   }

}
