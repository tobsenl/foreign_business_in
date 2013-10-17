package cn.com.jnpc.foreign.daoImpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jnpc.foreign.dao.fiBlobDao;
import cn.com.jnpc.foreign.model.FiBlobExample;
import cn.com.jnpc.foreign.po.FiBlob;

public class fiBlobImpl extends SqlSessionDaoSupport implements fiBlobDao {

    public FiBlob InsertReturnObject(String example, FiBlob blob) {
	getSqlSession().insert(
		"cn.com.jnpc.foreign.mapper.FiBlobMapper." + example, blob);
	return blob;
    }

    public void Updata(String example, FiBlob blob) {
	getSqlSession().update("cn.com.jnpc.foreign.mapper.FiBlobMapper." + example, blob);
    }

    public FiBlob SelectByPrimaryKey(String example, String id) {
	return getSqlSession().selectOne(
	"cn.com.jnpc.foreign.mapper.FiBlobMapper." + example,Integer.parseInt(id));
    }

    public FiBlob UpdataByPrimaryKey(String example, FiBlob blob) {
	getSqlSession().update("cn.com.jnpc.foreign.mapper.FiBlobMapper." + example, blob);
	return blob;
    }
}
