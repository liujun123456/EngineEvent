package com.jason.eventengine

import android.os.Handler
import android.os.Looper
import android.os.Message

/**
 * @author Liu
 * @Date   2019-12-12
 * @mobile 18711832023
 */
class HandlerEvent(looper: Looper):Handler(looper){

    fun isMainThread():Boolean{
        return looper== Looper.myLooper()
    }


    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)
    }


}
