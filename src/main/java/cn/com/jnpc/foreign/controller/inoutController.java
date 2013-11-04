package cn.com.jnpc.foreign.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.AbstractDocument.Content;

import net.sf.json.JSONObject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.po.FiInout;
import cn.com.jnpc.foreign.po.FiResidencePermit;
import cn.com.jnpc.foreign.service.InOutServices;
import cn.com.jnpc.foreign.service.ResidencePermitServices;
import cn.com.jnpc.foreign.utils.DateUtil;
import cn.com.jnpc.foreign.utils.Untils;
import cn.com.jnpc.foreign.utils.springContextUtil;

@Controller
@RequestMapping(value = "/inout")
public class inoutController {
    private InOutServices inoutServices;
    private ResidencePermitServices residencePermitServices;

    @InitBinder
    protected void init(HttpServletRequest request,
	    ServletRequestDataBinder binder) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	dateFormat.setLenient(false);
	binder.registerCustomEditor(Date.class, new CustomDateEditor(
		dateFormat, false));
    }

    @RequestMapping(value = "/inout_insert.html")
    public String inout_store(HttpServletRequest request,
	    HttpServletResponse response) throws UnsupportedEncodingException {
	inoutServices = (InOutServices) springContextUtil
		.getBean("InOutServices");
	FiInout inout = new FiInout();
	String value = Untils.NotNull(request.getParameter("content")) ? request
		.getParameter("content") : "";
	String type = Untils.NotNull(request.getParameter("type")) ? request
		.getParameter("type") : "";
	if (Untils.NotNull(value)) {
	    inout.setContent(value);
	}
	if (Untils.NotNull(type)) {
	    inout.setType(Integer.parseInt(type));
	}
	String message = "";
	User user = (User) Untils.getSessionP(request, "user");
	List id_list = null;
	String id = Untils.NotNull(request.getParameter("inout_pp_id")) ? request
		.getParameter("inout_pp_id") : "";
	String begintime = Untils.NotNull(request.getParameter("begintime")) ? request
		.getParameter("begintime") : "";
	if (Untils.NotNull(begintime)) {
	    inout.setBeginTime(DateUtil.stringToDate(begintime, "yyyy-MM-dd"));
	}
	if (Untils.NotNull(id)) {
	    id_list = new ArrayList();
	    String[] li = id.split(",");
	    for (String val : li) {
		// 不为0是因为页面参数勾选的时候默认的全选value为0
		if (Untils.NotNull(val) && val != "0") {
		    id_list.add(val);
		}
	    }
	    message = inoutServices.store(inout, id_list, user);
	} else {
	    message = "保存失败！请确认填写的数据正常后再尝试提交！";
	}
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

    @RequestMapping(value = "/permit_insert.html")
    public String permit_store(
	    @ModelAttribute(value = "extensionform") FiResidencePermit permit,
	    HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {
	residencePermitServices = (ResidencePermitServices) springContextUtil
		.getBean("ResidencePermitServices");
	// String value= inout.getContent();
	String message = "";
	User user = (User) Untils.getSessionP(request, "user");
	List id_list = null;
	// foerign_id
	String id = Untils.NotNull(request.getParameter("extension_id")) ? request
		.getParameter("extension_id") : "";
	if (Untils.NotNull(id)) {
	    id_list = new ArrayList();
	    String[] li = id.split(",");
	    for (String val : li) {
		// 不为0是因为页面参数勾选的时候默认的全选value为0
		if (Untils.NotNull(val) && val != "0") {
		    id_list.add(val);
		}
	    }
	    message = residencePermitServices.store(permit, id_list, user);
	} else {
	    message = "保存失败！请确认填写的数据正常后再尝试提交！";
	}
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
}
