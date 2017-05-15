package com.zfx.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.Param;

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
     
    @RequestMapping(value="home_upload" ,method=RequestMethod.POST)
    public ModelAndView upload(User user , @RequestPart("photo")MultipartFile multipartFile) throws IllegalStateException, IOException{
        
    	multipartFile.transferTo(new File("D:/" + multipartFile.getOriginalFilename()));
    	//photo.write("D:/" + photo.getSubmittedFileName()); //Part photo
    	
	   ModelAndView view = new ModelAndView() ;
       view.addObject("user", user);
       view.setViewName("uploadSuccess");
        return view;
        
    }
}
