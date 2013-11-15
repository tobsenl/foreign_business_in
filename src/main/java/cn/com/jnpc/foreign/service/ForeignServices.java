package cn.com.jnpc.foreign.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.dao.foreignDao;
import cn.com.jnpc.foreign.model.FiForeignerExample;
import cn.com.jnpc.foreign.model.FiForeignerExample.Criteria;
import cn.com.jnpc.foreign.model.FiInvitationExample;
import cn.com.jnpc.foreign.po.FiAttachment;
import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.po.FiInout;
import cn.com.jnpc.foreign.po.FiInvitation;
import cn.com.jnpc.foreign.po.FiMiddle;
import cn.com.jnpc.foreign.po.FiResidencePermit;
import cn.com.jnpc.foreign.utils.DateUtil;
import cn.com.jnpc.foreign.utils.JnpcException;
import cn.com.jnpc.foreign.utils.Untils;
import cn.com.jnpc.foreign.vo.PageMybatis;
import cn.com.jnpc.foreign.vo.foreigner;

@Transactional
@Service("ForeignServices")
public class ForeignServices {
    private static Logger log = Logger.getLogger(ForeignServices.class);

    InOutServices inoutservices;

    @Resource(name = "InOutServices")
    public void setInoutservices(InOutServices inoutservices) {
	this.inoutservices = inoutservices;
    }

    ResidencePermitServices permitservices;

    @Resource(name = "ResidencePermitServices")
    private void setPermitservices(ResidencePermitServices permitservices) {
	this.permitservices = permitservices;
    }

    MiddleServices middleservice;

    @Resource(name = "MiddleServices")
    public void setMiddleservice(MiddleServices middleservice) {
	this.middleservice = middleservice;
    }

    FiForeigner foreigner;

    InvitationServices invitationServices;

    @Resource(name = "InvitationServices")
    public void setIinvitationServices(InvitationServices invitationServices) {
	this.invitationServices = invitationServices;
    }

    foreignDao foreignDao;

    @Resource(name = "foreignDao")
    private void setForeignDao(foreignDao foreignDao) {
	this.foreignDao = foreignDao;
    }

    AttachmentServices sattachment;

    @Resource(name = "AttachmentServices")
    private void setSattachment(AttachmentServices sattachment) {
	this.sattachment = sattachment;
    }

    //
    FiForeignerExample foreignerexample;
    //
    // @Resource(name = "FiForeignerExample")
    // private void setForeignerexample(FiForeignerExample foreignerexample) {
    // this.foreignerexample = foreignerexample;
    // }

    FiAttachment pp_attachmentObject;
    FiAttachment ee_attachmentObject;
    FiResidencePermit permitObject;

