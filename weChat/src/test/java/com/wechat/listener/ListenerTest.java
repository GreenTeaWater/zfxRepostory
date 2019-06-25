package com.wechat.listener;

import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.context.ApplicationContext;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import com.wechat.common.event.ContentEvent;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:applicationContext.xml"})  
public class ListenerTest {

	@Autowired  
    private ApplicationContext applicationContext;  
    @Test  
    public void testPublishEvent() {  
        applicationContext.publishEvent(new ContentEvent("今年是龙年的博客更新了"));  
    }  
	  
}
