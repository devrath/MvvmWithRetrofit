package com.demo.application.utils

import android.view.View


/** A type which binds the views in a layout XML to fields. */
interface ViewBinding {
    /**
     * Returns the outermost [View] in the associated layout file. If this binding is for a
     * `<merge>` layout, this will return the first view inside of the merge tag.
     */
    fun getRoot(): View
}