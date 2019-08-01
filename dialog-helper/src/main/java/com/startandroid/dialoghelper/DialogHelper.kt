package com.startandroid.dialoghelper

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class DialogHelper : DialogHandler {

    private val configs: MutableMap<Int, DialogConfig> = mutableMapOf()

    fun registerDialogConfig(dialogCode: Int, dialogConfig: DialogConfig) {
        configs[dialogCode] = dialogConfig
    }

    fun showDialog(dialogCode: Int, targetFragment: Fragment) {
        configs[dialogCode]?.let {
            DialogHelperFragment.newInstance(dialogCode, it, targetFragment)
                .show(targetFragment.activity?.supportFragmentManager, "dialog")
        }
    }

    fun showDialog(dialogCode: Int, targetActivity: AppCompatActivity) {
        DialogHelperFragment.newInstance(dialogCode, configs[dialogCode])
            .show(targetActivity.supportFragmentManager, "dialog")
    }

    override fun onDialogResult(dialogCode: Int, resultCode: Int) {
        configs[dialogCode]?.run {
            when (resultCode) {
                DialogHelperFragment.RESULT_POSITIVE -> positiveAction
                DialogHelperFragment.RESULT_NEGATIVE -> negativeAction
                DialogHelperFragment.RESULT_NEUTRAL -> neutralAction
                else -> null
            }?.invoke()
        }
    }

    fun showOkDialog(title: Int? = null, message: Int? = null, targetFragment: Fragment) {
        val config = DialogConfig().positive(android.R.string.ok)
        title?.let { config.title(it) }
        message?.let { config.title(it) }
        DialogHelperFragment.newInstance(config = config, targetFragment = targetFragment)
            .show(targetFragment.activity?.supportFragmentManager, "dialog")
    }

    fun showOkDialog(title: Int? = null, message: Int? = null, targetActivity: AppCompatActivity) {
        val config = DialogConfig().positive(android.R.string.ok)
        title?.let { config.title(it) }
        message?.let { config.title(it) }
        DialogHelperFragment.newInstance(config = config)
            .show(targetActivity.supportFragmentManager, "dialog")
    }

}

