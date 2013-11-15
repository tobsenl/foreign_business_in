package cn.com.jnpc.foreign.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.dao.fiInOutDao;
import cn.com.jnpc.foreign.model.FiInoutExample;
import cn.com.jnpc.foreign.model.FiInoutExample.Criteria;
import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.po.FiInout;
import cn.com.jnpc.foreign.utils.Untils;

@Transactional
@Service("InOutServices")
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
    @Transactional(propagation=Propagation.REQUIRED)
    public void store(FiInout inout, User user) {
	inout.setCreateDate(new Date());
	if (user != null) {
	    inout.setCreateUser(user.getAccount());
	}
	inoutDao.InsertReturObject("insert", inout);
    }
    @Transactional(propagation=Propagation.REQUIRED)
    public String store(FiInout inout, List list_id, User user) {
	try {
	    for (int i = 0; i < list_id.size(); i++) {
		String str = (String) list_id.get(i);
		if (Untils.NotNull(str)) {
		    FiForeigner foreign = foreignServices.QueryByid_fi(str);
		    boolean flag=false;
		    if (Untils.NotNull(foreign.getFkInvitationId())) {
		    	List<FiInout> inoutlist=QueryByinvitforeign(foreign.getFkInvitationId()+"",foreign.getId()+"");
				if((foreign.getIsHere() == 1) && (0 == inout.getType())){//在连就出境
					if(inoutlist.size()>0){
						FiInout fout=inoutlist.get(0);
						if(fout.getType() == 0){
							return foreign.getName()+"之前"+fout.getCreateDate()+"已经进行了出境操作！";
						}else{
							flag=true;
						}
					}else{
						return foreign.getName()+"对应的邀请函中无对应的入境信息！无法进行出境操作！";
					}
				}else if((foreign.getIsHere() == 0) && (1 == inout.getType())){
					if(inoutlist.size()>0){
						FiInout fout=inoutlist.get(0);
						if(fout.getType() != 0){
							return foreign.getName()+"已经进行了"+(fout.getType()==1?"入境":"签证延期");
						}else{
							flag=true;
						}
					}else{
					    flag=true;
					}
				    flag=true;
				}else{
				    return foreign.getName()+"当前处于 "+(foreign.getIsHere()==1?"在连 状态":"不在连 状态")+"无法进行"+("1".equals(inout.getType())?"入境":"出境")+"操作";
				}
		    } else {
			return foreign.getName() + "无对应邀请函";
			// 这里还需要添加 1.判断写入时是否存在邀请函。存在则可以写inout；
			// 2.判断上条是否为out 为out则不能填写out 但是可以填写in
			// 反之其他的可以填写out但是不能填写in
		    }
		    if (flag) {
			inout.setFkForeignerId(str);
			inout.setFkInvitationId(foreign.getFkInvitationId());
			inout.setCreateDate(new Date());
			inout.setStatus(0);
			if (user != null) {
			    inout.setCreateUser(user.getAccount());
			}
			inoutDao.InsertReturObject("insert", inout);
		    }
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

    public List<FiInout> QueryByid_fi(String id) {
	List<FiInout> out = null;
	if (Untils.NotNull(id)) {
	    FiInoutExample inoutexamp = new FiInoutExample();
	    inoutexamp.createCriteria().andFkForeignerIdEqualTo(id);
	    inoutexamp.setOrderByClause(" CREATE_DATE desc");
	    out = inoutDao.selectByExample("selectByExample", inoutexamp);
	}
	return out;
    }

    public List<FiInout> QueryByinvitforeign(String invit, String foreign) {
	List<FiInout> out = null;
	if (Untils.NotNull(invit) && Untils.NotNull(foreign)) {
	    FiInoutExample inoutexamp = new FiInoutExample();
	    Criteria c = inoutexamp.createCriteria();
	    c.andFkForeignerIdEqualTo(foreign);
	    c.andFkInvitationIdEqualTo(invit);
	    inoutexamp.setOrderByClause(" CREATE_DATE desc");
	    out = inoutDao.selectByExample("selectByExample", inoutexamp);
	}
	return out;
    }
}
