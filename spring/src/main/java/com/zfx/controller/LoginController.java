package com.zfx.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zfx.entry.User;


@Controller
@RequestMapping("/")
public class LoginController {

   @RequestMapping(value="login")
    public ModelAndView login(@Valid User user ,Errors errors){
       ModelAndView view = new ModelAndView() ;
       view.addObject("user", user);
	   if(errors.hasErrors()) {
		   view.setViewName("index");
	       return view;
	   }
       view.setViewName("home");
       return view;
    }
}
