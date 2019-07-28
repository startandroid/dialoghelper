package com.startandroid.sample


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


// TODO make normal samples (buttons for different dialogs)
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
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ...

        val dialogConfig = DialogConfig()
            .title("title text")
            .message("message text")
            .positive("positive text") { Log.d("qweee", "positive")}
            .negative("negative text") { Log.d("qweee", "negative")}
            .neutral("neutral text") { Log.d("qweee", "neutral")}
            .cancelable(true)
        dialogHelper.registerDialogConfig(1, dialogConfig)
    }

    private fun deleteFile() {

    }

    fun click() {
        //dialogHelper.showOkDialog(R.string.test_title_1, R.string.test_message_1, this)
        dialogHelper.showDialog(1, this)
    }

    override fun onDialogResult(requestCode: Int, resultCode: Int) {
        dialogHelper.handleResult(requestCode, resultCode)
    }


}
