package cn.com.jnpc.foreign.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.dao.fiInOutDao;
import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.po.FiInout;
import cn.com.jnpc.foreign.utils.Untils;

public class InOutServices {
    private static Logger log = Logger.getLogger(InOutServices.class);

    fiInOutDao inoutDao;

    @Resource(name = "fiInOutDao")
    public void setInoutDao(fiInOutDao inoutDao) {
	this.inoutDao = inoutDao;
    }

    ForeignServices foreignServices;

    @Resource(name = "ForeignServices")
    public void setForeignServices(ForeignServices foreignServices) {
	this.foreignServices = foreignServices;
    }

    public String store(FiInout inout, List list_id, User user) {
	try {
	    for (int i = 0; i < list_id.size(); i++) {
		String str = (String) list_id.get(i);
		if (Untils.NotNull(str)) {
		    FiForeigner foreign = foreignServices.QueryByid_fi(str);
		    inout.setFkForeignerId(str);
		    inout.setFkInvitationId(foreign.getFkInvitationId());
		    inout.setCreateDate(new Date());
		    inout.setStatus(0);
		    if (user != null) {
			inout.setCreateUser(user.getAccount());
		    }
		    inoutDao.InsertReturObject("insert",inout);
		} else {
		    continue;
		}
	    }
	    return "信息正常保存";
	} catch (Exception e) {
	    log.info(e.getMessage());
	    log.info(e.toString());
	    log.info(e.getCause());
	    return "信息保存出错请重新提交";
	}
    }

}
