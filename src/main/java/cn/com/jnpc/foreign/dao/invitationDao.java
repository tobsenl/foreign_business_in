package cn.com.jnpc.foreign.dao;

import java.util.List;

import cn.com.jnpc.foreign.model.FiInvitationExample;
import cn.com.jnpc.foreign.po.FiInvitation;
import cn.com.jnpc.foreign.vo.PageMybatis;

public interface invitationDao {
    public String InsertReturId(String example,FiInvitation invitation);
    public List<FiInvitation> SelectByExample(String example,FiInvitationExample invitation);
    public List<FiInvitation> SelectAll(String example,PageMybatis page);
    public FiInvitation SelectById(String example,int id);
    public FiInvitation UptataReturnObj(String example,FiInvitation invitation);
}