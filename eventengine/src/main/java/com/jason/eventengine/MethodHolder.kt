package com.jason.eventengine

import java.lang.reflect.Method

/**
 * @author Liu
 * @Date   2019-12-12
 * @mobile 18711832023
 */
class MethodHolder(
    var eventType: String,
    var method: Method,
    var classPlace: Any,
    var sticky: Boolean,
    var threadMode: ThreadMode,
    var delayTime: Long
)
