package cn.com.jnpc.foreign.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.dao.foreignDao;
import cn.com.jnpc.foreign.model.FiForeignerExample;
import cn.com.jnpc.foreign.po.FiAttachment;
import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.po.FiInvitation;
import cn.com.jnpc.foreign.po.FiMiddle;
import cn.com.jnpc.foreign.po.FiResidencePermit;
import cn.com.jnpc.foreign.utils.DateUtil;
import cn.com.jnpc.foreign.utils.JnpcException;
import cn.com.jnpc.foreign.utils.Untils;
import cn.com.jnpc.foreign.vo.PageMybatis;
import cn.com.jnpc.foreign.vo.foreigner;

@Service
public class ForeignServices {
    private static Logger log = Logger.getLogger(ForeignServices.class);

    ResidencePermitServices permitservices;

    @Resource(name = "ResidencePermitServices")
    private void setPermitservices(ResidencePermitServices permitservices) {
	this.permitservices = permitservices;
    }

    MiddleServices middleservice;
    
    @Resource(name="MiddleServices")
    public void setMiddleservice(MiddleServices middleservice) {
        this.middleservice = middleservice;
    }
    
    FiResidencePermit permit;

    FiForeigner foreigner;

    InvitationServices invitationServices;
    
    @Resource(name="InvitationServices")
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
//    @Resource(name = "FiForeignerExample")
//    private void setForeignerexample(FiForeignerExample foreignerexample) {
//	this.foreignerexample = foreignerexample;
//    }

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
    public String store(foreigner foreign, MultipartFile pp_attachment,
	    MultipartFile ee_attachment, User user) {
	/**
	 * 存储顺序..文件->attachment->foreign 1 是PP 护照 2 是ee 专家证 3是RP 签证 0是邀请函
	 */
	Date passport_exp_date = null;
	Date birthday = null;
	Date rp_enddate = null;
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
		    foreigner=new FiForeigner();
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
	    if (Untils.NotNull(foreign.getResidence_permit_kind()) && !"0".equals(foreign.getResidence_permit_kind())){// 不为0表示存在居留许可
		    //permit.setResidencePermitId(foreign.getResidence_permit_id());
		    permit=new FiResidencePermit();
		    permit.setResidencePermitKind(foreign
			    .getResidence_permit_kind());
		    permit.setRpAddress(foreign.getRp_Address());
		    permit.setRpExpEnddate(rp_enddate);
		    permit.setStatus(0);
		    permit.setIsDefer(0);
		    permitObject = permitservices.InsertReturObject(permit,
			    user);
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
    public String storeUpdata(foreigner foreign, MultipartFile pp_attachment,
	    MultipartFile ee_attachment, User user) {
	/**
	 * 存储顺序..文件->attachment->foreign 1 是PP 护照 2 是ee 专家证 3是RP 签证 0是邀请函
	 */
	Date passport_exp_date = null;
	Date birthday = null;
	Date rp_enddate = null;
	String ee_number = Untils.NotNull(foreign.getExpert_evidence()) ? foreign
		.getExpert_evidence() : "";
	try {
	    if(Untils.NotNull(foreign.getId())){
		foreigner=foreignDao.SelectById("selectByPrimaryKey", Integer.parseInt(foreign.getId()));
	    }else{
		foreigner=new FiForeigner();
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
		if(Untils.NotNull(foreign.getFk_pp_attachment_id())){
		    pp_attachmentObject = sattachment.UpdataReturnObject(
			pp_attachment, user, foreign.getFk_pp_attachment_id());
		}else{
		    pp_attachmentObject = sattachment.InsertReturObject(
				pp_attachment, user, 1);
		}
		if(pp_attachmentObject != null
			&& Untils.NotNull(pp_attachmentObject.getId() + "")){
		    foreigner.setFkPpAttachmentId(pp_attachmentObject.getId()+"");
		}else{
		    new JnpcException("pp_attachment store error!");
		}
	    }
	    // 如果选择了专家证 则上传，同时保存勾选状态
	    if (Untils.NotNull(ee_number) && "1".equals(ee_number)) {
		    if (ee_attachment.getSize() > 0) {
			if(Untils.NotNull(foreign.getFk_ee_attachment_id())){			    
			    ee_attachmentObject = sattachment.UpdataReturnObject(
				    ee_attachment, user, foreign.getFk_ee_attachment_id());
			    
			}else{
			    ee_attachmentObject = sattachment.InsertReturObject(
				    ee_attachment, user, 2);
			}
			if(ee_attachmentObject != null
				&& Untils.NotNull(ee_attachmentObject.getId() + "")){
			    foreigner.setFkEeAttachmentId(ee_attachmentObject.getId()+"");
			}else{
			    new JnpcException("ee_attachment store error!");
			}
		    }
		    foreigner.setExpertEvidence(Integer.parseInt(ee_number));
	    }else if(Untils.NotNull(ee_number) && "0".equals(ee_number)){
		if (Untils.NotNull(foreign.getFk_ee_attachment_id() ) ){
		    sattachment.DeleteByID(foreign.getFk_ee_attachment_id());
		    foreigner.setFkEeAttachmentId("");
		}
	    }
	    // 存储附件居留许可
	    if (Untils.NotNull(foreign.getResidence_permit_kind()) && !"0".equals(foreign.getResidence_permit_kind())){// 不为0表示存在居留许可
		    //permit.setid(foreign.getFk_rp_permit_id());
		    if(Untils.NotNull(foreign.getFk_rp_permit_id())){
			permit=permitservices.QueryById(foreign.getFk_rp_permit_id());
		    }else{
			permit=new FiResidencePermit();
		    }
		    permit.setResidencePermitKind(foreign
			    .getResidence_permit_kind());
		    permit.setRpAddress(foreign.getRp_Address());
		    permit.setRpExpEnddate(rp_enddate);
		    permit.setStatus(0);
		    permit.setIsDefer(0);
		    if(Untils.NotNull(foreign.getFk_rp_permit_id())){
			permitObject = permitservices.UpdataReturnObject(permit,
				user);
		    }else{
			permitObject = permitservices.InsertReturObject(permit,
				user);
		    }
		    if(permitObject != null
			    && Untils.NotNull(permitObject.getId() + "")){
			foreigner.setFkRpPermitId(permitObject.getId()+"");
		    }else{
			throw new JnpcException("permitObject store error!");
		    }
	    }else if(Untils.NotNull(foreign.getResidence_permit_kind()) && "0".equals(foreign.getResidence_permit_kind())){
		if(Untils.NotNull(foreign.getFk_rp_permit_id())){
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
	    }else{
		throw new JnpcException("foreigner can not updata ! id is null ");
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
	foreignerexample=new FiForeignerExample();
	foreignerexample.createCriteria().andNameLike(name);
	foreignerexample.setOrderByClause(" name desc");
	List<FiForeigner> list = foreignDao.SelectByExample("selectByExample",
		foreignerexample);
	return list;
    }
    public void UpdataObject(FiForeigner foreign) {
	foreignDao.UpdataReturnObject("updateByPrimaryKey",foreign);
    }
    public FiForeigner QueryByid_fi(String id) {
	FiForeigner foreign = foreignDao.SelectById("selectByPrimaryKey",Integer.parseInt(id));
	return foreign;
    }
    /**
     * 
     * @param id
     * @return
     */
    public foreigner QueryByid(String id) {
	FiForeigner foreign = foreignDao.SelectById("selectByPrimaryKey",Integer.parseInt(id));
	foreigner forei=new foreigner();
	forei.setId(foreign.getId()+"");
	forei.setName(foreign.getName());
	forei.setSex(foreign.getSex()+"");
	forei.setBirthday(DateUtil.dateToString(foreign.getBirthday(),"yyyy-MM-dd"));
	forei.setCountry(foreign.getCountry());
	forei.setCompany_department(foreign.getCompanyDepartment());
	forei.setPassport_id(foreign.getPassportId());
	forei.setPassport_exp_date(DateUtil.dateToString(foreign.getPassportExpDate(),"yyyy-MM-dd"));
	forei.setPost(foreign.getPost());
	forei.setRole(foreign.getRole());
	forei.setFk_pp_attachment_id(foreign.getFkPpAttachmentId());
	forei.setExpert_evidence(foreign.getExpertEvidence()+"");
	forei.setFk_ee_attachment_id(foreign.getFkEeAttachmentId());
	
	forei.setFk_invitation_id(foreign.getFkInvitationId());
	forei.setIs_here(foreign.getIsHere()+"");
	forei.setStatus(foreign.getStatus()+"");
	if(Untils.NotNull(foreign.getFkRpPermitId())){
	FiResidencePermit permit=permitservices.QueryById(foreign.getFkRpPermitId());
	forei.setResidence_permit_id(permit.getResidencePermitId());
	forei.setResidence_permit_kind(permit.getResidencePermitKind());
	forei.setRp_exp_endDate(DateUtil.dateToString(permit.getRpExpEnddate(),"yyyy-MM-dd"));
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
    public List<FiForeigner> QueryList(String by,PageMybatis page){
	List<FiForeigner> list=null;
	if(by == "All"){
	    list=foreignDao.SelectAll("selectByPage",page);
	}
	return list;
    }
    /**
     * 
     * @param foreignname
     * @param passport_id
     * @param contry_from
     * @param numb_invitation
     * @param post
     * @return
     */
    public List<FiForeigner> QueryandList(String foreignname,String passport_id,String contry_from,String post,PageMybatis page){
	//一个表
	StringBuffer buffer=new StringBuffer();
	buffer.append(" t1.* from fi_foreigner t1 where 1=1 ");
	if(Untils.NotNull(foreignname)){
	    buffer.append(" and ( t1.NAME like '%"+foreignname+"%' )");
	}
	if(Untils.NotNull(passport_id)){
	    //criteria_foreign.andPassportIdEqualTo(passport_id);
	    buffer.append(" and ( t1.PASSPORT_ID in("+passport_id+") )");
	}
	if(Untils.NotNull(contry_from)){
	    //criteria_foreign.andCountryEqualTo(contry_from);
	    buffer.append(" and t1.COUNTRY ='"+contry_from+"'");
	}
	if(Untils.NotNull(post)){
	    //criteria_foreign.andPostEqualTo(post);
	    buffer.append(" and t1.POST ='"+post+"'");
	}
	page.setQuerysql(buffer.toString());
	List<FiForeigner> list = foreignDao.SelectByPage("selectByPage", page);
	return list;
    }
    /**
     * 
     * @param foreignname
     * @param passport_id
     * @param contry_from
     * @param numb_invitation
     * @param post
     * @return
     */
    public List<FiForeigner> QueryandInvitation(String foreignname,String passport_id,String contry_from,String numb_invitation,String post,PageMybatis page){
	//两个表
	StringBuffer buffer=new StringBuffer();
	buffer.append(" t1.* from fi_foreigner t1 where 1=1 ");
	if(Untils.NotNull(foreignname)){
	    buffer.append(" and ( t1.NAME like '%"+foreignname+"%' )");
	}
	if(Untils.NotNull(passport_id)){
	    buffer.append(" and ( t1.PASSPORT_ID in("+passport_id+") )");
	}
	if(Untils.NotNull(contry_from)){
	    buffer.append(" and t1.COUNTRY ='"+contry_from+"'");
	}
	if(Untils.NotNull(post)){
	    buffer.append(" and t1.POST ='"+post+"'");
	}if(Untils.NotNull(numb_invitation)){
	    List<FiInvitation> invitation=invitationServices.QueryByNumb(numb_invitation);//获取invitation_id
	    if(invitation!=null && invitation.size()>0){
	    	    List<FiMiddle> middle_list=middleservice.QueryByInvitation(invitation);
        	    if(middle_list!=null && middle_list.size()>0){
                	    buffer.append(" and ( t1.id in (");
                	    for(int i=0;i<middle_list.size();i++){//从middle对象中获取personID
                		FiMiddle middle= middle_list.get(i);
                		if(i==0){
                		    buffer.append(middle.getFkPersonId());
                		}else{
                		    buffer.append(","+middle.getFkPersonId());
                		}
                	    }
                	    buffer.append("))");
        	    }
	    }
	}
	page.setQuerysql(buffer.toString());
	List<FiForeigner> foreign_list=foreignDao.SelectByPage("selectByPage", page);
	return foreign_list;
    }
}
