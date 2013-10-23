package cn.com.jnpc.foreign.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.po.FiInvitation;
import cn.com.jnpc.foreign.service.ForeignServices;
import cn.com.jnpc.foreign.service.QueryServices;
import cn.com.jnpc.foreign.utils.Untils;
import cn.com.jnpc.foreign.utils.springContextUtil;

@Controller
@RequestMapping(value = "/query")
public class QueryController {
    
    QueryServices queryServices;
    
    @RequestMapping(value="/query_invitation_list.html")
    public String QueryInvitationList(@RequestParam(value = "foreign_id") String foreign_id,HttpServletRequest request,HttpServletResponse response){
	List<FiInvitation> invitationlist=null;
	JSONObject object = new JSONObject();
	if(Untils.NotNull(foreign_id)){
	    queryServices = (QueryServices) springContextUtil
		    .getBean("QueryServices");
	    invitationlist=queryServices.getInvitationByforeign(foreign_id);
	}
	if (invitationlist != null && invitationlist.size() > 0) {
	    object.put("invitationlist", invitationlist);
	}
	response.setContentType("text/Xml;charset=utf-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("expires", 0);
	PrintWriter out = null;
	try {
	    out = response.getWriter();
	    out.println(object);
	} catch (IOException ex1) {
	    ex1.printStackTrace();
	} finally {
	    out.close();
	}
	return null;
    }
    @RequestMapping(value="/query_foreign_list.html")
    public String QueryForeignList(@RequestParam(value = "invitation_id") String invitation_id,HttpServletRequest request,HttpServletResponse response){
	List<FiForeigner> foreignlist=null;
	JSONObject object = new JSONObject();
	if(Untils.NotNull(invitation_id)){
	    queryServices = (QueryServices) springContextUtil
		    .getBean("QueryServices");
	    foreignlist=queryServices.getForeignByinvitation(invitation_id);
	}
	if (foreignlist != null && foreignlist.size() > 0) {
	    object.put("foreignlist", foreignlist);
	}
	response.setContentType("text/Xml;charset=utf-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("expires", 0);
	PrintWriter out = null;
	try {
	    out = response.getWriter();
	    out.println(object);
	} catch (IOException ex1) {
	    ex1.printStackTrace();
	} finally {
	    out.close();
	}
	return null;
    }
}
