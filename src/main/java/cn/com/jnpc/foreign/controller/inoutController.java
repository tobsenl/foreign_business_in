package cn.com.jnpc.foreign.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.po.FiInout;
import cn.com.jnpc.foreign.service.InOutServices;
import cn.com.jnpc.foreign.service.InvitationServices;
import cn.com.jnpc.foreign.utils.Untils;
import cn.com.jnpc.foreign.utils.springContextUtil;

@Controller
@RequestMapping(value = "/inout")
public class inoutController {
    InOutServices inoutServices;
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder){
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(  
          dateFormat, false)); 
    }
    
    @RequestMapping(value = "/inout_insert.html")
    public @ResponseBody String inout_store(
	    @ModelAttribute(value = "inoutform") FiInout inout,
	    HttpServletRequest request, HttpServletResponse response) {
	inoutServices = (InOutServices) springContextUtil
		.getBean("InOutServices");
	String message;
	User user = (User) Untils.getSessionP(request, "user");
	List id_list=null;
	String id=Untils.NotNull(request.getParameter("inout_pp_id"))?request.getParameter("inout_pp_id"):"";
	if(Untils.NotNull(id)){
	    id_list=new ArrayList();
	    String [] li=id.split(",");
	    for (String val : li) {
		//不为0是因为页面参数勾选的时候默认的全选value为0
		if(Untils.NotNull(val) && val != "0"){
		    id_list.add(val);
		}
	    }
	    message=inoutServices.store(inout,id_list,user);
	    return message;
	}else{
	    return "数据异常,请确认提交的内容是否正确!";
	}
    }
}
