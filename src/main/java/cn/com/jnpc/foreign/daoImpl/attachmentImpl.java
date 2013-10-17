package cn.com.jnpc.foreign.daoImpl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.com.jnpc.foreign.dao.attachmentDao;
import cn.com.jnpc.foreign.po.FiAttachment;

public class attachmentImpl extends SqlSessionDaoSupport implements attachmentDao{
    
    public FiAttachment InsertReturnObject(String value,FiAttachment attachment){
	getSqlSession().insert("cn.com.jnpc.foreign.mapper.FiAttachmentMapper."+value,attachment);
	return attachment;
    }

    public void Updata(String value,FiAttachment attachment) {
	getSqlSession().update("cn.com.jnpc.foreign.mapper.FiAttachmentMapper."+value,attachment);
    }

    public FiAttachment SelectByPrimaryKey(String value, String id) {
	// TODO Auto-generated method stub
	return getSqlSession().selectOne("cn.com.jnpc.foreign.mapper.FiAttachmentMapper."+value,Integer.parseInt(id));
    }

    public FiAttachment UpdataReturnObject(String value, FiAttachment attachment) {
	getSqlSession().update("cn.com.jnpc.foreign.mapper.FiAttachmentMapper."+value,attachment);
	return attachment;
    }

    public void DeleteByPrimaryKey(String value, String id) {
	getSqlSession().delete("cn.com.jnpc.foreign.mapper.FiAttachmentMapper."+value,Integer.parseInt(id));
	
    }
}