package cn.com.jnpc.foreign.daoImpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.com.jnpc.foreign.dao.fiMiddleDao;
import cn.com.jnpc.foreign.model.FiMiddleExample;
import cn.com.jnpc.foreign.po.FiMiddle;

public class fiMiddleImpl extends SqlSessionDaoSupport implements fiMiddleDao {

    public String InsertReturnID(String example, FiMiddle middle) {
	getSqlSession().insert(
		"cn.com.jnpc.foreign.mapper.FiMiddleMapper." + example, middle);
	return middle.getId()+"";
    }

    public void Updata(String example, FiMiddle middle) {
	getSqlSession().update(
		"cn.com.jnpc.foreign.mapper.FiMiddleMapper." + example, middle);
    }

    public List<FiMiddle> SelectByExample(String example, FiMiddleExample middle) {
	return getSqlSession().selectList(
		"cn.com.jnpc.foreign.mapper.FiMiddleMapper." + example, middle);
    }

    public void DeleteByPrimaryKey(String example, int middle) {
	getSqlSession().delete(
		"cn.com.jnpc.foreign.mapper.FiMiddleMapper." + example, middle);
    }

    public FiMiddle SelectByOne(String example, FiMiddleExample middle) {
	return getSqlSession().selectOne(
		"cn.com.jnpc.foreign.mapper.FiMiddleMapper." + example, middle);
    }

}
