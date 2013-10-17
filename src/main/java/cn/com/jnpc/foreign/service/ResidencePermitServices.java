package cn.com.jnpc.foreign.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.dao.ResidencePermitDao;
import cn.com.jnpc.foreign.po.FiResidencePermit;
import cn.com.jnpc.foreign.utils.Untils;

public class ResidencePermitServices {
    private static Logger log = Logger.getLogger(ResidencePermitServices.class);
    ResidencePermitDao permitDao;

    @Resource(name = "ResidencePermitDao")
    private void setPermitDao(ResidencePermitDao permitDao) {
	this.permitDao = permitDao;
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
    public FiResidencePermit QueryById(String id){
	return permitDao.SelectByPrimaryKey("selectByPrimaryKey",id);
    }
    public void DeleteByID(String id){
	permitDao.DeleteByPrimaryKey("deleteByPrimaryKey",id);
    }
    public FiResidencePermit UpdataReturnObject(FiResidencePermit permit,
	    User user) {
	try {
	    FiResidencePermit permitA=null;
	    permit.setEditDate(new Date());
	    if (user != null && Untils.NotNull(user.getAccount())) {
		permit.setEditUser(user.getAccount());
	    }
	    permitA=permitDao.UpdataReturnObject("updateByPrimaryKey", permit);
	    return permitA;
	} catch (Exception e) {
	    log.info(e.getMessage());
	    log.info(e.toString());
	    log.info(e.getCause());
	    return null;
	}
    }
}
