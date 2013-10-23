package cn.com.jnpc.foreign.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.com.jnpc.foreign.po.FiForeigner;
import cn.com.jnpc.foreign.po.FiInvitation;
import cn.com.jnpc.foreign.po.FiMiddle;
import cn.com.jnpc.foreign.utils.Untils;

public class QueryServices {
    MiddleServices middleservices;
    InvitationServices invitationservices;
    ForeignServices foreignservices;
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
}
