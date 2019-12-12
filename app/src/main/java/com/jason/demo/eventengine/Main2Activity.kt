package com.jason.demo.eventengine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jason.eventengine.EventEngine
import com.jason.eventengine.EventSubscribe

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        EventEngine.getDefault().register(this)
    }

    @EventSubscribe(sticky = true)
    fun justBit(beanTwo: TestBeanTwo){
        Toast.makeText(this,"hahahahha",Toast.LENGTH_SHORT).show()

    }
}
