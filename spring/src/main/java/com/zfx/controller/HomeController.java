package com.zfx.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zfx.common.util.StringUtil;
import com.zfx.entry.User;


@Controller
@RequestMapping("/")
public class HomeController extends AbstractController{

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
    
    @RequestMapping("home_exceptipn")
    public ModelAndView exception(){
    	StringUtil.getString();
		return null;
    }
    
    @RequestMapping("home_flashRedirect")
    public String flashRedirect(User user ,RedirectAttributes model){
    	
    	model.addAttribute("name", user.getName());
    	model.addFlashAttribute("password", user.getPassword());
    	model.addFlashAttribute("user", user);
		return "redirect:/login";
    	
    }
    
}
