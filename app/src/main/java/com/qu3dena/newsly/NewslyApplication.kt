package com.qu3dena.newsly

import android.app.Application

/**
 * Application class for the Newsly app.
 *
 * Provides a singleton [instance] of the application context for use throughout the app.
 */
class NewslyApplication : Application() {

    companion object {
        /**
         * Singleton instance of [NewslyApplication].
         * Initialized in [onCreate].
         */
        lateinit var instance: NewslyApplication
            private set
    }

    /**
     * Initializes the [instance] property when the application is created.
     */
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}