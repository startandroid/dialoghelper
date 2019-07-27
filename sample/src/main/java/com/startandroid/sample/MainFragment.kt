package com.startandroid.sample


import android.os.Bundle
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
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ...

        val dialogConfig = DialogConfig()
            .message(R.string.delete_file_question)
            .positive(R.string.yes) { deleteFile() }
            .negative(R.string.no) // do nothing
        dialogHelper.registerDialogConfig(1, dialogConfig)
    }

    private fun deleteFile() {

    }

    fun click() {
        dialogHelper.showDialog(1, this)
    }

    override fun onDialogResult(requestCode: Int, resultCode: Int) {
        dialogHelper.handleResult(requestCode, resultCode)
    }


}
