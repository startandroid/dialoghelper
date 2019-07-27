package com.startandroid.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            .positive(R.string.yes) { /* do comething */ }
            .negative(R.string.no)
            .neutral(R.string.cancel)
        dialogHelper.registerDialogConfig(1, dialogConfig)
    }

    override fun onDialogResult(requestCode: Int, resultCode: Int) {
        dialogHelper.handleResult(requestCode, resultCode)
    }


}
