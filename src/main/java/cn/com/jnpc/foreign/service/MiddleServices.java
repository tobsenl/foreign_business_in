package cn.com.jnpc.foreign.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.dao.fiMiddleDao;
import cn.com.jnpc.foreign.model.FiMiddleExample;
import cn.com.jnpc.foreign.model.FiMiddleExample.Criteria;
import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.po.FiInvitation;
import cn.com.jnpc.foreign.po.FiMiddle;
import cn.com.jnpc.foreign.utils.Untils;

public class MiddleServices {
    FiMiddle middle;
    
    fiMiddleDao middleDao;
    @Resource(name="fiMiddleDao")
    private void setMiddleDao(fiMiddleDao middleDao) {
        this.middleDao = middleDao;
    }
    ForeignServices foreignservice;
    @Resource(name="ForeignServices")
    private void setForeignservice(ForeignServices foreignservice) {
        this.foreignservice = foreignservice;
    }
    public FiMiddle InsertReturObject(String foreign_id,User user){
	middle=new FiMiddle();
	middle.setFkPersonId(foreign_id);
	middle.setCreateDate(new Date());
	if(user!=null){
	    middle.setCreateUser(user.getAccount());
	}
	middleDao.InsertReturnID("insert", middle);
	return middle;
    }
    public FiMiddle UpdataObject(FiMiddle middle_v){
	middleDao.Updata("updateByPrimaryKey",middle_v);
	return middle_v;
    }
    public void deleteByPrimaryKey(String id){
	middleDao.DeleteByPrimaryKey("deleteByPrimaryKey",Integer.parseInt(id));
    }
    public List<FiMiddle> QueryByInvitation(List<FiInvitation> invitation){
	List<String> list=new ArrayList<String>();
	if(invitation!=null && invitation.size()>0){
	    for(int i=0;i<invitation.size();i++){
		FiInvitation invi=invitation.get(i);
		list.add(invi.getId()+"");
	    }
	}
	FiMiddleExample example=new FiMiddleExample();
	example.createCriteria().andFkInvitationIdIn(list);
	return middleDao.SelectByExample("selectByExample",example);
    }
    public List<FiMiddle> QueryByInvitation(FiInvitation invitation){
	FiMiddleExample example=new FiMiddleExample();
	example.createCriteria().andFkInvitationIdEqualTo(invitation.getId()+"");
	return middleDao.SelectByExample("selectByExample",example);
    }
    public List<FiMiddle> QueryByInvitation(String invitation_id){
	FiMiddleExample example=new FiMiddleExample();
	example.createCriteria().andFkInvitationIdEqualTo(invitation_id);
	return middleDao.SelectByExample("selectByExample",example);
    }
    public List<FiMiddle> QueryByForeign(String foreign_id){
	List<String> list=new ArrayList<String>();
	if(Untils.NotNull(foreign_id)){
	    String [] array= foreign_id.split(",");
	    for(int i=0;i<array.length;i++){
		String invi=array[i];
		if(Untils.NotNull(invi)){
			list.add(invi);
		}
	    }
	}
	FiMiddleExample example=new FiMiddleExample();
	example.createCriteria().andFkPersonIdIn(list);
	return middleDao.SelectByExample("selectByExample",example);
    }
    public FiMiddle QueryByForeign(FiForeigner foreign){
	FiMiddleExample example=new FiMiddleExample();
	Criteria criteria=example.createCriteria();
	criteria.andFkPersonIdEqualTo(foreign.getId()+"");
	criteria.andFkInvitationIdEqualTo(foreign.getFkInvitationId());
	return middleDao.SelectByOne("selectByExample",example);
    }
}
 