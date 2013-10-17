package cn.com.jnpc.foreign.daoImpl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.com.jnpc.foreign.dao.fiInOutDao;
import cn.com.jnpc.foreign.po.FiInout;

public class fiInOutImpl extends SqlSessionDaoSupport implements fiInOutDao {

    public FiInout InsertReturObject(String example, FiInout inout) {
	getSqlSession().insert("cn.com.jnpc.foreign.mapper.FiInoutMapper."+example,inout);
	return inout;
    }

}
