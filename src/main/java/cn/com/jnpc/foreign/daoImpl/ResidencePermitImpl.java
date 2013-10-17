package cn.com.jnpc.foreign.daoImpl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.com.jnpc.foreign.dao.ResidencePermitDao;
import cn.com.jnpc.foreign.po.FiResidencePermit;

public class ResidencePermitImpl extends SqlSessionDaoSupport implements ResidencePermitDao {

    public String InsertReturnID(String example, FiResidencePermit permit) {
	getSqlSession().insert(
		"cn.com.jnpc.foreign.mapper.FiResidencePermitMapper." + example, permit);
	return permit.getId()+"";
    }

    public FiResidencePermit SelectByPrimaryKey(String example, String id) {
	return getSqlSession().selectOne("cn.com.jnpc.foreign.mapper.FiResidencePermitMapper." + example,Integer.parseInt(id));
    }

    public FiResidencePermit UpdataReturnObject(String example,
	    FiResidencePermit permit) {
	getSqlSession().update(
		"cn.com.jnpc.foreign.mapper.FiResidencePermitMapper." + example, permit);
	return permit;
    }

    public void DeleteByPrimaryKey(String example, String id) {
	getSqlSession().delete(
		"cn.com.jnpc.foreign.mapper.FiResidencePermitMapper." + example, Integer.parseInt(id));
    }

}
