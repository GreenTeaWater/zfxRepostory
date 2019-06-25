package com.zfx.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.zfx.common.exception.SpringException;

public abstract class AbstractController {

	@ExceptionHandler(SpringException.class)
	public ModelAndView handleSpringException(SpringException springException){
		 ModelAndView view = new ModelAndView() ;
		 view.addObject("springException", springException);
		 view.setViewName("exception");
		 return view;
		
	}
	
}
