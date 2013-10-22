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

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta Http-equiv="Content-type" content="text/html; Charset=UTF-8">

<link href="<%=basePath%>style/new.css" Rel="stylesheet" Type="text/css">

<script src="<%=basePath%>script/jquery-1.9.1.min.js"></script>
<script src="<%=basePath%>/script/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	text-align: center;
	position: relative;
}

.table {
	width: 100%;
	height: auto;
	position: relative;
}

.row {
	width: 70%;
	position: relative;
	float: left;
	line-height: 35px;
	height: 35px;
	overflow: hidden;
	padding-left: 30%;
}

.Crow {
	width: 100%;
	position: relative;
	float: left;
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

.colspan {
	float: left;
	width: 38%;
}

.button {
	width: 100%;
	float: left;
	height: 30px;
	text-align: center;
}

.Crow .cols1 {
	width: 40%;
	float: left;
}

.Crow .cols2 {
	width: 60%;
	float: left;
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		//style="display: none;"
		//${message}
		
		$("#upload_ee").css("display","none");
		$("input[name=expert_evidence][value=0]").attr("checked",'checked');
		$("input[name=expert_evidence]").change(function(){
			var temp_radio=$("input[name=expert_evidence]:checked").val();
			if(temp_radio == "1"){
				$("#upload_ee").css("display","");
			}else{
				$("#upload_ee").css("display","none");
			}
		});
		
		$("#rp_div").css("display","none");
		$("#residence_permit_kind").change(function(){
			var kind_id=$("#residence_permit_kind  option:selected").val();
			//alert(kind_id);
			if(kind_id>0){
				$("#rp_div").css("display","");
			}else{
				$("#rp_div").css("display","none");
				$("[name='rp_exp_endDate']").val("");
				$("#_Address").val("0");
				$("#address").val("");
				$("#rp_Address").val("");
			}
		});
		
		$("#address").blur(function(){
			var temp_address=$("#_Address  option:selected").text()+","+$("#address").val();
			//alert(temp_address);
			$("#rp_Address").val(temp_address);
		});
		$("#_Address").change(function (){
				var temp_address=$("#_Address  option:selected").text()+","+$("#address").val();
				//alert(temp_address);
				$("#rp_Address").val(temp_address);
		});
		
			$.get("<%=basePath%>index/country.xml",function(y){
				var contrylist=$(y).find("country");
				if(contrylist.length > 0){
					for(var i=0;i<contrylist.length;i++){
						var o=contrylist[i];
						var id=$(o).find("id").text();
						var name=$(o).find("name").text();
						var relname=$(o).find("relname").text();
						$("#country").children().last().after("<option value='"+id+"'>"+relname+"("+name+")</option>")
					}
				}
			});
			$.get("<%=basePath%>index/place.xml",function(y){
				var contrylist=$(y).find("address");
				if(contrylist.length > 0){
					for(var i=0;i<contrylist.length;i++){
						var o=contrylist[i];
						var id=$(o).find("id").text();
						var name=$(o).find("name").text();
						var detail=$(o).find("detail").text();
						$("#_Address").children().last().after("<option value='"+id+"'>"+name+"</option>")
					}
				}
			});
			$.get("<%=basePath%>index/permit_kind.xml",function(y){
				var contrylist=$(y).find("kind");
				if(contrylist.length > 0){
					for(var i=0;i<contrylist.length;i++){
						var o=contrylist[i];
						var id=$(o).find("id").text();
						var name=$(o).find("name").text();
						$("#residence_permit_kind").children().last().after("<option value='"+id+"'>"+name+"</option>")
					}
				}
			});
			$.get("<%=basePath%>index/Company3th.xml",function(y){
				var contrylist=$(y).find("company");
				if(contrylist.length > 0){
					for(var i=0;i<contrylist.length;i++){
						var o=contrylist[i];
						var id=$(o).find("id").text();
						var name=$(o).find("name").text();
						$("#company_department").children().last().after("<option value='"+id+"'>"+name+"</option>")
					}
				}
			});
	});
