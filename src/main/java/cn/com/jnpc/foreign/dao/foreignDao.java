package cn.com.jnpc.foreign.dao;

import java.util.List;

import cn.com.jnpc.foreign.model.FiForeignerExample;
import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.vo.PageMybatis;

public interface foreignDao {
    public String InsertReturObject(String example,FiForeigner foreigner);
    public List<FiForeigner> SelectByExample(String example,FiForeignerExample foreigner);
    public List<FiForeigner> SelectByPage(String example,PageMybatis foreigner);
    public FiForeigner SelectById(String example,int id);
    public List<FiForeigner> SelectAll(String example,PageMybatis page);
    public FiForeigner UpdataReturnObject(String example,FiForeigner foreigner);
}
