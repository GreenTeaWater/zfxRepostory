package com.wechat.service;

import org.springframework.stereotype.Service;

import com.wechat.common.util.weChatConstant;
import com.wechat.common.util.weChatUtils;


@Service("textService")
public class TextServiceImpl implements TextService{

	private static String result;

	@Override
	public String parse(String content) {
		String result = weChatConstant.error;
		
		String []  array = content.split("=");
		if(array.length != 2 ) {
			return weChatConstant.home ;
		}
		
		if(weChatConstant.ip.equals(array[0])){ //查IP
			result = weChatUtils.getIp(array[1]);
		}else if(weChatConstant.weather.equals(array[0])){  //查天气
			result = weChatUtils.getweather(array[1]);
		}
		return result;
		
		
	}
	
	 
	
}
