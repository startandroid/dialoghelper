package com.startandroid.dialoghelper

import android.content.Intent

interface DialogHelperCallback {
    fun onDialogResult(requestCode: Int, resultCode: Int, data: Intent)
}