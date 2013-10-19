package cn.com.jnpc.foreign.daoImpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.com.jnpc.foreign.dao.fiInOutDao;
import cn.com.jnpc.foreign.model.FiInoutExample;
import cn.com.jnpc.foreign.po.FiInout;

public class fiInOutImpl extends SqlSessionDaoSupport implements fiInOutDao {

    public FiInout InsertReturObject(String example, FiInout inout) {
	getSqlSession().insert("cn.com.jnpc.foreign.mapper.FiInoutMapper."+example,inout);
	return inout;
    }

    public List<FiInout> selectByExample(String example,
	    FiInoutExample inoutexample) {
	return getSqlSession().selectList("cn.com.jnpc.foreign.mapper.FiInoutMapper."+example,inoutexample);
    }

}
