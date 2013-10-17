package cn.com.jnpc.foreign.dao;

import cn.com.jnpc.foreign.po.FiAttachment;

public interface attachmentDao {

    public FiAttachment InsertReturnObject(String classname,
	    FiAttachment attachment);

    public void Updata(String value, FiAttachment attachment);

    public FiAttachment SelectByPrimaryKey(String value, String id);

    public void DeleteByPrimaryKey(String value, String id);

    public FiAttachment UpdataReturnObject(String value, FiAttachment attachment);
}