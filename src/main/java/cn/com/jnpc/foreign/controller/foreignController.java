package cn.com.jnpc.foreign.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
    public String updataforeign(foreigner foreign, HttpServletRequest request,
	    HttpServletResponse response) throws IOException {
	// 获取session中保存的对象
	User user = (User) Untils.getSessionP(request, "user");
	foreignServices = (ForeignServices) springContextUtil
		.getBean("ForeignServices");
	// 上传文件处理器
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	// 文件对象
	MultipartFile pp_attachment = (CommonsMultipartFile) multipartRequest
		.getFile("pp_attachment");
	MultipartFile ee_attachment = (CommonsMultipartFile) multipartRequest
		.getFile("ee_attachment");

	String message = foreignServices.storeUpdata(foreign, pp_attachment,
		ee_attachment, user);
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
	return null;
    }

    @RequestMapping(value = "/AjaxQuery.html")
    public String AjaxQuery(
	    @RequestParam(value = "entryValue") String entryValue,
	    HttpServletRequest request, HttpServletResponse response) {
	List<FiForeigner> foreignlist = null;
	String value = Untils.NotNull(entryValue) ? entryValue : "";
	if (Untils.NotNull(value)) {
	    foreignServices = (ForeignServices) springContextUtil
		    .getBean("ForeignServices");
	    foreignlist = foreignServices.QueryByName("%" + value + "%");
	}
	List list = new ArrayList();
	if (foreignlist != null && foreignlist.size() > 0) {
	    for (FiForeigner forei : foreignlist) {
		JSONObject object = new JSONObject();
		// System.out.println(forei.getName()+" , "+forei.getPassportId());
		if (Untils.NotNull(value)) {
		    object.put("name", forei.getName());
		    object.put("pp_id", forei.getPassportId());
		    object.put("id", forei.getId());
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

    @RequestMapping(value = "/search_list.html")
    public String QueryList(@RequestParam("kind") String kind ,HttpServletRequest request,
	    HttpServletResponse response, Model model) {
//	@RequestMapping(value = "/AjaxQuery_list.html")
//	public String AjaxQueryList(HttpServletRequest request,
//		HttpServletResponse response) {
	List<FiForeigner> foreignlist = null;
	// var
	// url="name:"+name+" id:"+id+" contry:"+contry+" numb:"+numb+" post:"+post;
	String foreignname = Untils.NotNull(request.getParameter("foreign_name")) ? request
		.getParameter("foreign_name") : "";
	String passport_id = Untils.NotNull(request.getParameter("passport_id_q")) ? request
		.getParameter("passport_id_q") : "";
	String contry_from = Untils.NotNull(request.getParameter("contry_q")) ? request
		.getParameter("contry_q") : "";
	String numb_invitation = Untils.NotNull(request.getParameter("invitation_numb")) ? request
		.getParameter("invitation_numb") : "";
	String post = Untils.NotNull(request.getParameter("post_q")) ? request
		.getParameter("post_q") : "";
	String is_here_ = Untils.NotNull(request.getParameter("is_here_")) ? request
		.getParameter("is_here_") : "";

	String now_page=Untils.NotNull(request.getParameter("nowpage")) ? request
		.getParameter("nowpage") : "";
	String page_size=Untils.NotNull(request.getParameter("pagesize")) ? request
		.getParameter("pagesize") : "";
	String kinds=Untils.NotNull(kind) ? kind : "";
	
	foreignServices = (ForeignServices) springContextUtil
		.getBean("ForeignServices");
	
	String query_sql=foreignServices.getsql(foreignname,passport_id, contry_from, numb_invitation, post, is_here_);
	PageMybatis page = foreignServices.QueryCount(query_sql);
	if(Untils.NotNull(now_page) && now_page != "1"){
	    page.setNowpage(Long.parseLong(now_page));
	}else{
	    page.setNowpage(Long.parseLong("1"));
	}
	if(Untils.NotNull(page_size) && now_page != "1"){
	    page.setPagesize(Long.parseLong(page_size));
	}
	
	if(Untils.NotNull(query_sql)){
	    page.setQuerysql(query_sql);
	}
	foreignlist = foreignServices.QueryList(page);
	
	model.addAttribute("foreign_list", foreignlist);
	page.setPageurl(Untils.requestPath(request));
	model.addAttribute("page", page);
	
	if (kinds.equals("edit")) {
	    return "/foreign/foreign_edit";
	} else if (kinds.equals("inout")) {
	    return "/foreign/foreign_inout";
	} else if (kinds.equals("ishere")) {
	    return "/foreign/foreign_ishere";
	} else if (kinds.equals("extension")) {
	    return "/foreign/foreign_extension";
	} else if (kinds.equals("query")) {
	    return "/query/queryforeign";
	} else if (kinds.equals("foreigninoutquery")) {
	    return "/query/queryforeigninout";
	} else {
	    return "";
	}
    }

    @RequestMapping(value = "/AjaxQuery_inout.html")
    public String AjaxQueryInOut(HttpServletRequest request,
	    HttpServletResponse response) {
	foreigner foreign = null;
	String id = Untils.NotNull(request.getParameter("foreign_id")) ? request
		.getParameter("foreign_id") : "";
	foreignServices = (ForeignServices) springContextUtil
		.getBean("ForeignServices");
	List<FiInout> inout = null;
	if (Untils.NotNull(id)) {
	    foreign = foreignServices.QueryByid(request,id);
	    inout = foreignServices.QueryByInOut(id);
	}
	List<JSONObject> list = new ArrayList();
	// List list=new ArrayList();
	if (foreign != null) {
	    // Map object = new HashMap();
	    JSONObject object = new JSONObject();
	    object.put("foreign", foreign);
	    object.put("inout_list", inout);
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
	String id = Untils.NotNull(request.getParameter("inhere_id_list")) ? request
		.getParameter("inhere_id_list") : "";
	String status = Untils.NotNull(request.getParameter("is_here_status")) ? request
		.getParameter("is_here_status") : "";
	foreignServices = (ForeignServices) springContextUtil
		.getBean("ForeignServices");
	String s = "";
	JSONObject object = new JSONObject();
	String message = "";
	try {
	    if (Untils.NotNull(id)) {
		if (Untils.NotNull(status)) {
		    String[] array = id.split(",");
		    for (int i = 0; i < array.length; i++) {
			if (Untils.NotNull(array[i])) {
			    FiForeigner foreign_ = foreignServices
				    .QueryByid_fi(array[i]);
			    foreign_.setIsHere(Integer.parseInt(status));
			    foreignServices.UpdataObject(foreign_);
			}
		    }

		}
	    }
	    message = "是否再连信息保存成功！";
	} catch (Exception e) {
	    message = "是否再连信息保存失败！";
	} finally {
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
	    // return new String(message.getBytes("utf-8"),"ISO-8859-1");
	    return null;
	}
    }

    @RequestMapping(value = "/AjaxQuery_detail.html")
    public String AjaxQueryDetail(HttpServletRequest request,
	    HttpServletResponse response) {
	foreigner foreign = null;
	String id = Untils.NotNull(request.getParameter("foreign_id")) ? request
		.getParameter("foreign_id") : "";
	foreignServices = (ForeignServices) springContextUtil
		.getBean("ForeignServices");
	if (Untils.NotNull(id)) {
	    foreign = foreignServices.QueryByid(request,id);
	}
	List list = new ArrayList();
	if (foreign != null) {
	    JSONObject object = new JSONObject();
	    object.put("id", foreign.getId());
	    object.put("name", foreign.getName());
	    object.put("sex", foreign.getSex());
	    object.put("birthday", foreign.getBirthday());
	    object.put("country", foreign.getCountry());
	    object.put("company_department", foreign.getCompany_department());
	    object.put("passport_id", foreign.getPassport_id());
	    object.put("passport_exp_date", foreign.getPassport_exp_date());
	    object.put("post", foreign.getPost());
	    object.put("role", foreign.getRole());
	    object.put("fk_pp", foreign.getFk_pp_attachment_id());
	    object.put("fk_pp_url", foreign.getFk_pp_url());
	    object.put("expert_evidence", foreign.getExpert_evidence());
	    object.put("fk_ee", foreign.getFk_ee_attachment_id());
	    object.put("fk_ee_url", foreign.getFk_ee_url());
	    object.put("fk_invitation", foreign.getFk_invitation_id());
	    object.put("fk_permit", foreign.getFk_rp_permit_id());
	    object.put("is_here", foreign.getIs_here());
	    object.put("status", foreign.getStatus());
	    object.put("rp_id", foreign.getResidence_permit_id());
	    object.put("rp_kind", foreign.getResidence_permit_kind());
	    object.put("rp_exp_enddate", foreign.getRp_exp_endDate());
	    object.put("rp_address", foreign.getRp_Address());
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

    @RequestMapping(value = "/foreign_{kind}.html")
    public String QueryforList(@PathVariable("kind") String kind,
	    HttpServletRequest request, Model model) {
	foreignServices = (ForeignServices) springContextUtil
		.getBean("ForeignServices");
	PageMybatis page = foreignServices.QueryCount("");
	page.setNowpage(Long.parseLong("1"));
	List<FiForeigner> list = foreignServices.QueryList(page);
	page.setPageurl(Untils.requestPath(request)+"kind="+kind);
	model.addAttribute("foreign_list", list);
	model.addAttribute("page", page);
	if (kind.equals("edit")) {
	    return "/foreign/foreign_edit";
	} else if (kind.equals("inout")) {
	    return "/foreign/foreign_inout";
	} else if (kind.equals("ishere")) {
	    return "/foreign/foreign_ishere";
	} else if (kind.equals("extension")) {
	    return "/foreign/foreign_extension";
	} else if (kind.equals("query")) {
	    return "/query/queryforeign";
	} else if (kind.equals("foreigninoutquery")) {
	    return "/query/queryforeigninout";
	} else {
	    return "";
	}
    }

}
