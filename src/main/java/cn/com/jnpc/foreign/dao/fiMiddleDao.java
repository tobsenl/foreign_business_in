package cn.com.jnpc.foreign.dao;

import java.util.List;

import cn.com.jnpc.foreign.model.FiMiddleExample;
import cn.com.jnpc.foreign.po.FiMiddle;

public interface fiMiddleDao {
    public String InsertReturnID(String example,FiMiddle middle);
    public void Updata(String example,FiMiddle middle);
    public void DeleteByPrimaryKey(String example,int middle);
    public List<FiMiddle> SelectByExample(String example,FiMiddleExample middle);
}
