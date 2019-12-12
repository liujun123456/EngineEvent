package com.jason.demo.eventengine

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jason.eventengine.EventSubscribe

/**
 * @author Liu
 * @Date   2019-12-12
 * @mobile 18711832023
 */
open class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @EventSubscribe(sticky = true)
    fun justBit(beanTwo: TestBeanTwo){
        Toast.makeText(this,"hahahahha", Toast.LENGTH_SHORT).show()
    }
}
