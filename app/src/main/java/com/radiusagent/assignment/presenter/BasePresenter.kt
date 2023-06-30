package com.radiusagent.assignment.presenter

abstract class BasePresenter<V> {

    protected var view: V? = null

    fun attachView(view: V) {
        this.view = view
        init()
    }

    fun detachView() {
        destroy()
        this.view = null
    }

    open fun init() {}
    open fun destroy() {}
}