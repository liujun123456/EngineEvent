package com.jason.eventengine

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


/**
 * @author Liu
 * @Date   2019-12-12
 * @mobile 18711832023
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class EventSubscribe(

    /**
     * 事件执行线程，默认相同线程
     */
    val threadMode: ThreadMode = ThreadMode.POSTING,

    /**
     * 是否为粘性事件 默认false
     */
    val sticky: Boolean = false,

    /**
     * 延迟执行事件
     */
    val delayTime: Long = 0
)
