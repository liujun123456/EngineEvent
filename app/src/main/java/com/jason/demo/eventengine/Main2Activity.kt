package com.jason.demo.eventengine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.jason.eventengine.EventEngine
import com.jason.eventengine.EventSubscribe
import com.jason.eventengine.ThreadMode
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        EventBus.getDefault().register(this)

//        ben'di
//        EventEngine.getDefault().register(this)
//        EventEngine.getDefault().postSticky(TestBeanTwo("你好世界"))
    }

    @EventSubscribe(sticky = true,threadMode = ThreadMode.MainThread,delayTime = 3000)
    fun justBit(beanTwo: TestBeanTwo){
        Toast.makeText(this,"hahahahha",Toast.LENGTH_SHORT).show()

    }

    @Subscribe
    fun onEvent(bean:TestBeanTwo){
        findViewById<TextView>(R.id.hello).text="你好世界"
    }
}
