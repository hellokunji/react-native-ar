package com.reactnativear.blur

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.example.background.workers.CleanupWorker
import com.example.background.workers.SaveImageToFileWorker
import com.reactnativear.R
import com.reactnativear.blur.workers.BlurWorker

class BlurViewModel(application: Application): ViewModel() {

  internal var imageUri: Uri? = null
  internal var outputUri: Uri? = null
  internal val outputWorkInfos: LiveData<List<WorkInfo>>

  private val workManager = WorkManager.getInstance(application)

  init {
    imageUri = getImageUri(application.applicationContext)

    outputWorkInfos = workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)
  }

  private fun getImageUri(context: Context): Uri {
    val resources = context.resources

    val imageUri = Uri.Builder()
      .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
      .authority(resources.getResourcePackageName(R.drawable.android_cupcake))
      .appendPath(resources.getResourceTypeName(R.drawable.android_cupcake))
      .appendPath(resources.getResourceEntryName(R.drawable.android_cupcake))
      .build()

    return imageUri
  }

  private fun createInputDataForUri(): Data {
    val builder = Data.Builder()
    imageUri?.let {
      builder.putString(KEY_IMAGE_URI, imageUri.toString())
    }
    return builder.build()
  }

  internal fun applyBlur(blurLevel: Int) {
    var continuation = workManager
      .beginUniqueWork(
        IMAGE_MANIPULATION_WORK_NAME,
        ExistingWorkPolicy.REPLACE,
        OneTimeWorkRequest.from(CleanupWorker::class.java)
      )

    for (i in 0 until blurLevel) {
      val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()
      if (i == 0) {
        blurBuilder.setInputData(createInputDataForUri())
      }

      continuation = continuation.then(blurBuilder.build())
    }

    val constraints = Constraints.Builder()
      .setRequiresCharging(true)
      .build()

    val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
      .setConstraints(constraints)
      .addTag(TAG_OUTPUT)
      .build()

    continuation = continuation.then(save)

    continuation.enqueue()
  }
}
