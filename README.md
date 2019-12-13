# 关于EngineEvent
  1. EngineEvent是参考EventBus写的一个超轻量级的事件分发库，在EventBus的基础上，新增了事件延迟处理的功能
  2. 支持粘性事件处理
  3. 支持不同的事件处理线程
  4. 支持高精度匹配
  
# 集成

  ```
  #在项目目录build.gradle下加入jcenter库
  allprojects {
      repositories {
          google()
          jcenter()
          
      }
  }
  #在app目录下的build.gradle加
      compile 'com.jason.simple:engineEvent:1.0.0'
  ```

#使用
  添加订阅者
  
  ```
    EventEngine.getDefault().register(this)
    
  ```
  删除订阅者
    
  ```
    EventEngine.getDefault().unregister(this)
      
  ```
  
  发送事件
  
  ```
  
    //发送普通事件
    EventEngine.getDefault().post(TestBeanTwo("你好世界"))
    
    //发送粘性事件
    EventEngine.getDefault().postSticky(TestBeanTwo("你好世界"))
         
   ```
   
   定义事件
   ```
    @EventSubscribe(sticky = true,threadMode = ThreadMode.MainThread,delayTime = 3000)
    fun justBit(beanTwo: TestBeanTwo){
        Toast.makeText(this,"hahahahha",Toast.LENGTH_SHORT).show()

    }
    
    delayTime 为毫秒级

   ```