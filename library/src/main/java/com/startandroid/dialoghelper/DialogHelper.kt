package com.startandroid.dialoghelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class DialogHelper {

    private val configs: MutableMap<Int, DialogConfig> = mutableMapOf()

    fun registerDialogConfig(dialogCode: Int, dialogConfig: DialogConfig) {
        configs[dialogCode] = dialogConfig
    }

    fun showDialog(dialogCode: Int, targetFragment: Fragment) {
        DialogHelperFragment.newInstance(
            dialogCode,
            configs[dialogCode],
            targetFragment
        )
            .show(targetFragment.activity?.supportFragmentManager, "dialog")
    }

    fun showDialog(dialogCode: Int, targetActivity: AppCompatActivity) {
        DialogHelperFragment.newInstance(dialogCode, configs[dialogCode])
            .show(targetActivity.supportFragmentManager, "dialog")
    }

    fun handleResult(dialogCode: Int, resultCode: Int, data: Intent?) {
        configs[dialogCode]?.run {
            when (resultCode) {
                DialogHelperFragment.RESULT_POSITIVE -> positiveAction
                DialogHelperFragment.RESULT_NEGATIVE -> negativeAction
                DialogHelperFragment.RESULT_NEUTRAL -> neutralAction
                else -> null
            }?.invoke(data)
        }
    }

}

