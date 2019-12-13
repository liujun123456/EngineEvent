package com.jason.demo.eventengine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.jason.eventengine.EventEngine
import com.jason.eventengine.EventSubscribe
import com.jason.eventengine.ThreadMode

class MainActivity :  BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EventEngine.getDefault().register(this)
        findViewById<TextView>(R.id.hello).setOnClickListener {
//            EventEngine.getDefault().post(TestBeanTwo("你好世界"))

            startActivity(Intent(this,Main2Activity::class.java))
        }

    }


    @EventSubscribe(threadMode = ThreadMode.MainThread)
    fun onEvent(bean:TestBeanTwo){
        findViewById<TextView>(R.id.hello).text="你好世界"
    }
}
