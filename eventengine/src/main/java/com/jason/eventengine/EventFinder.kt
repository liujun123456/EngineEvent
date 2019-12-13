package com.jason.eventengine

import java.lang.reflect.Modifier
import java.util.concurrent.CopyOnWriteArrayList

/**
 * @author Liu
 * @Date   2019-12-12
 * @mobile 18711832023
 */
class EventFinder(var processor:EventProcessor){

    private val BRIDGE = 0x40

    private val SYNTHETIC = 0x1000

    private val MODIFIERS_IGNORE = Modifier.ABSTRACT or Modifier.STATIC or BRIDGE or SYNTHETIC


    fun findMethods(classObject: Any,eventSource:MutableMap<Any,CopyOnWriteArrayList<MethodHolder>>){
        //todo 每次新注册 都重新刷新对应事件源，防止重复 如果做缓存，那粘性事件怎么解决 ？？
        val methodList=CopyOnWriteArrayList<MethodHolder>()

        eventSource[classObject]=methodList

        currentClazz=classObject.javaClass

        while (currentClazz!=null){

            findByReflection(currentClazz!!,methodList,classObject)

            moveToSuperclass(currentClazz!!)
        }
    }

    private fun findByReflection(clazz: Class<Any>,methodList:CopyOnWriteArrayList<MethodHolder>,classObject: Any){
        val methods=clazz.declaredMethods
        methods.forEach {
            val modifier=it.modifiers
            if (modifier and Modifier.PUBLIC != 0 && modifier and MODIFIERS_IGNORE == 0){
                val params=it.parameterTypes
                if (params.size==1){
                    val annotation=it.getAnnotation(EventSubscribe::class.java)
                    if (annotation!=null){
                        val holder= MethodHolder(params[0].name,it,classObject,annotation.sticky,
                            annotation.threadMode,annotation.delayTime)
                        methodList.add(holder)
                        if (holder.sticky){
                            processor.checkStickyEvent(holder)
                        }
                    }
                }else if(it.isAnnotationPresent(EventSubscribe::class.java)){
                    throw EngineEventException("${it.declaringClass.name}.${it.name}" +
                        "must have exactly 1 parameter")
                }
            }else if (it.isAnnotationPresent(EventSubscribe::class.java)){
                throw EngineEventException("${it.declaringClass.name}.${it.name}" +
                    "is a illegal @Subscribe method: must be public, non-static, and non-abstract")
            }
        }
    }

    private var currentClazz: Class<in Any>?=null

    private fun moveToSuperclass(clazz: Class<Any>){
        currentClazz=clazz.superclass
        currentClazz?.let {
            val superName = it.name
            if (superName.startsWith("java.") || superName.startsWith("javax.") || superName.startsWith("android.")) {
                currentClazz=null
            }
        }
    }


}
