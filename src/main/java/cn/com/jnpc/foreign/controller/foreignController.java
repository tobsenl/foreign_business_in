package cn.com.jnpc.foreign.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.po.FiInout;
import cn.com.jnpc.foreign.service.ForeignServices;
import cn.com.jnpc.foreign.service.InOutServices;
import cn.com.jnpc.foreign.utils.Untils;
import cn.com.jnpc.foreign.utils.springContextUtil;
import cn.com.jnpc.foreign.vo.PageMybatis;
import cn.com.jnpc.foreign.vo.foreigner;

@Controller
@RequestMapping(value = "/foreign")
public class foreignController {
    private ForeignServices foreignServices;

    @RequestMapping(value = "/foreign_info.html")
    public String foreign(HttpServletRequest request,
	    HttpServletResponse response) {
	return "/foreign/foreign_info";
    }
    @RequestMapping(value = "/foreign_add.html")
    public String addforeign(foreigner foreign,
	    @RequestParam MultipartFile pp_attachment,
	    @RequestParam MultipartFile ee_attachment,
	    HttpServletRequest request, Model model) throws IOException {
	// 获取session中保存的对象
	User user = (User) Untils.getSessionP(request, "user");
	foreignServices = (ForeignServices) springContextUtil
		.getBean("ForeignServices");
	String massage = foreignServices.store(foreign, pp_attachment,
		ee_attachment, user);
	model.addAttribute("return_info", massage);
	return "/foreign/foreign_info";
    }
    @RequestMapping(value = "/foreign_updata.html")
    public String updataforeign(foreigner foreign,HttpServletRequest request,HttpServletResponse response) throws IOException {
	// 获取session中保存的对象
	User user = (User) Untils.getSessionP(request, "user");
	foreignServices = (ForeignServices) springContextUtil
		.getBean("ForeignServices");
	//上传文件处理器
	  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	  //文件对象
	  MultipartFile pp_attachment = (CommonsMultipartFile) multipartRequest.getFile("pp_attachment");
	  MultipartFile ee_attachment = (CommonsMultipartFile) multipartRequest.getFile("ee_attachment");
	  
	String massage = foreignServices.storeUpdata(foreign, pp_attachment,
		ee_attachment, user);
	response.setContentType("text/Xml;charset=utf-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("expires", 0);
	PrintWriter out = null;
	try {
	    out = response.getWriter();
	    out.println(massage);

	} catch (IOException ex1) {
	    ex1.printStackTrace();
	} finally {
	    out.close();
	}
	return null;
    }
    
    @RequestMapping(value = "/AjaxQuery.html")
    public String AjaxQuery(@RequestParam(value = "entryValue") String entryValue,HttpServletRequest request,
	    HttpServletResponse response) {
	List<FiForeigner> foreignlist=null;
	String value=Untils.NotNull(entryValue)?entryValue:"";
	if(Untils.NotNull(value)){
	    foreignServices = (ForeignServices) springContextUtil.getBean("ForeignServices");
	    foreignlist= foreignServices.QueryByName("%"+value+"%");
	}
	List list = new ArrayList();
	if(foreignlist!=null && foreignlist.size()>0){
	    for (FiForeigner forei : foreignlist) {
		JSONObject object = new JSONObject();
		//System.out.println(forei.getName()+" , "+forei.getPassportId());
		if(Untils.NotNull(value)){
		object.put("name",forei.getName());
		object.put("pp_id",forei.getPassportId());
		object.put("id",forei.getId());
		}
		list.add(object);
	    }
	}
	response.setContentType("text/Xml;charset=utf-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("expires", 0);
	PrintWriter out = null;
	try {
	    out = response.getWriter();
	    out.println(list);

	} catch (IOException ex1) {
	    ex1.printStackTrace();
	} finally {
	    out.close();
	}
	return null;
    }
    @RequestMapping(value = "/AjaxQuery_list.html")
    public String AjaxQueryList(HttpServletRequest request,
	    HttpServletResponse response) {
	List<FiForeigner> foreignlist=null;
	//var url="name:"+name+" id:"+id+" contry:"+contry+" numb:"+numb+" post:"+post;
	String foreignname=Untils.NotNull(request.getParameter("name"))?request.getParameter("name"):"";
	String passport_id=Untils.NotNull(request.getParameter("id"))?request.getParameter("id"):"";
	String contry_from=Untils.NotNull(request.getParameter("contry"))?request.getParameter("contry"):"";
	String numb_invitation=Untils.NotNull(request.getParameter("numb"))?request.getParameter("numb"):"";
	String post=Untils.NotNull(request.getParameter("post"))?request.getParameter("post"):"";
	String is_here_=Untils.NotNull(request.getParameter("is_here_"))?request.getParameter("is_here_"):"";
	
	PageMybatis page=new PageMybatis();
	page.setNowpage("1");
	page.setPageurl(Untils.requestPath(request));
	
	foreignServices = (ForeignServices) springContextUtil.getBean("ForeignServices");
	if(Untils.NotNull(numb_invitation) || Untils.NotNull(foreignname) || Untils.NotNull(passport_id) || Untils.NotNull(contry_from) || Untils.NotNull(post) || Untils.NotNull(is_here_)){
	    page.setQuerysql(" t1.* from fi_foreigner t1 where 1=1");
	    foreignlist= foreignServices.QueryandInvitation(foreignname,passport_id,contry_from,numb_invitation,post,is_here_,page);
	}else{
	    page.setQuerysql(" t1.* from fi_foreigner t1 where 1=1");
	    foreignlist= foreignServices.QueryList("All",page);
	}
	List list = new ArrayList();
	if(foreignlist!=null && foreignlist.size()>0){
	    for (FiForeigner forei : foreignlist) {
		JSONObject object = new JSONObject();
		object.put("id",forei.getId());
		object.put("name",forei.getName());
		object.put("sex",forei.getSex());
		object.put("country",forei.getCountry());
		object.put("companydepartment",forei.getCompanyDepartment());
		object.put("passportid",forei.getPassportId());
		object.put("role",forei.getRole());
		object.put("isHere",forei.getIsHere());
		list.add(object);
	    }
	}
	response.setContentType("text/Xml;charset=utf-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("expires", 0);
	PrintWriter out = null;
	try {
	    out = response.getWriter();
	    out.println(list);
	    
	} catch (IOException ex1) {
	    ex1.printStackTrace();
	} finally {
	    out.close();
	}
	return null;
    }
    @RequestMapping(value = "/AjaxQuery_inout.html")
    public String AjaxQueryInOut(HttpServletRequest request,
	    HttpServletResponse response) {
	foreigner foreign=null;
	String id=Untils.NotNull(request.getParameter("foreign_id"))?request.getParameter("foreign_id"):"";
	foreignServices = (ForeignServices) springContextUtil.getBean("ForeignServices");
	List<FiInout> inout=null;
	if(Untils.NotNull(id)){
	    foreign= foreignServices.QueryByid(id);
	    inout=foreignServices.QueryByInOut(id);
	}
	List<JSONObject> list=new ArrayList();
//	List list=new ArrayList();
	if(foreign!=null){
//	    Map object = new HashMap();
	    JSONObject object = new JSONObject();
	    object.put("foreign",foreign);
	    object.put("inout_list",inout);
	    list.add(object);
	}
	response.setContentType("text/Xml;charset=utf-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("expires", 0);
	PrintWriter out = null;
	try {
	    out = response.getWriter();
	    out.println(list);
	    
	} catch (IOException ex1) {
	    ex1.printStackTrace();
	} finally {
	    out.close();
	}
	return null;
    }
    @RequestMapping(value = "/foreign_hereis.html")
    public String Edit_hereis(HttpServletRequest request,
	    HttpServletResponse response) {
	String id=Untils.NotNull(request.getParameter("inhere_id_list"))?request.getParameter("inhere_id_list"):"";
	String status=Untils.NotNull(request.getParameter("is_here_status"))?request.getParameter("is_here_status"):"";
	foreignServices = (ForeignServices) springContextUtil.getBean("ForeignServices");
	String s="";
	JSONObject object = new JSONObject();
	try{
	if(Untils.NotNull(id)){
	    if(Untils.NotNull(status)){
		String [] array=id.split(",");
		for (int i = 0; i < array.length; i++) {
		    if(Untils.NotNull(array[i])){
			FiForeigner foreign_=foreignServices.QueryByid_fi(array[i]);
			foreign_.setIsHere(Integer.parseInt(status));
			foreignServices.UpdataObject(foreign_);
		    }
		}
		
	    }
	}
	
	object.put("message","是否再连信息保存成功！");
	}catch(Exception e){
	    object.put("message","是否再连信息保存失败！");
	}finally{
	    	response.setContentType("text/Xml;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("pragma", "no-cache");
		response.setDateHeader("expires", 0);
		PrintWriter out = null;
        	try {
        	    out = response.getWriter();
        	    out.println(object);
        	    return null;
        	} catch (IOException ex1) {
        	    ex1.printStackTrace();
        	    return null;
        	} finally {
        	    out.close();
        	    return null;
        	}
	}
    }
    @RequestMapping(value = "/AjaxQuery_detail.html")
    public String AjaxQueryDetail(HttpServletRequest request,
	    HttpServletResponse response) {
	foreigner foreign=null;
	String id=Untils.NotNull(request.getParameter("foreign_id"))?request.getParameter("foreign_id"):"";
	foreignServices = (ForeignServices) springContextUtil.getBean("ForeignServices");
	if(Untils.NotNull(id)){
	    foreign= foreignServices.QueryByid(id);
	}
	List list=new ArrayList();
	if(foreign!=null){
	    JSONObject object = new JSONObject();
	    object.put("id",foreign.getId());
	    object.put("name",foreign.getName());
	    object.put("sex",foreign.getSex());
	    object.put("birthday",foreign.getBirthday() );
	    object.put("country",foreign.getCountry());
	    object.put("company_department",foreign.getCompany_department());
	    object.put("passport_id",foreign.getPassport_id());
	    object.put("passport_exp_date",foreign.getPassport_exp_date());
	    object.put("post",foreign.getPost());
	    object.put("role",foreign.getRole());
	    object.put("fk_pp",foreign.getFk_pp_attachment_id());
	    object.put("expert_evidence",foreign.getExpert_evidence());
	    object.put("fk_ee",foreign.getFk_ee_attachment_id());
	    object.put("fk_invitation",foreign.getFk_invitation_id());
	    object.put("fk_permit",foreign.getFk_rp_permit_id());
	    object.put("is_here",foreign.getIs_here());
	    object.put("status",foreign.getStatus());
	    object.put("rp_id",foreign.getResidence_permit_id());
	    object.put("rp_kind",foreign.getResidence_permit_kind());
	    object.put("rp_exp_enddate",foreign.getRp_exp_endDate());
	    object.put("rp_address",foreign.getRp_Address());
	    list.add(object);
	}
	response.setContentType("text/Xml;charset=utf-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("expires", 0);
	PrintWriter out = null;
	try {
	    out = response.getWriter();
	    out.println(list);
	} catch (IOException ex1) {
	    ex1.printStackTrace();
	} finally {
	    out.close();
	}
	return null;
    }
    @RequestMapping(value="/foreign_{kind}.html")
    public String QueryforList(@PathVariable("kind") String kind,HttpServletRequest request,Model model){
	foreignServices = (ForeignServices) springContextUtil
		.getBean("ForeignServices");
	PageMybatis page=new PageMybatis();
	page.setQuerysql(" t1.* from fi_foreigner t1 where 1=1 ");
	page.setNowpage("1");
	List<FiForeigner> list= foreignServices.QueryList("All",page);
	model.addAttribute("foreign_list", list);
	if(kind.equals("edit")){
	    page.setPageurl(Untils.requestPath(request));
	    model.addAttribute("page", page);
	    return "/foreign/foreign_edit";
	}else if(kind.equals("inout")){
	    page.setPageurl(Untils.requestPath(request));
	    model.addAttribute("page", page);
	    return "/foreign/foreign_inout";
	}else if(kind.equals("ishere")){
	    page.setPageurl(Untils.requestPath(request));
	    model.addAttribute("page", page);
	    return "/foreign/foreign_ishere";
	}
	else{
	    return "";
	}
    }
    
}
