package com.reactnativear.dummyActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.reactnativear.R

class DummyActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_dummy)


    findViewById<Button>(R.id.back_btn).setOnClickListener {
      finish()
    }
  }
}
