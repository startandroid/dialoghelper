package com.startandroid.dialoghelper

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

class DialogHelperFragment : DialogFragment() {

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_MESSAGE = "message"
        const val EXTRA_POSITIVE_TEXT = "positive_text"
        const val EXTRA_NEGATIVE_TEXT = "negative_text"
        const val EXTRA_NEUTRAL_TEXT = "neutral_text"
        const val EXTRA_DIALOG_CODE = "dialog_code"

        const val RESULT_POSITIVE = 1
        const val RESULT_NEGATIVE = 2
        const val RESULT_NEUTRAL = 3

        fun newInstance(dialogCode: Int, config: DialogConfig?, targetFragment: Fragment? = null): DialogHelperFragment {
            val fragment = DialogHelperFragment()
            fragment.setTargetFragment(targetFragment, dialogCode)

            val args = Bundle()
            fragment.arguments = args

            args.putInt(EXTRA_DIALOG_CODE, dialogCode)
            config?.run {
                title?.let { args.putInt(EXTRA_TITLE, it) }
                message?.let { args.putInt(EXTRA_MESSAGE, it) }
                positiveText?.let { args.putInt(EXTRA_POSITIVE_TEXT, it) }
                negativeText?.let { args.putInt(EXTRA_NEGATIVE_TEXT, it) }
                neutralText?.let { args.putInt(EXTRA_NEUTRAL_TEXT, it) }
            }

            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogCode = arguments?.getInt(EXTRA_DIALOG_CODE, 0) ?: 0
        val builder = AlertDialog.Builder(activity)

        builder.run {

            arguments?.getIntAndDo(EXTRA_TITLE) {
                setTitle(getString(it))
            }

            arguments?.getIntAndDo(EXTRA_MESSAGE) {
                setMessage(getString(it))
            }

            val callback  = getCallback()

            arguments?.getIntAndDo(EXTRA_POSITIVE_TEXT) {
                setPositiveButton(getString(it)) { _, _ ->
                    callback?.onDialogResult(dialogCode,
                        RESULT_POSITIVE, Intent())
                }
            }

            arguments?.getIntAndDo(EXTRA_NEGATIVE_TEXT) {
                setNegativeButton(getString(it)) { _, _ ->
                    callback?.onDialogResult(dialogCode,
                        RESULT_NEGATIVE, Intent())
                }
            }

            arguments?.getIntAndDo(EXTRA_NEUTRAL_TEXT) {
                setNeutralButton(getString(it)) { _, _ ->
                    callback?.onDialogResult(dialogCode,
                        RESULT_NEUTRAL, Intent())
                }
            }

        }
        return builder.create()
    }

    private fun getCallback(): DialogHelperCallback? {
        return targetFragment as? DialogHelperCallback
            ?: activity as? DialogHelperCallback

    }

    private fun Bundle.getIntAndDo(key: String, action: (Int) -> Unit) {
        if (containsKey(key)) {
            action.invoke(getInt(key))
        }
    }



}