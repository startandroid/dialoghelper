package com.startandroid.sample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.startandroid.dialoghelper.DialogConfig
import com.startandroid.dialoghelper.DialogHelper

class MainActivity : AppCompatActivity() {

    companion object {
        const val CODE_DIALOG_IN_ACTIVITY = 1
    }

    val dialogHelper = DialogHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.container,
                MainFragment()
            ).commit()
        }

        registerDialogs()

        findViewById<TextView>(R.id.btn_activity_dialog).setOnClickListener {
            dialogHelper.showDialog(1, this)
        }
    }

    private fun registerDialogs() {
        val dialogConfig = DialogConfig()
            .title("Activity dialog")
            .message("Message")
            .positive(R.string.ok)
        dialogHelper.registerDialogConfig(CODE_DIALOG_IN_ACTIVITY, dialogConfig)
    }


}
