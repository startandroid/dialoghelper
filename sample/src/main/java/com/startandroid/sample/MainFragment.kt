package com.startandroid.sample


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.startandroid.dialoghelper.DialogConfig
import com.startandroid.dialoghelper.DialogHelper
import com.startandroid.dialoghelper.DialogHelperCallback

class MainFragment() : Fragment(), DialogHelperCallback {

    private val dialogHelper = DialogHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.findViewById<TextView>(R.id.tv)?.setOnClickListener {
            click()
        }
        registerDialogs()
    }

    private fun registerDialogs() {
        val dialogConfig = DialogConfig()
            .title(R.string.test_title_1)
            .message(R.string.test_message_1)
            .positive(R.string.dialog_yes) { Log.d("qweee", "positive action") }
            .negative(R.string.dialog_no) { Log.d("qweee", "negative action") }
            .neutral(R.string.dialog_cancel) { Log.d("qweee", "neutral action") }
        dialogHelper.registerDialogConfig(1, dialogConfig)
    }

    fun click() {
        dialogHelper.showDialog(1, this) // TODO use constant for dialogCode
    }

    override fun onDialogResult(requestCode: Int, resultCode: Int, data: Intent) {
        dialogHelper.handleResult(requestCode, resultCode, data)
    }


}
