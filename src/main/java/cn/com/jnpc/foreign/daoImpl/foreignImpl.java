package cn.com.jnpc.foreign.daoImpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.com.jnpc.foreign.dao.foreignDao;
import cn.com.jnpc.foreign.model.FiForeignerExample;
import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.vo.PageMybatis;

public class foreignImpl extends SqlSessionDaoSupport implements foreignDao{

    public String InsertReturObject(String example, FiForeigner foreigner) {
	getSqlSession().insert(
		"cn.com.jnpc.foreign.mapper.FiForeignerMapper." + example, foreigner);
	return foreigner.getId()+"";
    }

    public List<FiForeigner> SelectByExample(String example,FiForeignerExample foreigner) {
	return getSqlSession().selectList(
		"cn.com.jnpc.foreign.mapper.FiForeignerMapper." + example, foreigner);
	 
    }
    public List<FiForeigner> SelectByPage(String example,PageMybatis foreigner) {
	return getSqlSession().selectList(
		"cn.com.jnpc.foreign.mapper.FiForeignerMapper." + example, foreigner);
	
    }
    public FiForeigner SelectById(String example,int id) {
	return getSqlSession().selectOne(
		"cn.com.jnpc.foreign.mapper.FiForeignerMapper." + example, id);
	
    }

    public FiForeigner UpdataReturnObject(String example, FiForeigner foreigner) {
	getSqlSession().update(
		"cn.com.jnpc.foreign.mapper.FiForeignerMapper." + example,foreigner);
	return foreigner;
    }

    public PageMybatis SelectCount(String example,String where) {
	return getSqlSession().selectOne(
		"cn.com.jnpc.foreign.mapper.FiForeignerMapper." + example,where);
    }
}