</script>
</head>
<body>
	<div class="container" style="position: relative;">
		<jsp:include page="/index/top.jsp" />
	<c:if test="${return_info == '成功创建!' }" >
		<script type="text/javascript">
			if(!confirm("外籍人员信息成功创建！是否继续新建外籍人员信息？")){
				self.location="<%=basePath%>/index/main.jsp";
			}
		</script>
	</c:if>
	<c:if test="${return_info == '新建失败!' }" >
		<script type="text/javascript">
			if(!confirm("外籍人员信息新建失败！是否重新输入?")){
				self.location="<%=basePath%>/index/main.jsp";
			}
		</script>
	</c:if>
		<form id="form1" name="timeForm" method="post"
			action="foreign/foreign_add.html" enctype="multipart/form-data">
			<div class="table">
				<div class="rowh1">外籍专家信息录入</div>
				<br />
				<div class="row">
					<div class="cols1">
						姓 名(英文)<label style="color: red;">*</label>
					</div>
					<div class="cols2">
						<input type="text" id="name" name="name">
					</div>
				</div>
				<div class="row">
					<div class="cols1">性 别<label style="color: red;">*</label></div>
					<div class="cols2">
						<input type="radio" name="sex" value="1">男&nbsp;<input type="radio"
							name="sex" value="0">女
					</div>
				</div>
				<div class="row">
					<div class="cols1">
						出生日期<label style="color: red;">*</label>
					</div>
					<div class="cols2">
						<input class="Wdate" type="text" name="birthday"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" />
					</div>
				</div>
				<div class="row">
					<div class="cols1">
						国 籍<label style="color: red;">*</label>
					</div>
					<div class="cols2">
						<select id="country" name="country">
							<option value="1"></option>
							<!-- 
							<option value="11">American(美国)</option>
							<option value="12">Australia(澳大利亚)</option>
							<option value="13">Russian(俄国)</option>
							 -->
						</select>
					</div>
				</div>
				<div class="row">
					<div class="cols1">单 位<label style="color: red;">*</label></div>
					<div class="cols2">
						<select id="company_department" name="company_department">
                            <option value=""></option>
                        </select>
					</div>
				</div>
				<div class="row">
					<div class="cols1">
						护 照<label style="color: red;">*</label>
					</div>
					<div class="cols2">
						<input type="text" id="passport_id" name="passport_id">
					</div>
				</div>
				<div class="row">
					<div class="cols1">护照有效期至<label style="color: red;">*</label></div>
					<div class="cols2">
						<input class="Wdate" id="et1" type="text" name="passport_exp_date"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" />
					</div>
				</div>
				<div class="row">
					<div class="cols1">职 务</div>
					<div class="cols2">
						<input type="text" id="post" name="post">
					</div>
				</div>
				<div class="row">
					<div class="cols1">身 份</div>
					<div class="cols2">
						<select id="role" id="role" name="role">
							<option value=""></option>
							<option value="1">专家</option>
							<option value="2">配偶</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="cols1">护照扫描件上传<label style="color: red;">*</label></div>
					<div class="cols2">
						<input type="file" id="pp_attachment" name="pp_attachment"> 
						<input type="hidden" id="fk_pp_attachment_id" name="fk_pp_attachment_id">
					</div>
				</div>
				<div class="row">
					<div class="cols1">外国专家证</div>
					<div class="cols2">
						<input type="radio" name="expert_evidence" value="1"/>有&nbsp;<input
							type="radio" name="expert_evidence" value="0">无
					</div>
				</div>
				<div class="row" id="upload_ee">
					<div class="cols1">专家证扫描件上传<label style="color: red;">*</label></div>
					<div class="cols2">
						<input type="file" name="ee_attachment">
						<input type="hidden" id="fk_ee_attachment_id" name="fk_ee_attachment_id">
					</div>
				</div>
				<div class="row">
					<div class="colspan">
						<div class="Crow">
							<div class="cols1">签证类型</div>
							<div class="cols2">
								<select id="residence_permit_kind" name="residence_permit_kind">
									<option value="0"></option>
									<!-- 
									<option value="1">居留许可</option>
									<option value="2">F签证</option>
									<option value="3">Z签证</option>
									 -->
								</select>
							</div>
						</div>
					</div>
				</div>
				<div id="rp_div" >
					<div class="row">
						<div class="colspan">
							<div class="Crow">
								<div class="cols1">签证有限期至<label style="color: red;">*</label></div>
								<div class="cols2">
									<input class="Wdate" id="et2" type="text" name="rp_exp_endDate"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="cols1">居留地址<label style="color: red;">*</label></div>
						<div class="cols2">
							<select id="_Address" name="_Address">
								<option value="0"></option>
								<!-- 
								<option value="1">专家1村</option>
								<option value="2">专家2村</option>
								 -->
							</select>小区<input type="text" id="address"> <input type="hidden"
								id="rp_Address" name="rp_Address" />
						</div>
					</div>
				</div>
				<div class="button">
					<input type="submit" value="提	交"><input type="button"
						value="重	置">
				</div>
				<div class="button"></div>
			</div>
		</form>
	</div>
	<jsp:include page="/index/bottom.jsp" />
</body>
</html>
