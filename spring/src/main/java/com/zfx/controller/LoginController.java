package com.zfx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zfx.entry.User;


@Controller
@RequestMapping("/")
public class LoginController {

   @RequestMapping(value="login")
    public ModelAndView login(User user){
       ModelAndView view = new ModelAndView() ;
       view.addObject("user", user);
       view.setViewName("home");
       return view;
    }
}
