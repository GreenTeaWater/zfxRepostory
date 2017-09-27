package com.wechat.service;

import org.springframework.stereotype.Service;

@Service("textService")
public class TextServiceImpl implements TextService{

	@Override
	public String parse(String content) {
		return "靜怡吃屁";
	}

}
