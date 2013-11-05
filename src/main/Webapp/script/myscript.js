﻿function isValid(form){ 
  if(form.userid.value ==""){
    alert("用户编号不能为空！");
    return false;
  }
  else if(form.userid.value.length != 8){
    alert("用户编号为8位！");
    return false;
  }
  else if(form.passwd.value.length == 0){
    alert("请输入密码！");
    return false;
  }
  else {
	  form.submit();
    return true;
  }	
  
}
function f_submit(){
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
	if($('select[name="country"]').val()=="1"){
		alert("请选择国籍！");
		return false;
	}
	if($('select[name="company_department"]').val()==""){
		alert("请选择单位！");
		return false;
	}
	if($('input[name="passport_id"]').val()==""){
		alert("请输入护照！");
		return false;
	}
	if($('input[name="passport_exp_date"]').val()==""){
		alert("请选择护照有效期！");
		return false;
	}
	if($('input[name="pp_attachment"]').val()==""){
		alert("请选择扫描文件上传！");
		return false;
	}
	if($(':radio[name="expert_evidence"]:checked').val()=="1"){
		if($('input[name="ee_attachment"]').val()==""){
			alert("请输入护照扫描件！");
			return false;
		}
	}
	if($('select[name="residence_permit_kind"]').val()!="0"){
		if($('input[name="rp_exp_endDate"]').val()==""){
			alert("请选择签证有效期！");
			return false;
		}
		if($('select[name="_Address"]').val()=="0"){
			alert("请选择居留地址！");
			return false;
		}
		if($("#address").val()==""){
			alert("请输入具体地点！");
			return false;
		}
	}
		$("#form1").submit();
}
function f_subinv(){
	if($('input[name="invitation_id"]').val()==""){
		alert("邀请函号不能为空！");
		 return false;
	}
	if($('input[name="attachment"]').val()==""){
		alert("请选择邀请函！");
		 return false;
	}
	var month=$('input[name="month"]').val();
	if(month==""){
		alert("请输入的月份！");
		 return false;
	}
	if(isNaN(month)){
		alert("请输入数字!");
		return false;
	}
	if(parseInt(month)>12||parseInt(month)<=0){
		alert("请输入正确的月份");
		return false;
	}
	var day=$('input[name="day"]').val();
	if(day==""){
		alert("请输入的日期！");
		 return false;
	}
	if(isNaN(day)){
		alert("请输入数字!");
		return false;
	}
	if(parseInt(day)>31||parseInt(day)<=0){
		alert("请输入正确的日期的！");
		return false;
	}
	if($(':radio[name="goback_times"]:checked').val()==undefined){
		alert("请选择来往次数！");
		 return false;
	}
	if($('input[name="arrived_date"]').val()==""){
		alert("请选择拟入境日期！");
		return false;
	}
	if($('input[name="leaving_date"]').val()==""){
		alert("请选择拟离境日期！");
		return false;
	}
	$("#form1").submit();
}
