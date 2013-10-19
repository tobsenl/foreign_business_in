package cn.com.jnpc.foreign.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.dao.invitationDao;
import cn.com.jnpc.foreign.model.FiInvitationExample;
import cn.com.jnpc.foreign.po.FiAttachment;
import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.po.FiInvitation;
import cn.com.jnpc.foreign.po.FiMiddle;
import cn.com.jnpc.foreign.utils.DateUtil;
import cn.com.jnpc.foreign.utils.JnpcException;
import cn.com.jnpc.foreign.utils.Untils;
import cn.com.jnpc.foreign.vo.PageMybatis;

public class InvitationServices {
    private static Logger log = Logger.getLogger(InvitationServices.class);

    ForeignServices foreignservice;

    @Resource(name = "ForeignServices")
    public void setForeignservice(ForeignServices foreignservice) {
	this.foreignservice = foreignservice;
    }

    MiddleServices middleservice;

    @Resource(name = "MiddleServices")
    private void setMiddleservice(MiddleServices middleservice) {
	this.middleservice = middleservice;
    }

    AttachmentServices attchServices;

    @Resource(name = "AttachmentServices")
    private void setAttchServices(AttachmentServices attchServices) {
	this.attchServices = attchServices;
    }

    FiInvitation fiinvitation;

    invitationDao invitationDao;

    @Resource(name = "invitationDao")
    private void setInvitationDao(invitationDao invitationDao) {
	this.invitationDao = invitationDao;
    }

    public String store(HttpServletRequest request, MultipartFile attachment,
	    User user) {
	// 获取行数max
	String r_numb = request.getParameter("numb");
	int numb = Untils.NotNull(r_numb) ? Integer.parseInt(r_numb) : 0;
	String invitationId = Untils.NotNull(request
		.getParameter("invitation_id")) ? request
		.getParameter("invitation_id") : "";
	Date leavingDate = Untils.NotNull(request.getParameter("leaving_date"))
		&& !"0".equals(request.getParameter("leaving_date")) ? DateUtil
		.stringToDate(request.getParameter("leaving_date"),
			"yyyy-MM-dd") : null;
	Date arrivedDate = Untils.NotNull(request.getParameter("arrived_date"))
		&& !"0".equals(request.getParameter("arrived_date")) ? DateUtil
		.stringToDate(request.getParameter("arrived_date"),
			"yyyy-MM-dd") : null;
	String stayTime = Untils.NotNull(request.getParameter("stay_time")) ? request
		.getParameter("stay_time") : "";
	String gobackTimes = Untils.NotNull(request
		.getParameter("goback_times")) ? request
		.getParameter("goback_times") : "";
	try {
	    List<FiMiddle> list_m_id = new ArrayList<FiMiddle>();
	    // 进行循环将删除行的error数据剔除
	    for (int i = 1; i <= numb; i++) {
		String r_foreign_id = request.getParameter("foreign_id" + i);
		String foreign_id = Untils.NotNull(r_foreign_id) ? r_foreign_id
			: "";
		if (Untils.NotNull(foreign_id)) {
		    // 生成middle数据不含 invitationID
		    FiMiddle middle_id = middleservice.InsertReturObject(
			    foreign_id, user);
		    list_m_id.add(middle_id);
		} else {
		    continue;
		}
	    }

	    // 保存附件
	    FiAttachment attch = attchServices.InsertReturObject(attachment,
		    user, 0);

	    // 为invitation 填充数据
	    fiinvitation=new FiInvitation();
	    fiinvitation.setCreateDate(new Date());
	    if (user != null) {
		fiinvitation.setCreateUser(user.getAccount());
	    }
	    fiinvitation.setStatus(0);

	    fiinvitation.setGobackTimes(gobackTimes);
	    fiinvitation.setStayTime(stayTime);
	    fiinvitation.setArrivedDate(arrivedDate);
	    fiinvitation.setLeavingDate(leavingDate);
	    fiinvitation.setInvitationId(invitationId);
	    fiinvitation.setIsUse(0);
	    // 为 invitation 填充 附件ID
	    fiinvitation.setFkAttachmentId(attch.getId() + "");

	    // 保存invitation
	    String fiinvitation_id = invitationDao.InsertReturId("insert",
		    fiinvitation);
	    if (fiinvitation != null && Untils.NotNull(fiinvitation_id)) {
		// 为 附件补充相关invitation信息
		if (attch != null) {
		    attch.setCardId(fiinvitation.getInvitationId());
		    attch.setEndTime(fiinvitation.getArrivedDate());
		    attch.setStartTime(fiinvitation.getLeavingDate());
		    attch.setKfParentId(fiinvitation_id);
		    attchServices.UpdataObject(attch);
		}
		// 循环为 middle 补充invitation信息
		for (int j = 0; j < list_m_id.size(); j++) {
		    FiMiddle middle = list_m_id.get(j);
		    middle.setFkInvitationId(fiinvitation_id);
		    FiForeigner foreign=foreignservice.QueryByid_fi(middle.getFkPersonId());
//		    foreign.setFkInvitationId(fiinvitation_id);
//		    foreignservice.UpdataObject(foreign);
		    //此处由需求部门特别提出。目的是为了方便查看以往的邀请函对应的护照号码.
		    middle.setFkPersonPpid(foreign.getPassportId());
		    middleservice.UpdataObject(middle);
		}
	    } else {
		throw new JnpcException("invitation添加异常！");
	    }
	    return "成功创建!";
	} catch (Exception e) {
	    log.info(e.getMessage());
	    log.info(e.toString());
	    log.info(e.getCause());
	    return "新建失败!";
	}
    }
    
