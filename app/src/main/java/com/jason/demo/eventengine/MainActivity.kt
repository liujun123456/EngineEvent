package com.jason.demo.eventengine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.jason.eventengine.EventEngine
import com.jason.eventengine.EventSubscribe
import com.jason.eventengine.ThreadMode
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity :  BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EventBus.getDefault().register(this)

//        EventEngine.getDefault().register(this)
        findViewById<TextView>(R.id.hello).setOnClickListener {
//            EventEngine.getDefault().post(TestBeanTwo("你好世界"))

            startActivity(Intent(this,Main2Activity::class.java))
        }

    }


    @Subscribe
    fun onEvent(bean:TestBeanTwo){
        findViewById<TextView>(R.id.hello).text="你好世界"
    }

    @Subscribe
    fun onEventTwo(bean:TestBeanTwo){
        findViewById<TextView>(R.id.hello).text="你好世界"
    }

}
