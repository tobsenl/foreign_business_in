package cn.com.jnpc.foreign.dao;

import java.util.List;

import cn.com.jnpc.foreign.model.FiInoutExample;
import cn.com.jnpc.foreign.po.FiInout;

public interface fiInOutDao {
    public FiInout InsertReturObject(String example,FiInout inout);
    public List<FiInout> selectByExample(String example,FiInoutExample inoutexample);
}
