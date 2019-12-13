# 关于EventBus
  1. EventBus是一个轻量级的发布和订阅事件的库
  2. 有基本超过2W的开发者在使用
  3. 服务于许多大体量的App
  4. 集成和使用方式都特别方便
    ```
    
    //注册与注销
    
     @Override
     public void onStart() {
         super.onStart();
         EventBus.getDefault().register(this);
     }
    
     @Override
     public void onStop() {
         super.onStop();
         EventBus.getDefault().unregister(this);
     }
    
    //定义事件
    public class MessageEvent
    
    //准备订阅者
    @Subscribe(threadMode = ThreadMode.MAIN)  
    public void onMessageEvent(MessageEvent event)
    
    //发布者
    EventBus.getDefault().post(new MessageEvent());

    ```  
# 疑问
  ## EventBus是如何进行事件关联的
   

