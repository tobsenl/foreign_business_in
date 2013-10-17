package cn.com.jnpc.foreign.dao;

import cn.com.jnpc.foreign.po.FiResidencePermit;

public interface ResidencePermitDao {
    public String InsertReturnID(String example,FiResidencePermit permit);
    public FiResidencePermit SelectByPrimaryKey(String example,String id);
    public void DeleteByPrimaryKey(String example,String id);
    public FiResidencePermit UpdataReturnObject(String example,FiResidencePermit permit);
}
