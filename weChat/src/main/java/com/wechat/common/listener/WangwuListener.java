package com.wechat.common.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

import com.wechat.common.event.ContentEvent;

/*@Component  */
public class WangwuListener implements SmartApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		  System.out.println("王五在孙六之前收到新的内容：" + event.getSource());  
		
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		  return 1;  
	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		// TODO Auto-generated method stub
		 return eventType == ContentEvent.class;  
	}

	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		// TODO Auto-generated method stub
		  return sourceType == String.class;  
	}

}
