package com.zfx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import com.zfx.entry.User;


@Controller
@RequestMapping("/")
public class HomeController {

    
    @RequestMapping("*")
    public String error(){
        
        return "error";
    }
    
    @RequestMapping("loginPage")
    public String home(){
        
        return "login";
    }
    
    @RequestMapping(value="upload" ,method=RequestMethod.POST)
    public String upload(User user ,@RequestPart("photo") byte [] photo){
        
        //System.out.println(photo.length);
        return null;
        
    }
}
