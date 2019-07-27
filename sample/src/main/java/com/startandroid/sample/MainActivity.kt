package com.startandroid.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.startandroid.dialoghelper.DialogConfig
import com.startandroid.dialoghelper.DialogHelper
import com.startandroid.dialoghelper.DialogHelperCallback

class MainActivity : AppCompatActivity(), DialogHelperCallback {

    val dialogHelper = DialogHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerDialogs()

        findViewById<TextView>(R.id.txt).setOnClickListener {
            dialogHelper.showDialog(1, this)
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.container,
                MainFragment()
            ).commit()
        }


    }

    private fun registerDialogs() {
        val dialogConfig = DialogConfig()
            .title(R.string.test_title_2)
            .message(R.string.test_message_2)
            .positive(R.string.dialog_yes) { Log.d("qweee", "positive action 2") }
            .negative(R.string.dialog_no) { Log.d("qweee", "negative action 2") }
            .neutral(R.string.dialog_cancel) { Log.d("qweee", "neutral action 2") }
        dialogHelper.registerDialogConfig(1, dialogConfig)
    }

    override fun onDialogResult(requestCode: Int, resultCode: Int, data: Intent) {
        dialogHelper.handleResult(requestCode, resultCode, data)
    }


}
