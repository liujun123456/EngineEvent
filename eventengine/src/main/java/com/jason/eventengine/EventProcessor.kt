package com.jason.eventengine

import android.os.Looper
import java.util.*
import java.util.concurrent.Executors

/**
 * @author Liu
 * @Date   2019-12-12
 * @mobile 18711832023
 */
class EventProcessor{

    private var handler:HandlerEvent= HandlerEvent(Looper.getMainLooper())

    private var pool= Executors.newSingleThreadExecutor()

    private val unDealStickyEvent: Vector<Any> = Vector()


    fun enqueue(holder:MethodHolder,any: Any){
        when {
            holder.threadMode==ThreadMode.BackGround -> postBackground(holder,any)
            holder.threadMode==ThreadMode.MainThread -> postMainThread(holder,any)
            else -> postCurrentThread(holder,any)
        }

    }

    fun putStickyEvent(any: Any){
        unDealStickyEvent.add(any)
    }

    fun isMainThread():Boolean{
        return handler.isMainThread()
    }

    fun checkStickyEvent(holder: MethodHolder){
        unDealStickyEvent.forEach {
            if (it.javaClass.name==holder.eventType){
                enqueue(holder,it)
                unDealStickyEvent.remove(it)
            }
        }
    }

    private fun postCurrentThread(holder:MethodHolder,any: Any){
        if (isMainThread()){
            postMainThread(holder,any)
        }else{
            postBackground(holder,any)
        }
    }

    private fun postBackground(holder:MethodHolder,any: Any){
        pool.submit({
            if (holder.delayTime!=0L){
                Thread.sleep(holder.delayTime)
            }
            holder.method.invoke(holder.classPlace,any)
        },holder.delayTime)
    }

    private fun postMainThread(holder:MethodHolder,any: Any){
        handler.postDelayed({
            holder.method.invoke(holder.classPlace,any)
        },holder.delayTime)
    }

}

