package com.startandroid.dialoghelper


// TODO add possibility to use text instead of resId
class DialogConfig {

    internal var title: Int? = null

    internal var message: Int? = null

    internal var positiveText: Int? = null

    internal var negativeText: Int? = null

    internal var neutralText: Int? = null

    internal var positiveAction: (() -> Unit)? = null

    internal var negativeAction: (() -> Unit)? = null

    internal var neutralAction: (() -> Unit)? = null

    fun title(text: Int) = apply { title = text }

    fun message(text: Int) = apply { message = text }

    fun positive(text: Int, action: (() -> Unit)? = null) = apply {
        positiveText = text
        positiveAction = action
    }

    fun negative(text: Int, action: (() -> Unit)? = null) = apply {
        negativeText = text
        negativeAction = action
    }

    fun neutral(text: Int, action: (() -> Unit)? = null) = apply {
        neutralText = text
        neutralAction = action
    }
}