package com.startandroid.dialoghelper

class DialogConfig {

    internal var titleResId: Int? = null

    internal var titleText: String? = null

    internal var messageResId: Int? = null

    internal var messageText: String? = null

    internal var positiveResId: Int? = null

    internal var positiveText: String? = null

    internal var negativeResId: Int? = null

    internal var negativeText: String? = null

    internal var neutralResId: Int? = null

    internal var neutralText: String? = null

    internal var positiveAction: (() -> Unit)? = null

    internal var negativeAction: (() -> Unit)? = null

    internal var neutralAction: (() -> Unit)? = null

    internal var cancelable: Boolean? = null

    fun title(text: Int) = apply {
        titleResId = text
        titleText = null
    }

    fun title(text: String) = apply {
        titleResId = null
        titleText = text
    }

    fun message(text: Int) = apply {
        messageResId = text
        messageText = null
    }

    fun message(text: String) = apply {
        messageResId = null
        messageText = text
    }

    fun positive(text: Int, action: (() -> Unit)? = null) = apply {
        positiveResId = text
        positiveText = null
        positiveAction = action
    }

    fun positive(text: String, action: (() -> Unit)? = null) = apply {
        positiveResId = null
        positiveText = text
        positiveAction = action
    }

    fun negative(text: Int, action: (() -> Unit)? = null) = apply {
        negativeResId = text
        negativeText = null
        negativeAction = action
    }

    fun negative(text: String, action: (() -> Unit)? = null) = apply {
        negativeResId = null
        negativeText = text
        negativeAction = action
    }

    fun neutral(text: Int, action: (() -> Unit)? = null) = apply {
        neutralResId = text
        neutralText = null
        neutralAction = action
    }

    fun neutral(text: String, action: (() -> Unit)? = null) = apply {
        neutralResId = null
        neutralText = text
        neutralAction = action
    }

    fun cancelable(cancelable: Boolean) = apply {
        this.cancelable = cancelable
    }
}