package cn.com.jnpc.foreign.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.po.FiInvitation;
import cn.com.jnpc.foreign.service.ForeignServices;
import cn.com.jnpc.foreign.service.InvitationServices;
import cn.com.jnpc.foreign.service.MiddleServices;
import cn.com.jnpc.foreign.utils.DateUtil;
import cn.com.jnpc.foreign.utils.Untils;
import cn.com.jnpc.foreign.utils.springContextUtil;
import cn.com.jnpc.foreign.vo.PageMybatis;

@Controller
@RequestMapping(value = "/invitation")
public class invitationController{
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder){
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(  
          dateFormat, false)); 
    }
    private InvitationServices invitationServices;
    
    @RequestMapping(value = "/invitation_info.html")
    public String invitation(HttpServletRequest request,
	    HttpServletResponse response) {
	return "/invitation/invitation_info";
    }
    @RequestMapping(value = "/invitation_{kind}.html")//edit beuse
    public String invitationQueryAll(@PathVariable("kind") String kind,HttpServletRequest request,
	    HttpServletResponse response,Model model) {
	invitationServices = (InvitationServices) springContextUtil
		.getBean("InvitationServices");
	PageMybatis page=new PageMybatis();
	page.setQuerysql(" t1.* from fi_invitation t1 where 1=1 ");
	page.setNowpage("1");
	List<FiInvitation> list= invitationServices.QueryList("All",page);
	model.addAttribute("invitation_list", list);
	page.setPageurl(Untils.requestPath(request));
	model.addAttribute("page", page);
	if(kind.equals("edit")){
	    return "/invitation/invitation_edit";
	}else if(kind.equals("beuse")){
	    return "/invitation/invitation_beuse";
	}else{
	    return "";
	}
    }
    @RequestMapping(value = "/invitation_query.html")
    public String invitationQuery(HttpServletRequest request,
	    HttpServletResponse response,Model model) {
	String invitation_id_q=Untils.NotNull(request.getParameter("invitation_id_q"))?request.getParameter("invitation_id_q"):""; 
	String is_use_q=Untils.NotNull(request.getParameter("is_use_q"))?request.getParameter("is_use_q"):"";
	String indate_q=Untils.NotNull(request.getParameter("indate_q"))?request.getParameter("indate_q"):"";
	String foreign_id_q=Untils.NotNull(request.getParameter("foreign_id_q"))?request.getParameter("foreign_id_q"):"";
	invitationServices = (InvitationServices) springContextUtil
		.getBean("InvitationServices");
	PageMybatis page=new PageMybatis();
	page.setNowpage("1");
	List<FiInvitation> list=null;
	page.setQuerysql(" t1.* from fi_invitation t1 where 1=1 ");
	if(Untils.NotNull(foreign_id_q) || Untils.NotNull(indate_q) || Untils.NotNull(is_use_q) || Untils.NotNull(invitation_id_q)){
	    list= invitationServices.Queryandforeign(foreign_id_q,invitation_id_q,is_use_q,indate_q,page);
	}else{
	    list= invitationServices.QueryList("All",page);
	}
	List list_jason=new ArrayList();
	if(list!=null && list.size()>0){
	    for (FiInvitation invit : list) {
		JSONObject object = new JSONObject();
		object.put("id",invit.getId());
		object.put("invitationId",invit.getInvitationId());
		object.put("stayTime",invit.getStayTime());
		object.put("isUse",invit.getIsUse());
		object.put("gobackTimes",invit.getGobackTimes());
		object.put("arrivedDate",DateUtil.dateToString(invit.getArrivedDate(), "yyyy-MM-dd"));
		list_jason.add(object);
	    }
	}
	response.setContentType("text/Xml;charset=utf-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("expires", 0);
	PrintWriter out = null;
	try {
	    out = response.getWriter();
	    out.println(list_jason);
	    
	} catch (IOException ex1) {
	    ex1.printStackTrace();
	} finally {
	    out.close();
	}
	return null;
    }
    @RequestMapping(value = "/AjaxQuery_detail.html")
    public String AjaxQuery_list(HttpServletRequest request,
	    HttpServletResponse response,Model model) {
	String invitation_id=Untils.NotNull(request.getParameter("invitation_id"))?request.getParameter("invitation_id"):""; 
	FiInvitation invitation=null;
	List foreign_list=null;
	invitationServices = (InvitationServices) springContextUtil
		.getBean("InvitationServices");
	if(Untils.NotNull(invitation_id)){
		invitation= invitationServices.QueryById(invitation_id);
		if(invitation !=null && Untils.NotNull(invitation.getId())){
		    List<FiInvitation> invitation_list=new ArrayList<FiInvitation>();
		    invitation_list.add(invitation);
		    foreign_list=invitationServices.QueryLinkForeign(invitation_list);
		}
	}
	List list_jason=null;
	if(invitation !=null){
	    list_jason=new ArrayList();
	    JSONObject object = new JSONObject();
	    object.put("id",invitation.getId());
	    object.put("invitationId",invitation.getInvitationId());
	    object.put("fkAttachmentId",invitation.getFkAttachmentId());
	    object.put("stayTime",invitation.getStayTime());
	    object.put("gobackTimes",invitation.getGobackTimes());
	    object.put("arrivedDate",DateUtil.dateToString(invitation.getArrivedDate(), "yyyy-MM-dd"));
	    object.put("leavingDate",DateUtil.dateToString(invitation.getLeavingDate(), "yyyy-MM-dd"));
	    object.put("foreign_list",foreign_list);
	    list_jason.add(object);
	}
	response.setContentType("text/Xml;charset=utf-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("expires", 0);
	PrintWriter out = null;
	try {
	    out = response.getWriter();
	    out.println(list_jason);
	    
	} catch (IOException ex1) {
	    ex1.printStackTrace();
	} finally {
	    out.close();
	}
	return null;
    }

    @RequestMapping(value = "/invitation_add.html")
    public String addinvitation(@RequestParam MultipartFile attachment,
	    HttpServletRequest request,Model model,HttpServletResponse response) throws IOException {
	// 获取session中保存的对象
	User user = (User) Untils.getSessionP(request, "user");
	invitationServices=(InvitationServices)springContextUtil.getBean("InvitationServices");
	String massage=invitationServices.store(request,attachment,user);
	model.addAttribute("return_info", massage);
	return "/invitation/invitation_info";
    }
    @RequestMapping(value = "/invitation_updata.html")
//    public String addinvitation(HttpServletRequest request,Model model,HttpServletResponse response) throws IOException {
	public String updateinvitation(@ModelAttribute(value="from1") FiInvitation invitation,
		HttpServletRequest request,Model model,HttpServletResponse response) throws IOException {
	String gobackTimes = Untils.NotNull(request
		.getParameter("gobackTimes")) ? request
		.getParameter("gobackTimes") : "";
	// 获取session中保存的对象
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	MultipartFile attachment=(CommonsMultipartFile)multipartRequest.getFile("attachment");
	User user = (User) Untils.getSessionP(request, "user");
	invitationServices=(InvitationServices)springContextUtil.getBean("InvitationServices");
	String massage=invitationServices.storeUpdata(invitation,request,attachment,user);
	//model.addAttribute("return_info", massage);
	//return "/invitation/invitation_info";
	return null;
    }
    @RequestMapping(value = "/invitation_usedit.html")
    public String Edit_usedit(HttpServletRequest request,
	    HttpServletResponse response){
	String id=Untils.NotNull(request.getParameter("usedit_id_list"))?request.getParameter("usedit_id_list"):"";
	String status=Untils.NotNull(request.getParameter("usedit_status"))?request.getParameter("usedit_status"):"";
	invitationServices = (InvitationServices) springContextUtil.getBean("InvitationServices");
	String s="";
	JSONObject object = new JSONObject();
	String message="";
	try{
	if(Untils.NotNull(id)){
	    if(Untils.NotNull(status)){
		String [] array=id.split(",");
		for (int i = 0; i < array.length; i++) {
		    if(Untils.NotNull(array[i])){
			String numb_id=array[i];
			message=invitationServices.UpdataInvitation(numb_id,status);
		    }
		}
		
	    }
	}
	}catch(Exception e){
	    message="邀请函"+(status.equals("1")?"启用失败":"无法正常失效");
	}finally{
	    	response.setContentType("text/Xml;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("pragma", "no-cache");
		response.setDateHeader("expires", 0);
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    JSONObject object1 = new JSONObject();
		    object1.put("message", message);
		    out.println(object1);
		    
		} catch (IOException ex1) {
		    ex1.printStackTrace();
		} finally {
		    out.close();
		}
//	    return new String(message.getBytes("utf-8"),"ISO-8859-1");
	    return null;
	}
    }

}
