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
    @RequestMapping(value = "/login.do")
    public String login(Users user,HttpServletRequest request,Model model){
	User use=null;
	boolean flag=false;
	user=new Users();
	try {
		 //user=EMSUtils.findUserByAccount(userId);
		flag= Untils.verifyUser( user.getUserid(),user.getPasswd());
		if (flag) {
			use=(User)EMSUtils.findUserByAccount(user.getUserid());
			boolean check=Untils.setSessionP(request, "user", (Object)user);
			if(check){
			    model.addAttribute("user",use);
			}else{
			    model.addAttribute("error","系统错误请联系IT部门");
			}
		}else{
		    model.addAttribute("error","用户名或者密码错误");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	if(flag){
	    return "index/index";	    
	}else{
	    return "login";//返回路径的问题.
	}
    }
    @RequestMapping(value = "/login.html")
    public String login(HttpServletRequest request,Model model){
	return "index/index";
    }
    
}
