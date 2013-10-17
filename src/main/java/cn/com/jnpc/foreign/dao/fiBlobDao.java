package cn.com.jnpc.foreign.dao;

import cn.com.jnpc.foreign.po.FiBlob;

public interface fiBlobDao{
   
    public FiBlob InsertReturnObject(String example,FiBlob blob);
    
    public void Updata(String example,FiBlob blob);
    
    public FiBlob SelectByPrimaryKey(String example,String id);
    
    public FiBlob UpdataByPrimaryKey(String example,FiBlob blob);
}