    public String storeUpdata(FiInvitation new_invitation,HttpServletRequest request, MultipartFile attachment,
	    User user) {
	// 获取行数max
	String r_numb = request.getParameter("numb");
	int numb = Untils.NotNull(r_numb) ? Integer.parseInt(r_numb) : 0;
	//首先进行查询.
	
	try {
	    List<FiMiddle> list_m_id = new ArrayList<FiMiddle>();
	    // 进行循环将删除行的error数据剔除
	    List<FiMiddle> list_old_m = middleservice.QueryByInvitation(new_invitation);
	    
	    for (int i = 1; i <= numb; i++) {
		String r_foreign_id = request.getParameter("foreign_id" + i);
		String foreign_id = Untils.NotNull(r_foreign_id) ? r_foreign_id
			: "";
		if (Untils.NotNull(foreign_id)) {
		    // 生成middle数据不含 invitationID
		    int x=0;
		    FiMiddle tempmiddle=null;
		    FiMiddle middle_id =null;
		    for (FiMiddle fiMiddle : list_old_m) {
			String person_id=fiMiddle.getFkPersonId();
			if(person_id.equals(foreign_id)){
			    x=x+1;
			    tempmiddle=fiMiddle;
			}
		    }
		    if(x == 0){
		    middle_id = middleservice.InsertReturObject(
			    foreign_id, user);
		    }else{
			if(tempmiddle!=null){
			    tempmiddle.setFkPersonId(foreign_id);
			    middle_id = middleservice.UpdataObject(tempmiddle);
			}
		    }
		    if(middle_id != null){
			list_m_id.add(middle_id);
		    }
		} else {
		    continue;
		}
	    }
	    for (FiMiddle fiMiddle : list_old_m) {
		String old_id=fiMiddle.getFkPersonId();
		int x=0;
		FiMiddle tempmiddle=null;
		for (FiMiddle fiMiddle2 : list_m_id) {
		    String new_id=fiMiddle2.getFkPersonId();
		    if(!old_id.equals(new_id)){
			x=x+1;
		    }
		}
		if(x > 0){
		    middleservice.deleteByPrimaryKey(old_id);
		}
	    }
	    for (int j = 0; j < list_m_id.size(); j++) {
		    FiMiddle middle = list_m_id.get(j);
		    middle.setFkInvitationId(new_invitation.getId()+"");
		    FiForeigner foreign=foreignservice.QueryByid_fi(middle.getFkPersonId());
//		    foreign.setFkInvitationId(fiinvitation_id);
//		    foreignservice.UpdataObject(foreign);
		    middle.setFkPersonPpid(foreign.getPassportId());
		    middleservice.UpdataObject(middle);
		}
	    FiAttachment attch=null;
	    // 保存附件
	    if(Untils.NotNull(new_invitation.getFkAttachmentId()) || attachment.getSize()>0){
		if(Untils.NotNull(new_invitation.getFkAttachmentId())){
		    attch = attchServices.UpdataReturnObject(attachment,
		    user, "0");
		}else{
		    attch = attchServices.InsertReturObject(attachment,
			    user, 0);
		}
	    	if (attch != null) {
		    attch.setCardId(fiinvitation.getInvitationId());
		    attch.setEndTime(fiinvitation.getArrivedDate());
		    attch.setStartTime(fiinvitation.getLeavingDate());
		    attch.setKfParentId(new_invitation.getId()+"");
		    attchServices.UpdataObject(attch);
		}
	    }
	    // 为invitation 填充数据
	    fiinvitation=invitationDao.SelectById("selectByPrimaryKey",new_invitation.getId());
	    fiinvitation.setEditDate(new Date());
	    if (user != null) {
		fiinvitation.setEditUser(user.getAccount());
	    }
	    fiinvitation.setGobackTimes(new_invitation.getGobackTimes());
	    fiinvitation.setStayTime(new_invitation.getStayTime());
	    fiinvitation.setArrivedDate(new_invitation.getArrivedDate());
	    fiinvitation.setLeavingDate(new_invitation.getLeavingDate());
	    fiinvitation.setInvitationId(new_invitation.getInvitationId());
	    // 为 invitation 填充 附件ID
	    if(attch!=null){
		fiinvitation.setFkAttachmentId(attch.getId() + "");
	    }

	    // 保存invitation
	    invitationDao.UptataReturnObj("updateByPrimaryKey",
		    fiinvitation);
	    return "成功修改!";
	} catch (Exception e) {
	    log.info(e.getMessage());
	    log.info(e.toString());
	    log.info(e.getCause());
	    return "修改失败!";
	}
    }
    

