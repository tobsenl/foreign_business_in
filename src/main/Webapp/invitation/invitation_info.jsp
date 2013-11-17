<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@include file="../index/include.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
		    + request.getServerName() + ":" + request.getServerPort()
		    + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title></title>

<meta Http-equiv="Content-type" content="text/html; Charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="<%=basePath%>script/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="<%=basePath%>script/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<link rel="stylesheet" href="<%=basePath%>style/jquery-ui/base/jquery-ui.css" />
<link href="<%=basePath%>style/new.css" Rel="stylesheet" Type="text/css">

<script src="<%=basePath%>script/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.position.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.menu.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.autocomplete.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath%>script/myscript.js"></script>

<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
}

.table {
	width: 100%;
	height: auto;
}

.row {
	width: 70%;
	position: relative;
	float: left;
	line-height: 35px;
	overflow: hidden;
	padding-left: 30%;
	text-align: left;
}

.rowh1 {
	text-align: center;
	font-size: 36px;
}

.cols1 {
	width: 15%;
	float: left;
	text-align: left;
}

.cols2 {
	width: 85%;
	float: left;
	text-align: left;
}
.cols3{
	width:80%;
	float: left;
	text-align: left;
}
.cols4{
position: relative;
	width:100%;
	text-align: left;
}
.clos5{
position: relative;
	width:95%;
	float: left;
	text-align: left;
}
.clos6{
position: relative;
	width:5%;
	float: left;
	text-align: left;
}
.droplist{
	background-color: red;
	width: 400px;
	height: 400px;
	float: left;
	position: relative;
}
.droplist_row{
float: left;
	position: relative;
	width: 100%;
	line-height: 25px;
	height: 25px;
}
.droplist_col{
	float: left;
	position: relative;
	width: 50%;
	text-align: left;
	
}
.button {
	width: 100%;
	float: left;
	height: 25px;
	text-align: center;
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		var m = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

		$("#day,#month").blur(function(){
			var day=$("#day").val() == 0 ?"":$("#day").val();
			var month=$("#month").val() == 0?"":$("#month").val();
			var time=month+","+day;
			$("#stay_time").val(time);
			var arrived_date=$("input[name='arrived_date']").val();
			getplantime(arrived_date);
		});
		function getplantime(arrived_date){
			var iday=0;
			var imonth=0;
			var month=$("#month").val();
			var day=$("#day").val();
			if(day != "" && typeof(day)!="undefined"){
				iday = parseInt(day);
			}
			if(month != "" && typeof(month)!="undefined"){
				imonth = parseInt(month);
			}
			//选取的年月日
			//alert(arrived_date !="");
		if(arrived_date !=""){
			var timelist=arrived_date.split('-');
			var year_= parseInt(timelist[0]);//年
			var month_= parseInt(timelist[1]);//月
			var day_= parseInt(timelist[2]);//日
			
			if(iday > 0 || imonth > 0){
				if(imonth > 0){
						imonth=month_+imonth;
						var size=imonth/12;
						var t_size_year=0;
						if(size == 1){
							size=0
						}else{
							t_size_year=Math.floor(size);
						}
						year_= year_ + t_size_year;
						month_= imonth - (t_size_year*12);
						if(month_ == 0){
							month_ = imonth;
						}
				}
				if(iday > 0){
					//alert("was into");
					iday=day_+iday;
					var chushu=1;
					if(month_=== 2 && (year_/4==0&&year_/100!=0)||(year_/400==0)){
						chushu=m[month_-1]+1;
					}else{
						chushu=m[month_-1];
					}
					var t_size_day=Math.floor(iday/chushu);
					month_= month_ + t_size_day;
					day_= iday - (t_size_day*chushu);
				}
				$("input[name='leaving_date']").val(year_+"-"+month_+"-"+day_);
			}
		}
		}
		$("input[name='arrived_date']").focus(function(){
			WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d',onpicking:function(dp){
				var arrived_date=dp.cal.getNewDateStr();
				//填写的停留时间
				getplantime(arrived_date);
			}});
		});
        $(function(){
        	//添加元素的删除
    		$(".row").on("click",".cols4",function(e){
    			e=$(e.target);
    			//var numb=$("#numb").val();
    			if( e.attr("class") == "clos6" ){
    				//numb=parseInt(numb)-1;
    				e=$(e).parent();
    				$(e).remove();
    				//$("#numb").val(numb);
    			}
    		});
            
            $("#select_foeign").autocomplete({
                source:function(request,response) {
    				//alert(request);
    				$.getJSON("foreign/AjaxQuery.html?entryValue="+request.term,
    						function(date){
    					if(date){
    						//此处构建数据结构：
    						response(
    						data = $.map(date,function(obj){
    							return {
    								value: request.term,
    								label: " 姓名: "+obj.name+ " 护照号（ "+obj.pp_id+" ）",
    								id: obj.id
    							}
    						})
    						);
    					}
    				});
    				},
                select: function(e,ui){
                	var numb=$("#numb").val();
	   				var obj_row=$(".row").find(".cols4");
	   				var add=1;
	   					if(obj_row.length > 0){
		   					for(var i=0 ; i < obj_row.length ; i++){
		   						var v_id=$(obj_row[i]).find("input").val();
		   						if(v_id == ui.item.id){
		   							add=0;
		   							break;
		   						}
		   					}
	   					}
	   					if(add == 0){
	   						alert("已存在相同外籍人员！请重新选择！");
	   					}else{
	   						numb=parseInt(numb)+1;
	   						$("#showlist").append("<div class='cols4'><input type='hidden' name='foreign_id"+numb+"' value='"+ui.item.id+"' /><div class='clos5'>"+ui.item.label+"</div><div class='clos6'>X</div></div>");
	   						$("#numb").val(numb);
	   					}
   			 	}
            });
        });
        $(".button").on("mouseup","input[type='reset']",function(){
        	$("#showlist .cols4").remove();
        	$("#numb").val("0");
		});
	});