    /**
     * 
     * @param foreign
     * @param pp_attachment
     * @param ee_attachment
     * @param user
     * @return
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public String store(foreigner foreign, MultipartFile pp_attachment,
	    MultipartFile ee_attachment, User user) {
	/**
	 * 存储顺序..文件->attachment->foreign 1 是PP 护照 2 是ee 专家证 3是RP 签证 0是邀请函
	 */
	Date passport_exp_date = null;
	Date birthday = null;
	Date rp_enddate = null;
	FiResidencePermit permit = null;
	pp_attachmentObject = null;
	ee_attachmentObject = null;
	permitObject = null;
	String ee_number = Untils.NotNull(foreign.getExpert_evidence()) ? foreign
		.getExpert_evidence() : "";
	try {

	    passport_exp_date = Untils.NotNull(foreign.getPassport_exp_date())
		    && !"0".equals(foreign.getPassport_exp_date()) ? DateUtil
		    .stringToDate(foreign.getPassport_exp_date(), "yyyy-MM-dd")
		    : null;
	    birthday = Untils.NotNull(foreign.getBirthday())
		    && !"0".equals(foreign.getBirthday()) ? DateUtil
		    .stringToDate(foreign.getBirthday(), "yyyy-MM-dd") : null;
	    rp_enddate = Untils.NotNull(foreign.getRp_exp_endDate())
		    && !"0".equals(foreign.getRp_exp_endDate()) ? DateUtil
		    .stringToDate(foreign.getRp_exp_endDate(), "yyyy-MM-dd")
		    : null;
	    foreigner = new FiForeigner();
	    // 存储附件PP
	    if (pp_attachment.getSize() > 0) {
		pp_attachmentObject = sattachment.InsertReturObject(
			pp_attachment, user, 1);
	    }
	    // 如果选择了专家证 则上传，同时保存勾选状态
	    if (Untils.NotNull(ee_number) && "1".equals(ee_number)) {
		if (ee_attachment.getSize() > 0) {
		    ee_attachmentObject = sattachment.InsertReturObject(
			    ee_attachment, user, 2);
		}
		foreigner.setExpertEvidence(Integer.parseInt(ee_number));
	    }
	    // 存储附件居留许可
	    if (Untils.NotNull(foreign.getResidence_permit_kind())
		    && !"0".equals(foreign.getResidence_permit_kind())) {// 不为0表示存在居留许可
		// permit.setResidencePermitId(foreign.getResidence_permit_id());
		permit = new FiResidencePermit();
		permit.setResidencePermitKind(foreign
			.getResidence_permit_kind());
		permit.setRpAddress(foreign.getRp_Address());
		permit.setRpExpEnddate(rp_enddate);
		permit.setStatus(0);
		permit.setIsDefer(0);
		permitObject = permitservices.InsertReturObject(permit, user);
	    }
	    if (permitObject != null
		    && Untils.NotNull(permitObject.getId() + "")) {
		foreigner.setFkRpPermitId(permitObject.getId() + "");
	    }
	    // 构建foreign
	    if (Untils.NotNull(birthday)) {
		foreigner.setBirthday(birthday);
	    }
	    if (Untils.NotNull(foreign.getSex())) {
		foreigner.setSex(Integer.parseInt(foreign.getSex()));
	    }
	    // 必填部分
	    foreigner.setCompanyDepartment(foreign.getCompany_department());
	    foreigner.setCountry(foreign.getCountry());
	    foreigner.setCreateDate(new Date());
	    if (user != null && Untils.NotNull(user.getAccount())) {
		foreigner.setCreateUser(user.getAccount());
	    }
	    foreigner.setName(foreign.getName());
	    foreigner.setSex(Integer.parseInt(foreign.getSex()));
	    foreigner.setStatus(0);
	    foreigner.setIsHere(0);
	    // 选填部分
	    if (Untils.NotNull(foreign.getPost())) {
		foreigner.setPost(foreign.getPost());
	    }
	    if (Untils.NotNull(foreign.getRole())) {
		foreigner.setRole(foreign.getRole());
	    }
	    if (pp_attachmentObject != null
		    && Untils.NotNull(pp_attachmentObject.getId() + "")) {
		if (Untils.NotNull(foreign.getPassport_id())) {
		    pp_attachmentObject.setCardId(foreign.getPassport_id());// 护照ID
		    foreigner.setPassportId(foreign.getPassport_id());
		}
		if (Untils.NotNull(passport_exp_date)) {
		    pp_attachmentObject.setEndTime(passport_exp_date);// 护照过期时间
		    foreigner.setPassportExpDate(passport_exp_date);
		}
	    }
	    if (pp_attachmentObject != null
		    && Untils.NotNull(pp_attachmentObject.getId() + "")) {
		foreigner.setFkPpAttachmentId(pp_attachmentObject.getId() + "");
	    } else {
		throw new JnpcException("pp add is error!");
	    }
	    if (ee_attachmentObject != null
		    && Untils.NotNull(ee_attachmentObject.getId() + "")) {
		foreigner.setFkEeAttachmentId(ee_attachmentObject.getId() + "");
	    }
	    foreignDao.InsertReturObject("insert", foreigner);
	    if (!Untils.NotNull(foreigner.getId() + "")) {
		throw new JnpcException("foreigner add is error!");
	    }
	    // 补充PP的attachment表的相关资料
	    if (pp_attachmentObject != null
		    && Untils.NotNull(pp_attachmentObject.getId() + "")) {
		if (Untils.NotNull(foreign.getPassport_id())
			|| Untils.NotNull(passport_exp_date)) {
		    sattachment.UpdataObject(pp_attachmentObject);
		}
	    }
	    return "成功创建!";
	} catch (Exception e) {
	    log.info(e.getMessage());
	    log.info(e.toString());
	    log.info(e.getCause());
	    return "新建失败!";
	}
    }

    /**
     * 
     * @param foreign
     * @param pp_attachment
     * @param ee_attachment
     * @param user
     * @return
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public String storeUpdata(foreigner foreign, MultipartFile pp_attachment,
	    MultipartFile ee_attachment, User user) {
	/**
	 * 存储顺序..文件->attachment->foreign 1 是PP 护照 2 是ee 专家证 3是RP 签证 0是邀请函
	 */
	Date passport_exp_date = null;
	Date birthday = null;
	Date rp_enddate = null;
	FiResidencePermit permit = null;
	pp_attachmentObject = null;
	ee_attachmentObject = null;
	permitObject = null;
	String ee_number = Untils.NotNull(foreign.getExpert_evidence()) ? foreign
		.getExpert_evidence() : "";
	try {
	    if (Untils.NotNull(foreign.getId())) {
		foreigner = foreignDao.SelectById("selectByPrimaryKey",
			Integer.parseInt(foreign.getId()));
	    } else {
		foreigner = new FiForeigner();
	    }
	    passport_exp_date = Untils.NotNull(foreign.getPassport_exp_date())
		    && !"0".equals(foreign.getPassport_exp_date()) ? DateUtil
		    .stringToDate(foreign.getPassport_exp_date(), "yyyy-MM-dd")
		    : null;
	    birthday = Untils.NotNull(foreign.getBirthday())
		    && !"0".equals(foreign.getBirthday()) ? DateUtil
		    .stringToDate(foreign.getBirthday(), "yyyy-MM-dd") : null;
	    rp_enddate = Untils.NotNull(foreign.getRp_exp_endDate())
		    && !"0".equals(foreign.getRp_exp_endDate()) ? DateUtil
		    .stringToDate(foreign.getRp_exp_endDate(), "yyyy-MM-dd")
		    : null;

	    // 存储附件PP
	    if (pp_attachment.getSize() > 0) {
		if (Untils.NotNull(foreign.getFk_pp_attachment_id())) {
		    pp_attachmentObject = sattachment.UpdataReturnObject(
			    pp_attachment, user,
			    foreign.getFk_pp_attachment_id(), 1);
		} else {
		    pp_attachmentObject = sattachment.InsertReturObject(
			    pp_attachment, user, 1);
		}
		if (pp_attachmentObject != null
			&& Untils.NotNull(pp_attachmentObject.getId() + "")) {
		    foreigner.setFkPpAttachmentId(pp_attachmentObject.getId()
			    + "");
		} else {
		    new JnpcException("pp_attachment store error!");
		}
	    }
	    // 如果选择了专家证 则上传，同时保存勾选状态
	    if (Untils.NotNull(ee_number) && "1".equals(ee_number)) {
		if (ee_attachment.getSize() > 0) {
		    if (Untils.NotNull(foreign.getFk_ee_attachment_id())) {
			ee_attachmentObject = sattachment.UpdataReturnObject(
				ee_attachment, user,
				foreign.getFk_ee_attachment_id(), 2);

		    } else {
			ee_attachmentObject = sattachment.InsertReturObject(
				ee_attachment, user, 2);
		    }
		    if (ee_attachmentObject != null
			    && Untils.NotNull(ee_attachmentObject.getId() + "")) {
			foreigner.setFkEeAttachmentId(ee_attachmentObject
				.getId() + "");
		    } else {
			new JnpcException("ee_attachment store error!");
		    }
		}
		foreigner.setExpertEvidence(Integer.parseInt(ee_number));
	    } else if (Untils.NotNull(ee_number) && "0".equals(ee_number)) {
		if (Untils.NotNull(foreign.getFk_ee_attachment_id())) {
		    sattachment.DeleteByID(foreign.getFk_ee_attachment_id());
		    foreigner.setFkEeAttachmentId("");
		    foreigner.setExpertEvidence(Integer.parseInt(ee_number));
		}
	    }
	    // 存储附件居留许可
	    if (Untils.NotNull(foreign.getResidence_permit_kind())
		    && !"0".equals(foreign.getResidence_permit_kind())) {// 不为0表示存在居留许可
		// permit.setid(foreign.getFk_rp_permit_id());
		if (Untils.NotNull(foreign.getFk_rp_permit_id())) {
		    permit = permitservices.QueryById(foreign
			    .getFk_rp_permit_id());
		} else {
		    permit = new FiResidencePermit();
		}
		permit.setResidencePermitKind(foreign
			.getResidence_permit_kind());
		permit.setRpAddress(foreign.getRp_Address());
		permit.setRpExpEnddate(rp_enddate);
		permit.setStatus(0);
		permit.setIsDefer(0);
		if (Untils.NotNull(foreign.getFk_rp_permit_id())) {
		    permitObject = permitservices.UpdataReturnObject(permit,
			    user);
		} else {
		    permitObject = permitservices.InsertReturObject(permit,
			    user);
		}
		if (permitObject != null
			&& Untils.NotNull(permitObject.getId() + "")) {
		    foreigner.setFkRpPermitId(permitObject.getId() + "");
		} else {
		    throw new JnpcException("permitObject store error!");
		}
	    } else if (Untils.NotNull(foreign.getResidence_permit_kind())
		    && "0".equals(foreign.getResidence_permit_kind())) {
		if (Untils.NotNull(foreign.getFk_rp_permit_id())) {
		    permitservices.DeleteByID(foreign.getFk_rp_permit_id());
		    foreigner.setFkRpPermitId("");
		}
	    }
	    // 构建foreign
	    if (Untils.NotNull(birthday)) {
		foreigner.setBirthday(birthday);
	    }
	    if (Untils.NotNull(foreign.getSex())) {
		foreigner.setSex(Integer.parseInt(foreign.getSex()));
	    }
	    // 必填部分
	    foreigner.setCompanyDepartment(foreign.getCompany_department());
	    foreigner.setCountry(foreign.getCountry());
	    foreigner.setEditDate(new Date());
	    if (user != null && Untils.NotNull(user.getAccount())) {
		foreigner.setEditUser(user.getAccount());
	    }
	    foreigner.setName(foreign.getName());
	    foreigner.setSex(Integer.parseInt(foreign.getSex()));
	    foreigner.setStatus(0);
	    // 选填部分
	    if (Untils.NotNull(foreign.getPost())) {
		foreigner.setPost(foreign.getPost());
	    }
	    if (Untils.NotNull(foreign.getRole())) {
		foreigner.setRole(foreign.getRole());
	    }
	    if (pp_attachmentObject != null
		    && Untils.NotNull(pp_attachmentObject.getId() + "")) {
		if (Untils.NotNull(foreign.getPassport_id())) {
		    pp_attachmentObject.setCardId(foreign.getPassport_id());// 护照ID
		    foreigner.setPassportId(foreign.getPassport_id());
		}
		if (Untils.NotNull(passport_exp_date)) {
		    pp_attachmentObject.setEndTime(passport_exp_date);// 护照过期时间
		    foreigner.setPassportExpDate(passport_exp_date);
		}
	    }

	    if (Untils.NotNull(foreigner.getId() + "")) {
		foreignDao.UpdataReturnObject("updateByPrimaryKey", foreigner);
	    } else {
		throw new JnpcException(
			"foreigner can not updata ! id is null ");
	    }
	    // 补充PP的attachment表的相关资料
	    if (pp_attachmentObject != null
		    && Untils.NotNull(pp_attachmentObject.getId() + "")) {
		if (Untils.NotNull(foreign.getPassport_id())
			|| Untils.NotNull(passport_exp_date)) {
		    sattachment.UpdataObject(pp_attachmentObject);
		}
	    }

	    return "修改成功!";
	} catch (Exception e) {
	    log.info(e.getMessage());
	    log.info(e.toString());
	    log.info(e.getCause());
	    return "修改失败!";
	}
    }

    /**
     * 
     * @param name
     * @return
     */
    public List<FiForeigner> QueryByName(String name) {
	foreignerexample = new FiForeignerExample();
	Criteria criter=foreignerexample.createCriteria();
	criter.andNameLike(name);
	criter.andStatusNotEqualTo(1);
	foreignerexample.setOrderByClause(" name desc");
	List<FiForeigner> list = foreignDao.SelectByExample("selectByExample",
		foreignerexample);
	return list;
    }
    @Transactional(propagation=Propagation.REQUIRED)
    public String del(String idstr) {
	if (Untils.NotNull(idstr)) {
	    List<String> getlist = Untils.getlistBystr(idstr);
	    if (getlist != null && getlist.size() > 0) {
		for (int i = 0; i < getlist.size(); i++) {
		    String id = getlist.get(i);
		    FiForeigner forei = QueryByid_fi(id);
		    forei.setStatus(1);// 0初始化 1删除(失效)
		    UpdataObject(forei);
		}
		return "操作成功!";
	    } else {
		return "需要删除的人员列表为空！请确认后再提交";
	    }
	} else {
	    return "需要删除的人员列表为空！请确认后再提交";
	}
    }
    @Transactional(propagation=Propagation.REQUIRED)
    public void UpdataObject(FiForeigner foreign) {
	foreignDao.UpdataReturnObject("updateByPrimaryKey", foreign);
    }
    @Transactional(propagation=Propagation.REQUIRED)
    public String UpdataIsHere(String id, String status) {
	FiForeigner foreign_ = QueryByid_fi(id);
	if (foreign_ != null) {
	    if (Untils.NotNull(foreign_.getFkInvitationId())) {
		List<FiInout> inoutlist = inoutservices.QueryByinvitforeign(
			foreign_.getFkInvitationId(), id);
		boolean flag = false;
		if (inoutlist != null && inoutlist.size() > 0) {
		    FiInout inout = inoutlist.get(0);// 最近一条
		    if (status.equals("1") && inout.getType() == 1) {// 在连
			flag = true;
		    } else if (status.equals("0") && inout.getType() == 0) {// 不在连
			flag = true;
		    } else {
			return foreign_.getName() + "已经"
				+ (inout.getType() == 0 ? "出境" : "入境");
		    }
		} else {
		    return "无对应的出入境信息无法进行是否在连操作!";
		}
		if (flag) {
		    foreign_.setIsHere(Integer.parseInt(status));
		    UpdataObject(foreign_);
		}
		return "是否在连信息保存成功！";
	    } else {
		return foreign_.getName() + "无对应邀请函信息";
	    }
	} else {
	    return "未查询到对应的外籍人员信息";
	}
    }

    public FiForeigner QueryByid_fi(String id) {
	FiForeigner foreign = foreignDao.SelectById("selectByPrimaryKey",
		Integer.parseInt(id));
	return foreign;
    }

    /**
     * 
     * @param id
     * @return
     */
    public foreigner QueryByid(HttpServletRequest request, String id) {
	FiForeigner foreign = foreignDao.SelectById("selectByPrimaryKey",
		Integer.parseInt(id));
	foreigner forei = new foreigner();
	forei.setId(foreign.getId() + "");
	forei.setName(foreign.getName());
	forei.setSex(foreign.getSex() + "");
	forei.setBirthday(DateUtil.dateToString(foreign.getBirthday(),
		"yyyy-MM-dd"));
	forei.setCountry(foreign.getCountry());
	forei.setCompany_department(foreign.getCompanyDepartment());
	forei.setPassport_id(foreign.getPassportId());
	forei.setPassport_exp_date(DateUtil.dateToString(
		foreign.getPassportExpDate(), "yyyy-MM-dd"));
	forei.setPost(foreign.getPost());
	forei.setRole(foreign.getRole());
	forei.setFk_pp_attachment_id(foreign.getFkPpAttachmentId());
	if (Untils.NotNull(foreign.getFkPpAttachmentId())) {
	    FiAttachment attachement = sattachment.QueryById(foreign
		    .getFkPpAttachmentId());
	    String workpath = Untils.getWorkPath(request,
		    Untils.getSpitpath(attachement.getUrl()));
	    forei.setFk_pp_url(workpath);
	}
	forei.setFk_ee_attachment_id(foreign.getFkEeAttachmentId());
	if (Untils.NotNull(foreign.getFkEeAttachmentId())) {
	    FiAttachment attachement = sattachment.QueryById(foreign
		    .getFkEeAttachmentId());
	    String workpath = Untils.getWorkPath(request,
		    Untils.getSpitpath(attachement.getUrl()));
	    forei.setFk_ee_url(workpath);
	}
	forei.setExpert_evidence(foreign.getExpertEvidence() + "");

	forei.setFk_invitation_id(foreign.getFkInvitationId());
	forei.setIs_here(foreign.getIsHere() + "");
	forei.setStatus(foreign.getStatus() + "");
	if (Untils.NotNull(foreign.getFkRpPermitId())) {
	    forei.setFk_rp_permit_id(foreign.getFkRpPermitId());
	    FiResidencePermit permit = permitservices.QueryById(foreign
		    .getFkRpPermitId());
	    forei.setResidence_permit_id(permit.getResidencePermitId());
	    forei.setResidence_permit_kind(permit.getResidencePermitKind());
	    forei.setRp_exp_endDate(DateUtil.dateToString(
		    permit.getRpExpEnddate(), "yyyy-MM-dd"));
	    forei.setRp_Address(permit.getRpAddress());
	}
	return forei;
    }

    /**
     * 
     * @param by
     * @param page
     * @return
     */
    public List<FiForeigner> QueryList(PageMybatis page) {
	List<FiForeigner> list = foreignDao.SelectByPage("selectByPage", page);
	return list;
    }

    public String getsql(String foreignname, String passport_id,
	    String contry_from, String numb_invitation, String post,
	    String is_here_) {
	// 两个表
	StringBuffer buffer = new StringBuffer();
	buffer.append(" and t1.status <> 1 ");
	if (Untils.NotNull(foreignname)) {
	    buffer.append(" and ( t1.NAME like '%" + foreignname + "%' )");
	}
	if (Untils.NotNull(passport_id)) {
	    buffer.append(" and ( t1.PASSPORT_ID in('" + passport_id + "') )");
	}
	if (Untils.NotNull(contry_from)) {
	    buffer.append(" and t1.COUNTRY ='" + contry_from + "'");
	}
	if (Untils.NotNull(post)) {
	    buffer.append(" and t1.COMPANY_DEPARTMENT ='" + post + "'");
	}
	if (Untils.NotNull(is_here_)) {
	    buffer.append(" and t1.IS_HERE =" + is_here_);
	}
	if (Untils.NotNull(numb_invitation)) {
	    List<FiInvitation> invitation = invitationServices
		    .QueryByNumb(numb_invitation);// 获取invitation_id
	    if (invitation != null && invitation.size() > 0) {
		List<FiMiddle> middle_list = middleservice
			.QueryByInvitation(invitation);
		if (middle_list != null && middle_list.size() > 0) {
		    buffer.append(" and ( t1.id in (");
		    int j = 0;
		    for (int i = 0; i < middle_list.size(); i++) {// 从middle对象中获取personID
			FiMiddle middle = middle_list.get(i);
			if (Untils.NotNull(middle.getFkPersonId())) {
			    if (i == 0) {
				j++;
				buffer.append(middle.getFkPersonId());
			    } else {
				buffer.append("," + middle.getFkPersonId());
			    }
			}
		    }
		    buffer.append("))");
		}
	    }
	}
	return buffer.toString();
    }

    public List<FiInout> QueryByInOut(String foreign_id) {
	List<FiInout> inout = null;
	if (Untils.NotNull(foreign_id)) {
	    inout = inoutservices.QueryByid_fi(foreign_id);
	}
	return inout;
    }

    public List<FiForeigner> QueryByIdlist(List id) {
	FiForeignerExample example = new FiForeignerExample();
	example.createCriteria().andIdIn(id);
	example.setOrderByClause(" id desc ");
	List<FiForeigner> foreign = foreignDao.SelectByExample(
		"selectByExample", example);
	return foreign;
    }

    public PageMybatis QueryCount(String where) {
	return foreignDao.SelectCount("selectAllCount", where);
    }
}
