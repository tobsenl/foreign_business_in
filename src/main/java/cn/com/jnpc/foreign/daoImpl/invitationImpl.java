package cn.com.jnpc.foreign.daoImpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.com.jnpc.foreign.dao.fiMiddleDao;
import cn.com.jnpc.foreign.dao.invitationDao;
import cn.com.jnpc.foreign.model.FiInvitationExample;
import cn.com.jnpc.foreign.po.FiInvitation;
import cn.com.jnpc.foreign.po.FiMiddle;
import cn.com.jnpc.foreign.vo.PageMybatis;

public class invitationImpl extends SqlSessionDaoSupport implements invitationDao {

    public String InsertReturId(String example, FiInvitation invitation) {
	getSqlSession().insert(
		"cn.com.jnpc.foreign.mapper.FiInvitationMapper." + example, invitation);
	return invitation.getId()+"";
    }

    public List<FiInvitation> SelectByExample(String example,
	    FiInvitationExample invitation) {
	return getSqlSession().selectList(
		"cn.com.jnpc.foreign.mapper.FiInvitationMapper." + example, invitation);
	
    }

    public List<FiInvitation> SelectAll(String example, PageMybatis page) {
	return getSqlSession().selectList(
		"cn.com.jnpc.foreign.mapper.FiInvitationMapper." + example, page);
	
    }

    public FiInvitation SelectById(String example, int id) {
	return getSqlSession().selectOne(
		"cn.com.jnpc.foreign.mapper.FiInvitationMapper." + example, id);
	
    }

    public FiInvitation UptataReturnObj(String example, FiInvitation invitation) {
	getSqlSession().update(
		"cn.com.jnpc.foreign.mapper.FiInvitationMapper." + example, invitation);
	return invitation;
    }

    public PageMybatis SelectCount(String example,String where) {
	return getSqlSession().selectOne(
		"cn.com.jnpc.foreign.mapper.FiInvitationMapper." + example, where);
    }
}
