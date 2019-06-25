package com.wechat.common.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.wechat.common.event.ContentEvent;

/*@Component  */
public class ZhangsanListener implements ApplicationListener<ContentEvent>{

	@Override
	public void onApplicationEvent(ContentEvent event) {
		 System.out.println("张三收到了新的内容：" + event.getSource());  
	}

}
