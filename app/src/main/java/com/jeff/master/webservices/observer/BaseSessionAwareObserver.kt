package com.jeff.master.webservices.observer

interface BaseSessionAwareObserver {

    fun onSessionExpiredError()

    fun onCommonError(e: Throwable)
}
