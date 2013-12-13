function checksubmit(){
	if($('input[name="name"]').val()==""){
		alert("名字不能为空！");
		 return false;
	}
	if($(':radio[name="sex"]:checked').val()==undefined){
		alert("请选择性别！");
		 return false;
	}
	if($('input[name="birthday"]').val()==""){
		alert("请选择出生年月！");
		return false;
	}
	if($('select[name="country"]').val()=="0"){
		alert("请选择国籍！");
		return false;
	}
	if($('select[name="company_department"]').val()==""){
		alert("请选择单位！");
		return false;
	}
	if($('input[name="passport_id"]').val()==""){
		alert("请输入护照号码！");
		return false;
	}
	if($('input[name="passport_exp_date"]').val()==""){
		alert("请选择护照有效期！");
		return false;
	}
	if($(':radio[name="expert_evidence"]:checked').val()=="1"){
		if($("#fk_ee_attachment_id").val() == ""){
		if($('input[name="ee_attachment"]').val()==""){
			alert("请输入专家证扫描件！");
			return false;
		}
		}
		if($('select[name="residence_permit_kind"]').val() == null){
			alert("签证类型此时必须为居留许可");
			return false;
		}
		var obj=document.getElementById('residence_permit_kind');
		var text=obj.options[obj.selectedIndex].text;
		if(text != "居留许可"){
			alert("签证类型此时必须为居留许可");
			return false;
		}
	}
	return true;
}
function checkedit(){
	if($('input[name="invitationId"]').val()==""){
		alert("邀请函编号不能为空！");
		 return false;
	}
	
	var day=$('input[name="day"]').val();
	if(day!=""){	
		if(parseInt(day)>31||parseInt(day)<0){
			alert("请输入正确的天数！(最大天数不能超过31)");
			return false;
		}
	}
	if($(':radio[name="gobackTimes"]:checked').val()==undefined){
		alert("请选择来往次数！");
		 return false;
	}
	if($('input[name="arrivedDate"]').val()==""){
		alert("请选择拟入境日期！");
		return false;
	}
	if($('input[name="leavingDate"]').val()==""){
		alert("请选择拟离境日期！");
		return false;
	}
}