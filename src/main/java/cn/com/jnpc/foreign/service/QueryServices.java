package cn.com.jnpc.foreign.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.po.FiInout;
import cn.com.jnpc.foreign.po.FiInvitation;
import cn.com.jnpc.foreign.po.FiMiddle;
import cn.com.jnpc.foreign.utils.Untils;
import cn.com.jnpc.foreign.vo.foreigner;
@Service("QueryServices")
public class QueryServices {
    MiddleServices middleservices;
    InvitationServices invitationservices;
    ForeignServices foreignservices;
    
    @Resource(name="MiddleServices")
    public void setMiddleservices(MiddleServices middleservices) {
        this.middleservices = middleservices;
    }
    
    @Resource(name="InvitationServices")
    public void setInvitationservices(InvitationServices invitationservices) {
        this.invitationservices = invitationservices;
    }
    
    @Resource(name="ForeignServices")
    public void setForeignservices(ForeignServices foreignservices) {
        this.foreignservices = foreignservices;
    }
    InOutServices inoutservices;
    
    @Resource(name="InOutServices")
    public void setInoutservices(InOutServices inoutservices) {
        this.inoutservices = inoutservices;
    }

    public List<FiInvitation> getInvitationByforeign(String id){
	List<FiInvitation> invitation_list=null;
	if(Untils.NotNull(id)){
	    List<FiMiddle> middle_list=middleservices.QueryByForeign(id);
	    List list_id=new ArrayList();
	    for (int i = 0; i < middle_list.size(); i++) {
		FiMiddle middle=middle_list.get(i);
		String invit_id=middle.getFkInvitationId();
		if(Untils.NotNull(invit_id)){		    
		    list_id.add(invit_id);
		}
	    }
	    return invitationservices.QueryByIdlist(list_id);
	}else{
	    return null;
	}
    }
    public List<FiForeigner> getForeignByinvitation(String id){
	List<FiInvitation> invitation_list=null;
	if(Untils.NotNull(id)){
	    List<FiMiddle> middle_list=middleservices.QueryByInvitation(id);
	    List list_id=new ArrayList();
	    for (int i = 0; i < middle_list.size(); i++) {
		FiMiddle middle=middle_list.get(i);
		String foreign_id=middle.getFkPersonId();
		if(Untils.NotNull(foreign_id)){		    
		    list_id.add(foreign_id);
		}
	    }
	    return foreignservices.QueryByIdlist(list_id);
	}else{
	    return null;
	}
    }
    public foreigner getforeignQueryByid(String id,HttpServletRequest request){
	if(Untils.NotNull(id)){
	    return foreignservices.QueryByid(request,id);
	}else{
	    return null;
	}
    }
    public FiInvitation getinvitation(String id){
	if(Untils.NotNull(id)){
	    return invitationservices.QueryById(id);
	}else{
	    return null;
	}
    }
    public List<FiInout> getinout(String invit,String foreign){
	if(Untils.NotNull(invit) && Untils.NotNull(foreign)){
	    return inoutservices.QueryByinvitforeign(invit,foreign);
	}else{
	    return null;
	}
    }
    public List<FiForeigner> getforeignbymiddle(FiInvitation invit){
	if(Untils.NotNull(invit)){
	    List<FiInvitation> invitation_list=new ArrayList<FiInvitation>();
	    invitation_list.add(invit);
	    return invitationservices.QueryLinkForeign(invitation_list);
	}else{
	    return null;
	}
    }
    public String getAttachmentPath(HttpServletRequest request,String id){
	return invitationservices.getAttachmentPath(request,id);
    }
}
