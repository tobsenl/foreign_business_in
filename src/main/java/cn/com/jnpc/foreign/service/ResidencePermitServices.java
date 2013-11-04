package cn.com.jnpc.foreign.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.dao.ResidencePermitDao;
import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.po.FiInout;
import cn.com.jnpc.foreign.po.FiMiddle;
import cn.com.jnpc.foreign.po.FiResidencePermit;
import cn.com.jnpc.foreign.utils.Untils;

public class ResidencePermitServices {
    private static Logger log = Logger.getLogger(ResidencePermitServices.class);
    ResidencePermitDao permitDao;

    @Resource(name = "ResidencePermitDao")
    private void setPermitDao(ResidencePermitDao permitDao) {
	this.permitDao = permitDao;
    }

    ForeignServices foreignServices;

    @Resource(name = "ForeignServices")
    public void setForeignServices(ForeignServices foreignServices) {
	this.foreignServices = foreignServices;
    }

    MiddleServices middleservices;
    @Resource(name="MiddleServices")
    public void setMiddleservices(MiddleServices middleservices) {
        this.middleservices = middleservices;
    }
    InOutServices inoutservices;
    @Resource(name="InOutServices")
    public void setInoutservices(InOutServices inoutservices) {
        this.inoutservices = inoutservices;
    }

    public FiResidencePermit InsertReturObject(FiResidencePermit permit,
	    User user) {
	try {
	    permit.setCreateDate(new Date());
	    if (user != null && Untils.NotNull(user.getAccount())) {
		permit.setCreateUser(user.getAccount());
	    }
	    permitDao.InsertReturnID("insert", permit);
	    return permit;
	} catch (Exception e) {
	    log.info(e.getMessage());
	    log.info(e.toString());
	    log.info(e.getCause());
	    return null;
	}
    }

    public FiResidencePermit QueryById(String id) {
	return permitDao.SelectByPrimaryKey("selectByPrimaryKey", id);
    }

    public void DeleteByID(String id) {
	permitDao.DeleteByPrimaryKey("deleteByPrimaryKey", id);
    }

    public FiResidencePermit UpdataReturnObject(FiResidencePermit permit,
	    User user) {
	try {
	    FiResidencePermit permitA = null;
	    permit.setEditDate(new Date());
	    if (user != null && Untils.NotNull(user.getAccount())) {
		permit.setEditUser(user.getAccount());
	    }
	    permitA = permitDao
		    .UpdataReturnObject("updateByPrimaryKey", permit);
	    return permitA;
	} catch (Exception e) {
	    log.info(e.getMessage());
	    log.info(e.toString());
	    log.info(e.getCause());
	    return null;
	}
    }

    public String store(FiResidencePermit permit, List id_list, User user) {
	for (int i = 0; i < id_list.size(); i++) {
	    String str = (String) id_list.get(i);
	    if (Untils.NotNull(str)) {
		FiForeigner foreign = foreignServices.QueryByid_fi(str);
		if (Untils.NotNull(foreign.getFkInvitationId())) {
		    if(Untils.NotNull(foreign.getFkBeuse()+"") && foreign.getFkBeuse() == 1){
			FiResidencePermit fipermit=new FiResidencePermit();
			fipermit.setFkForeignerId(foreign.getId()+"");
			fipermit.setIsDefer(1);
			fipermit.setResidencePermitKind(permit.getResidencePermitKind());
			fipermit.setRpAddress(permit.getRpAddress());
			fipermit.setRpExpEnddate(permit.getRpExpEnddate());
			fipermit.setStatus(0);
			FiResidencePermit fipermit_=InsertReturObject(fipermit, user);
			foreign.setFkRpPermitId(fipermit_.getId()+"");
			foreignServices.UpdataObject(foreign);
			FiMiddle middle=middleservices.QueryByForeign(foreign);
			middle.setDeferId("1");
			FiInout inout=new FiInout();
			inout.setFkForeignerId(foreign.getId()+"");
			inout.setFkInvitationId(foreign.getFkInvitationId());
			inout.setBeginTime(new Date());
			inout.setEndTime(fipermit_.getRpExpEnddate());
			inout.setStatus(0);
			inout.setType(2);
			
			inout.setContent(fipermit_.getId()+"");
			inoutservices.store(inout,user);
		    }else{
			if(foreign.getFkBeuse() == 0){
			    return foreign.getName() + "对应的邀请函还未启用无法进行签证延期";
			}
		    }
		} else {
		    return foreign.getName() + "无对应邀请函";
		}
	    }
	}
	return "签证延期成功!";
    }
}