</script>
</head>

<body>
	<div class="container">
		<jsp:include page="/index/top.jsp" />
		<c:choose>
			<c:when test="${return_info == '成功创建!' }" >
		<script type="text/javascript">
			if(!confirm("邀请函信息成功创建！是否继续新建邀请函？")){
				self.location="<%=basePath%>/index/main.jsp";
			}
		</script>
			</c:when>
			<c:when test="${return_info == '新建失败!' }" >
		<script type="text/javascript">
			if(!confirm("邀请函信息新建失败！是否重新输入?")){
				self.location="<%=basePath%>/index/main.jsp";
			}
		</script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript">
				var sx='${return_info}';
				if(sx != "" && sx != null){
					alert(sx);
				}
		</script>
			</c:otherwise>
		</c:choose>
		
		<form action="invitation/invitation_add.html" id="form1" method="post" enctype="multipart/form-data">
		<div class="table">
			<div class="rowh1">邀请函信息录入</div>
			<br />
			<div class="row">
				<div class="cols1">邀请函号<label style="color: red;">*</label></div>
				<div class="cols2">
					<input type="text" name="invitation_id">
				</div>
			</div>
			<div class="row">
				<div class="cols1">邀请函上传<label style="color: red;">*</label></div>
				<div class="cols2">
					<input type="file" name="attachment">
				</div>
			</div>
			<div class="row">
				<div class="cols1">申请签证有效期<label style="color: red;">*</label></div>
				<div class="cols2">
					<input type="text" name="month" id="month" style="text-align: right;">月<input type="text" name="day" id="day" style="text-align: right;">日
					<input type="hidden" name="stay_time" id="stay_time"/>
				</div>
			</div>
			<div class="row">
			<div class="cols1">来往次数<label style="color: red;">*</label></div>
			<div class="cols2">
				<input type="radio" name="goback_times" value="1">一次往返&nbsp;<input
					type="radio" name="goback_times" value="2">两次往返&nbsp;<input type="radio"
					name="goback_times" value="3">多次往返
					</div>
			</div>
			<div class="row">
				<div class="cols1">拟入境日期<label style="color: red;">*</label></div>
				<div class="cols2">
				<!-- 
					<input class="Wdate" id="et1" type="text" name="arrived_date"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" />
						-->
					<input class="Wdate" id="et1" type="text" name="arrived_date" />
				</div>
			</div>
			<div class="row">
				<div class="cols1">拟离境日期<label style="color: red;">*</label></div>
				<div class="cols2">
					<input class="Wdate" id="et1" type="text" name="leaving_date"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" />
				</div>
			</div>
			<div class="row">
				<div class="cols1">外籍人员添加</div>
				<div class="cols2"><input type="text" name="select_foeign" id="select_foeign"/></div>
			</div>
			<div class="row">
				<div class="cols3" id="showlist">所含外籍人员</div>
			</div>
			<div class="button">
				<input type="button" value="提	交" onclick="f_subinv();">&nbsp &nbsp &nbsp<input type="reset"
					value="重	置">
			</div>
		</div>
		<input type="hidden" name="numb" id="numb" value="0"/>
		</form>
	</div>
	<jsp:include page="/index/bottom.jsp" />
	
	
</body>
</html>
