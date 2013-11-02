package cn.com.jnpc.foreign.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.jnpc.ems.common.EMSUtils;
import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.po.Users;
import cn.com.jnpc.foreign.utils.Untils;

@Controller
public class LoginController{
    @RequestMapping(value = "/login.html")
    public String login(Users user,HttpServletRequest request,Model model){
	User use=null;
	boolean flag=false;
	try {
	    if(Untils.getSessionP(request, "user") != null){
		flag=true;
	    }else{
    	    if(user!=null && Untils.NotNull(user.getUserid()) && Untils.NotNull(user.getPasswd())){
    		flag= Untils.verifyUser( user.getUserid(),user.getPasswd());
    		if (flag) {
    			use=(User)EMSUtils.findUserByAccount(user.getUserid());
    			boolean check=Untils.setSessionP(request, "user", (Object)use);
    			if(check){
    			    model.addAttribute("user",use);
    			}else{
    			    model.addAttribute("error","系统错误请联系IT部门");
    			}
    		}else{
    		    model.addAttribute("error","用户名或者密码错误");
    		}
    	    }else{
    		model.addAttribute("error","请填写用户名密码!");
    	    }
	    }
	} catch (Exception e) {
	    System.out.println(e.getCause()); 
	}
	if(flag){
	    return "index/index";	    
	}else{
	    return "login";//返回路径的问题.
	}
    }
    @RequestMapping(value = "/login.do")
    public String login(HttpServletRequest request,Model model){
	return "index/index";
    }
    @RequestMapping(value = "/loginout.html")
    public String loginout(HttpServletRequest request,Model model){
	Untils.moveOutSessionP(request,"user");
	return "/index";
    }
    
}
