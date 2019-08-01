package com.startandroid.sample


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.startandroid.dialoghelper.DialogConfig
import com.startandroid.dialoghelper.DialogHandler
import com.startandroid.dialoghelper.DialogHelper
import com.startandroid.dialoghelper.HasDialogHandler


class MainFragment : Fragment(), HasDialogHandler {

    companion object {
        const val CODE_DIALOG_WITHOUT_ACTIONS = 1
        const val CODE_DIALOG_WITH_ACTIONS = 2
        const val TAG = "dialoghelper"
    }

    override fun dialogHandler(): DialogHandler? = dialogHelper

    private val dialogHelper = DialogHelper()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.findViewById<TextView>(R.id.btn_dialog_simple_ok)?.setOnClickListener {
            dialogHelper.showOkDialog(
                title = R.string.title,
                message = R.string.message,
                targetFragment = this
            )
        }

        view?.findViewById<TextView>(R.id.btn_dialog_without_actions)?.setOnClickListener {
            dialogHelper.showDialog(Companion.CODE_DIALOG_WITHOUT_ACTIONS, this)
        }

        view?.findViewById<TextView>(R.id.btn_dialog_with_actions)?.setOnClickListener {
            dialogHelper.showDialog(Companion.CODE_DIALOG_WITH_ACTIONS, this)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerDialogs()
    }

    private fun registerDialogs() {
        var dialogConfig = DialogConfig()
            .title(R.string.title)
            .message(R.string.message)
            .positive(R.string.yes) { positiveAction() }
            .negative(R.string.no) { negativeAction() }
            .neutral(R.string.cancel) // no action
            .cancelable(true)
        dialogHelper.registerDialogConfig(Companion.CODE_DIALOG_WITH_ACTIONS, dialogConfig)

        dialogConfig = DialogConfig()
            .message("Message")
            .positive("I understand")
            .cancelable(false)
        dialogHelper.registerDialogConfig(Companion.CODE_DIALOG_WITHOUT_ACTIONS, dialogConfig)
    }

    fun positiveAction() {
        Log.d(Companion.TAG, "positive action")
    }

    fun negativeAction() {
        Log.d(Companion.TAG, "negative action")
    }


}
