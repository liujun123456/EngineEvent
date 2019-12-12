package com.jason.eventengine

import java.util.concurrent.CopyOnWriteArrayList

/**
 * @author Liu
 * @Date   2019-12-12
 * @mobile 18711832023
 */
class EventEngine private constructor() {

    private var processor:EventProcessor=EventProcessor()

    private var finder: EventFinder = EventFinder(processor)

    private val eventSource:MutableMap<Any,CopyOnWriteArrayList<MethodHolder>> = HashMap()


    companion object {

        private var engine:EventEngine?=null

        @JvmStatic
        fun getDefault():EventEngine{
            if (engine==null){
                synchronized(EventEngine::class.java){
                    if (engine==null){
                        engine=EventEngine()
                    }
                }
            }
            return engine?:EventEngine()
        }
    }

    fun register(classObject: Any){
        synchronized(eventSource){
            finder.findMethods(classObject,eventSource)
        }

    }

    fun unregister(classObject: Any){
        synchronized(eventSource){
            eventSource.remove(classObject)
        }
    }

    /**
     * 匹配事件源
     */
    fun post(any: Any){
        val clazz=any.javaClass
        eventSource.entries.forEach {
            it.value.forEach {holder->
                if (clazz.name==holder.eventType){
                    processor.enqueue(holder,any)
                }
            }
        }
    }

    /**
     * 匹配粘性事件，如果暂时不匹配，先保留
     */
    fun postSticky(any: Any){
        var isMatch=false
        val clazz=any.javaClass
        eventSource.entries.forEach {
            it.value.forEach {holder->
                if (clazz.name==holder.eventType){
                    processor.enqueue(holder,any)
                    isMatch=true
                }
            }
        }
        if (!isMatch){
            processor.putStickyEvent(any)
        }

    }

    fun isMainThread():Boolean{
        return processor.isMainThread()
    }
}
