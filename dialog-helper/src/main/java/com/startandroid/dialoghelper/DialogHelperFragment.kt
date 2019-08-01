package com.startandroid.dialoghelper

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

class DialogHelperFragment : DialogFragment() {

    companion object {
        const val EXTRA_TITLE_RES_ID = "title_res_id"
        const val EXTRA_TITLE_TEXT = "title_text"
        const val EXTRA_MESSAGE_RES_ID = "message_res_id"
        const val EXTRA_MESSAGE_TEXT = "message_text"
        const val EXTRA_POSITIVE_RES_ID = "positive_res_id"
        const val EXTRA_POSITIVE_TEXT = "positive_text"
        const val EXTRA_NEGATIVE_RES_ID = "negative_res_id"
        const val EXTRA_NEGATIVE_TEXT = "negative_text"
        const val EXTRA_NEUTRAL_RES_ID = "neutral_res_id"
        const val EXTRA_NEUTRAL_TEXT = "neutral_text"
        const val EXTRA_CANCELABLE = "cancelable"

        const val EXTRA_DIALOG_CODE = "dialog_code"

        const val RESULT_POSITIVE = 1
        const val RESULT_NEGATIVE = 2
        const val RESULT_NEUTRAL = 3

        fun newInstance(
            dialogCode: Int? = null,
            config: DialogConfig? = null,
            targetFragment: Fragment? = null
        ): DialogHelperFragment {
            val fragment = DialogHelperFragment()
            fragment.setTargetFragment(targetFragment, 0)
            val args = Bundle()
            fragment.arguments = args

            config?.run {
                dialogCode?.let { args.putInt(EXTRA_DIALOG_CODE, it) }
                titleResId?.let { args.putInt(EXTRA_TITLE_RES_ID, it) }
                titleText?.let { args.putString(EXTRA_TITLE_TEXT, it) }
                messageResId?.let { args.putInt(EXTRA_MESSAGE_RES_ID, it) }
                messageText?.let { args.putString(EXTRA_MESSAGE_TEXT, it) }
                positiveResId?.let { args.putInt(EXTRA_POSITIVE_RES_ID, it) }
                positiveText?.let { args.putString(EXTRA_POSITIVE_TEXT, it) }
                negativeResId?.let { args.putInt(EXTRA_NEGATIVE_RES_ID, it) }
                negativeText?.let { args.putString(EXTRA_NEGATIVE_TEXT, it) }
                neutralResId?.let { args.putInt(EXTRA_NEUTRAL_RES_ID, it) }
                neutralText?.let { args.putString(EXTRA_NEUTRAL_TEXT, it) }
                cancelable?.let { args.putBoolean(EXTRA_CANCELABLE, it) }
            }

            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogCode = arguments?.getInt(EXTRA_DIALOG_CODE, 0)
        val builder = AlertDialog.Builder(activity)

        builder.run {

            arguments?.getIntAndDo(EXTRA_TITLE_RES_ID) {
                setTitle(it)
            }

            arguments?.getStringAndDo(EXTRA_TITLE_TEXT) {
                setTitle(it)
            }



            arguments?.getIntAndDo(EXTRA_MESSAGE_RES_ID) {
                setMessage(it)
            }

            arguments?.getStringAndDo(EXTRA_MESSAGE_TEXT) {
                setMessage(it)
            }


            val callback = getCallback()


            val positiveDialogListener: (dialog: DialogInterface, which: Int) -> Unit = { _, _ ->
                dialogCode?.let {
                    callback?.onDialogResult(
                        it,
                        RESULT_POSITIVE
                    )
                }
            }

            arguments?.getIntAndDo(EXTRA_POSITIVE_RES_ID) {
                setPositiveButton(it, positiveDialogListener)
            }

            arguments?.getStringAndDo(EXTRA_POSITIVE_TEXT) {
                setPositiveButton(it, positiveDialogListener)
            }


            val negativeDialogListener: (dialog: DialogInterface, which: Int) -> Unit = { _, _ ->
                dialogCode?.let {
                    callback?.onDialogResult(
                        it,
                        RESULT_NEGATIVE
                    )
                }
            }

            arguments?.getIntAndDo(EXTRA_NEGATIVE_RES_ID) {
                setNegativeButton(it, negativeDialogListener)
            }

            arguments?.getStringAndDo(EXTRA_NEGATIVE_TEXT) {
                setNegativeButton(it, negativeDialogListener)
            }


            val neutralDialogListener: (dialog: DialogInterface, which: Int) -> Unit = { _, _ ->
                dialogCode?.let {
                    callback?.onDialogResult(
                        it,
                        RESULT_NEUTRAL
                    )
                }
            }

            arguments?.getIntAndDo(EXTRA_NEUTRAL_RES_ID) {
                setNeutralButton(it, neutralDialogListener)
            }

            arguments?.getStringAndDo(EXTRA_NEUTRAL_TEXT) {
                setNeutralButton(it, neutralDialogListener)
            }


        }

        arguments?.getBooleanAndDo(EXTRA_CANCELABLE) {
            isCancelable = it
        }

        return builder.create()
    }

    private fun getCallback(): DialogHandler? {
        return (targetFragment as? HasDialogHandler
            ?: activity as? HasDialogHandler)?.dialogHandler()

    }

    private fun Bundle.getIntAndDo(key: String, action: (Int) -> Unit) {
        if (containsKey(key)) {
            action.invoke(getInt(key))
        }
    }

    private fun Bundle.getStringAndDo(key: String, action: (String) -> Unit) {
        if (containsKey(key)) {
            action.invoke(getString(key, ""))
        }
    }

    private fun Bundle.getBooleanAndDo(key: String, action: (Boolean) -> Unit) {
        if (containsKey(key)) {
            action.invoke(getBoolean(key, false))
        }
    }

}