    public List<FiInvitation> QueryByNumb(String id) {
	List<String> list = new ArrayList<String>();
	String[] a = id.split(",");
	for (int i = 0; i < a.length; i++) {
	    if(Untils.NotNull(a[i])){
		list.add(a[i]);
	    }
	}
	FiInvitationExample example = new FiInvitationExample();
	example.createCriteria().andInvitationIdIn(list);
	List<FiInvitation> invitation = invitationDao.SelectByExample(
		"selectByExample", example);
	return invitation;
    }
    public FiInvitation QueryById(String id) {
	FiInvitationExample example = new FiInvitationExample();
	example.createCriteria().andIdEqualTo(Integer.parseInt(id));
	List<FiInvitation> invitation = invitationDao.SelectByExample(
		"selectByExample", example);
	return invitation.get(0);
    }
    public List QueryLinkForeign(List<FiInvitation> invitation_id) {
	List<FiMiddle> middle_list=middleservice.QueryByInvitation(invitation_id);
	List list=null;
	if(Untils.NotNull(middle_list) && middle_list.size()>0){
	    list=new ArrayList();
	    for(int i=0;i<middle_list.size();i++){
		FiMiddle middle=middle_list.get(i);
		Map map=new HashMap();
		map.put("id", middle.getFkPersonId());
		//此处由需求部门特别提出。目的是为了方便查看以往的邀请函对应的护照号码.
		map.put("passportId", middle.getFkPersonPpid());
		FiForeigner foreign=foreignservice.QueryByid_fi(middle.getFkPersonId());
		map.put("name", foreign.getName());
		list.add(map);
	    }
	}
	return list;
    }
    public List<FiInvitation> Queryandforeign(String foreign_id_q,String invitation_id_q,String is_use_q,String indate_q,PageMybatis page) {
	StringBuffer buffer=new StringBuffer();
	buffer.append(page.getQuerysql());
	if(Untils.NotNull(invitation_id_q)){
	    buffer.append(" and t1.INVITATION_ID = '");
	    buffer.append(invitation_id_q+"'");
	}if(Untils.NotNull(is_use_q)){
	    buffer.append(" and t1.IS_USE = '");
	    buffer.append(is_use_q+"'");
	}if(Untils.NotNull(indate_q)){
	    buffer.append(" and t1.ARRIVED_DATE = to_date('");
	    buffer.append(indate_q);
	    buffer.append("','yyyy-mm-dd hh24:mi:ss')");
	}
	if(Untils.NotNull(foreign_id_q)){
	    //通过fimiddle的fk_foreign_id in ()
	    List<FiMiddle> middle_list=middleservice.QueryByForeign(foreign_id_q);
	    if(middle_list!=null && middle_list.size()>0){
		buffer.append(" and (t1.id in (");
		int j=0;
		for(int i=0;i<middle_list.size();i++){
        		FiMiddle middle=middle_list.get(i);
        		if(Untils.NotNull(middle.getFkInvitationId())){
                		if(j==0){
                		    j++;
                		    buffer.append(middle.getFkInvitationId());
                		}else{
                		    buffer.append(","+middle.getFkInvitationId());
                		}
        		}
    	    	}
		buffer.append(" )) ");
	    }
	    //将获取的fimiddle集合 循环得到 invitation_id
	    //然后拼接sql
	}
	page.setQuerysql(buffer.toString());
	return invitationDao.SelectAll("selectByPage", page);
    }
    public List<FiInvitation> QueryList(String method,PageMybatis page) {
	return invitationDao.SelectAll("selectByPage",page);
	
    }
}
