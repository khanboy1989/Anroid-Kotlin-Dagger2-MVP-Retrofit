package net.cocooncreations.dimvparchitecture.ui.base

import android.content.Context

interface BaseView {

    /**
     * Returns the context in which the application is running.
     * @return the context in which the application is running
     */

    fun getContext():Context

}