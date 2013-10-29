<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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

<title>My JSP 'foreign_inout.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=basePath%>style/new.css" Rel="stylesheet" Type="text/css">

<script src="<%=basePath%>script/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.mouse.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.button.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.draggable.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.position.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.resizable.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.dialog.js" type="text/javascript"></script>
<script src="<%=basePath%>script/jquery.form.js" type="text/javascript"></script>
<script src="<%=basePath%>/script/My97DatePicker/WdatePicker.js"></script>

<link rel="stylesheet" href="<%=basePath%>style/jquery-ui/base/jquery-ui.css" />
<link href="<%=basePath%>style/page.css" Rel="stylesheet" Type="text/css">

<style>
            .top {
                height: 80px;
                width: 80%;
                margin-left: 20%;
            }
            
            .query_row {
                line-height: 30px;
                width: 30%;
                position: relative;
                float: left;
            }
            
            .row {
                line-height: 25px;
            }
            
            .cols {
                position: relative;
                float: left;
                text-align: center;
            }
            
            .title {
                width: 100%;
                float: left;
            }
            
            .content {
                width: 100%;
                float: left;
            }
            
            .title .cols {
                font-weight: bold;
            }
            
            .cols div {
                position: relative;
                float: left;
            }
            
            .form {
                width: 100%;
            }
            
            .row_ {
                width: 85%;
                position: relative;
                float: left;
                line-height: 35px;
                overflow: hidden;
                margin-left: 15%;
            }
             .row_button {
                width: 75%;
                position: relative;
                line-height: 15px;
                overflow: hidden;
            }
            .Crow {
                width: 100%;
                position: relative;
                line-height: 35px;
                float: left;
            }
            
            .rowh1 {
                text-align: center;
                font-size: 36px;
            }
            
            .cols1 {
                width: 25%;
                float: left;
            }
            
            .cols2 {
                width: 75%;
                float: left;
            }
            .colspan {
                float: left;
                width: 49%;
            }
            
            .button {
                width: 100%;
                text-align: center;
            }
            
            .Crow > .cols1 {
                width: 43%;
                float: left;
                text-align: left;
            }
            
            .Crow > .cols2 {
                width: 57%;
                float: left;
                text-align: left;
            }
            .row1{
				float:left;
				position:relative;
				width: 50%;
                line-height: 35px;
                overflow: hidden;
			}
            .cols1_ {
                position: relative;
                width: 40%;
                float: left;
                text-align: left;
            }
            
            .cols2_ {
                position: relative;
                width: 60%;
                float: left;
                text-align: left;
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function(){
            	var permit_kind=null;
            	var company_kind=null;
            	var country_kind=null;
            	function getmatch(obj,val){
            		for(var i=0;i<obj.length;i++){
            			val=trim(val);
            			if(obj[i].id==val){
            				return obj[i].name;
            			}
            		}
            	}
            	function trim(str){ //删除左右两端的空格
            	     return str.replace(/(^\s*)|(\s*$)/g, "");
            	}
            	var queryoptions={
             		   dataType:  'json',
             		   success : function(data){
             		   if(data){
             			   if(data.length>0){
             				   $("#row_list").html("");
             				   var inner_html="";
             				  $.each(data,function(x,obj){
 	            				   inner_html=inner_html+"<div class='row'>";
             					   inner_html=inner_html+"<div class='content'>";
             					   inner_html=inner_html+"<div class='cols' style='width: 5%;'>";
             					   inner_html=inner_html+"<input type='checkbox' value='"+obj.id+"'>";
             					   inner_html=inner_html+"</div>";
             					   inner_html=inner_html+"<div class='cols' style='width: 20%;'>";
             					   inner_html=inner_html+ obj.name;
             					   inner_html=inner_html+"</div>";
             					   inner_html=inner_html+"<div class='cols' style='width: 5%;'>";
             					   if(obj.sex == 1){
             					   inner_html=inner_html+"男";
             					   }else{
             					   inner_html=inner_html+"女";
             					   }
             					   inner_html=inner_html+"</div>";
             					   inner_html=inner_html+"<div class='cols' style='width: 15%;'>";
             					   inner_html=inner_html+getmatch(country_kind,obj.country);
             					   inner_html=inner_html+"</div>";
             					   inner_html=inner_html+"<div class='cols' style='width: 10%;'>";
             					  var v=getmatch(company_kind,obj.companydepartment);
	           					   if(v.length>9){
	           						   inner_html=inner_html+v.substring(0,9)+"……";
	           					   }else{
	           						   inner_html=inner_html+getmatch(company_kind,obj.companydepartment);
	           					   }
             					   inner_html=inner_html+"</div>";
             					   inner_html=inner_html+"<div class='cols' style='width: 20%;'>";
             					   inner_html=inner_html+obj.passportid ;
             					   inner_html=inner_html+"</div>";
             					   inner_html=inner_html+"<div class='cols' style='width: 10%;'>";
             					  if(obj.role == 1 ){
                					   inner_html=inner_html+"专家";
                					}else if(obj.role == 2){
              					   	   inner_html=inner_html+"配偶";
              					   }else if(obj.role == null){
             					   	   inner_html=inner_html+"（无对应信息）";
             					   }
             					   inner_html=inner_html+"</div>";
 	            				   
             					   inner_html=inner_html+"<div class='cols' style='width: 5%;' >";
             					   inner_html=inner_html+"<div style='width: 100%;text-align: center;' class='show'>";
             					   inner_html=inner_html+"show";
             					   inner_html=inner_html+"<input type='hidden' value='"+obj.id+"'>";
             					   inner_html=inner_html+"</div>";
             					   inner_html=inner_html+"</div>";
             					  
             					   inner_html=inner_html+"</div>";
             					   inner_html=inner_html+"</div>";
 	            			   });
 	            			   $("#row_list").html("");
 	            			   $("#row_list").html(inner_html);
             			   }else{
             				   alert("未查询到匹配数据！");
             			   }
             		   }
                	}
                };
            	
            	$("#upload_ee").css("display","none");
    			$("#rpExpEnddate_,#rpAddress_").css("display","none");
            	$("#queryform").ajaxForm(queryoptions);
            	
            	var options={
            		dataType:  'json',
            		url:"<%=basePath%>inout/permit_insert.html",
            		success : function(datas){
               			if(datas){
               				alert(datas.message);
               			}
               		}	
            	};
            	
            	$("#extensionform").ajaxForm(options);
                $("#message").dialog({
                    autoOpen: false,
                    modal: true,
                    height: 200,
                    width: 300,
                    buttons: {
                        确定: function(){
                            $(this).dialog("close");
                        },
                        取消: function(){
                            $(this).dialog("close");
                        }
                    }
                });
                function clearform(){
        			$("#form1").clearForm();
        			$("#upload_ee").css("display","none");
        			$("#rpExpEnddate_,#rpAddress_").css("display","none");
                }
                $("#formshow").dialog({
                    autoOpen: false,
                    modal: true,
                    width: 700,
                    buttons: {
                        关闭: function(){
                            $(this).dialog("close");
                        }
                    }
                });
                $("#extension").dialog({
                    autoOpen: false,
                    modal: true,
                    width: 700,
                    buttons: {
                        确定: function(){
                        	$("#extensionform").submit();
                            $(this).dialog("close");
                        },
                        关闭: function(){
                            $(this).dialog("close");
                        }
                    }
                });
                
                $("#row_list").on("click",".show",function(e){
                	var e=$(e.target);
                	var id=$(e).find("input[type='hidden']").val();
                	
                	$.getJSON("<%=basePath%>foreign/AjaxQuery_detail.html?foreign_id="+id,function(data){
                		if(data){
                			var a=data[0];
                			clearform();
                			$("#name_").html(a.name);
                			if(a.sex == 1){
                				$("#sex_").html("男");
                			}else{
                				$("#sex_").html("女");
                			}
                			//$("input[name='sex'][value='"+a.sex+"']").prop("checked",true);
                			$("#birthDay_").html(a.birthday);
                			$("#country_").html(getmatch(country_kind,a.country));
                			$("#companyDepartment_").html(getmatch(company_kind,a.company_department));
                			$("#passportId_").html(a.passport_id);
                			$("#passportExpDate_").html(a.passport_exp_date);
                			$("#post_").html(a.post);
                			if(a.role == 1){
                				$("#role").html("专家");
                			}else if(a.role == 2){
                				$("#role").html("配偶");
                			}else{
                				$("#role").html("无");
                			}
                			$("#fkPpAttachmentId_").html(a.fk_pp);
                			if(a.expert_evidence){
                				$("#expertEvidence_").html("有");
	                			$("#upload_ee").css("display","");
	                			$("#fkEeAttachmentId_").html(a.fk_ee);
                			}else{
                				$("#expertEvidence_").html("无");
                			}
                			if(a.rp_kind){
	                			$("#fkRpPermitKind_").html(getmatch(permit_kind,a.rp_kind));
	                			$("#rpExpEnddate_,#rpAddress_").css("display","");
	                			$("#rpExpEnddate_").html(a.rp_exp_enddate);
	                			if(a.rp_address){
	                				var address=a.rp_address.split(',');
	                				$("#rpAddress_").html(address[0]+address[1]);
	                			}
                			}else{
                				$("#fkRpPermitKind_").html("无对应签证！");
                			}
                		}
                	});
                	$(".form").dialog("open");
                });
                
                
               $("#allcheckbox").click(function(e){
            	   if($(this).attr("checked") == "checked"){
            		   $("input[type='checkbox']").removeAttr("checked");
            	   }else{
            		   $("input[type='checkbox']").attr("checked",'true');
            	   }
               });
               
               $("#clear").click(function(){
            	   $("#queryform").clearForm();
               });
               $.get("<%=basePath%>index/country.xml",function(y){
   				var contrylist=$(y).find("country");
   				if(contrylist.length > 0){
   					var j_value="[";
   					for(var i=0;i<contrylist.length;i++){
   						var o=contrylist[i];
   						var id=$(o).find("id").text();
   						var name=$(o).find("name").text();
   						var relname=$(o).find("relname").text();
   						if(i==contrylist.length-1){
							j_value=j_value+"{'id':'"+id+"','name':'"+name+"'}";
						}else{
							j_value=j_value+"{'id':'"+id+"','name':'"+name+"'}, ";
						}
   						$("#contry").children().last().after("<option value='"+id+"'>"+relname+"("+name+")</option>")
   					}
   					j_value=j_value+"]";
					country_kind=eval("("+j_value+")");
					setCountry(country_kind);
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
   					var j_value="[";
   					for(var i=0;i<contrylist.length;i++){
   						var o=contrylist[i];
   						var id=$(o).find("id").text();
   						var name=$(o).find("name").text();
   						if(i==contrylist.length-1){
							j_value=j_value+"{'id':'"+id+"','name':'"+name+"'}";
						}else{
							j_value=j_value+"{'id':'"+id+"','name':'"+name+"'}, ";
						}
   						$("#residencePermitKind").children().last().after("<option value='"+id+"'>"+name+"</option>")
   					}
   					j_value=j_value+"]";
   					permit_kind=eval("("+j_value+")");
   				}
   			});
   			$.get("<%=basePath%>index/Company3th.xml",function(y){
				var contrylist=$(y).find("company");
				if(contrylist.length > 0){
					var j_value="[";
					for(var i=0;i<contrylist.length;i++){
						var o=contrylist[i];
						var id=$(o).find("id").text();
						var name=$(o).find("name").text();
						if(i==contrylist.length-1){
							j_value=j_value+"{'id':'"+id+"','name':'"+name+"'}";
						}else{
							j_value=j_value+"{'id':'"+id+"','name':'"+name+"'}, ";
						}
						$("#post").children().last().after("<option value='"+id+"'>"+name+"</option>")
					}
					j_value=j_value+"]";
					company_kind=eval("("+j_value+")");
					setCompanyDepartment(company_kind);
				}
			});
   			$("#query").click(function(){
   				$("#queryform").submit();
   			});
	   			$("#extension_write").click(function(){
	   				var temp=$("input[type='checkbox']:checked");
	   				var id="";
	   				var value="";
	   				if(temp){
	   					if(temp.length > 0){
	   						if($(temp).val() != "0"){
	   							var te=$(temp).parent(".cols").next().html();
	   							te=trim(te);
	   							if($(temp).val() != "0"){
	   		   						id=id+$(temp).val()+",";
	   							}if(te != "姓名"){
	   		   						value=value+te+",";
	   							}
	   						}else if(temp.length > 1){
		   						$.each(temp,function(x,v){
		   							var te=$(v).parent(".cols").next().html();
		   							te=trim(te);
		   							if($(v).val() != "0"){
		   		   						id=id+$(v).val()+",";
		   							}if(te != "姓名"){
		   		   						value=value+te+",";
		   							}
		   		   				});
	   						}
	   						if(id != ""){
	   		   					$("#extension_id").val(id);
	   		   				}
	   		   				if(value !=""){
	   		   					$("#extension_pp").html(value);
	   		   				}
	   		   				$("#extension").dialog("open");
	   					}else{
	   						alert("请勾选要修改的人员后再点击尝试！");
	   					}
	   				}
	   				
	   			});
	   			$("#address").blur(function(){
	   				var temp_address=$("#_Address  option:selected").text()+","+$("#address").val();
	   				//alert(temp_address);
	   				$("#rpAddress").val(temp_address);
	   			});
	   			$("#_Address").change(function (){
	   				var temp_address=$("#_Address  option:selected").text()+","+$("#address").val();
	   				//alert(temp_address);
	   				$("#rpAddress").val(temp_address);
	   			});
	   			function setCountry(_kind){
	   	            $.each($("#row_list").find("[title='country']"),function(c,b){
	   	         	  $(b).html(getmatch(_kind,$(b).html())); 
	   	            });
	   			}
				function setCompanyDepartment(_kind){
	   	            $.each($("#row_list").find("[title='companyDepartment']"),function(c,b){
	   	            	var p=getmatch(_kind,$(b).html());
	   	            	if(p.length>9){
	   	            		$(b).html(p.substring(0,9)+"……");
	   	            	}else{
	   	            		$(b).html(getmatch(_kind,$(b).html())); 
	   	            	}
	   	            });
	   			}
            });
        </script>
</head>

<body>
	<div class="container">
		<jsp:include page="/index/top.jsp" />
		<div class="main">
		<form action="<%=basePath%>foreign/AjaxQuery_list.html" id="queryform" method="post">
            <center>
                签证延期维护
            </center>
            <br/>
            <div class="top">
                <div class="query_row">
                    <div class="cols1">
                        姓名
                    </div>
                    <div class="cols2">
                        <input type="text" id="name" name="name">
                    </div>
                    </div>
                <div class="query_row">
                    <div class="cols1">
                        护照
                    </div>
                    <div class="cols2">
                        <input type="text" id="id" name="id">
                    </div>
                </div>
                <div class="query_row">
                    <div class="cols1">
                        国籍
                    </div>
                    <div class="cols2">
                        <select id="contry" name="contry">
                            <option value=""></option>
                        </select>
                    </div>
                    </div>
                <div class="query_row">
                     <div class="cols1">
                        邀请函
                    </div>
                    <div class="cols2">
                        <input type="text" id="numb" name="numb">
                    </div>
                    
                </div>
                <div class="query_row">
                   <div class="cols1" >
                        单位
                    </div>
                    <div class="cols2">
                        <select id="post" name="post">
                            <option value=""></option>
                        </select>
                    </div>
                    </div>
                <div class="row_button">
                    <center>
		                <button id="query" >
		                    查询
		                </button>
		                &nbsp; &nbsp; &nbsp;
		                <button id="clear">
		                    清空
		                </button>
		            </center>
                </div>
            </div>
            </form>
        </div><br />
		<div class="body">
            <center>
               签证延期维护列表
            </center>
            <br/>
            <div class="row">
                <div class="title ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-corner-all">
                    <div class="cols" style="width: 5%;">
                        <input type="checkbox" id="allcheckbox" value="0">
                    </div>
                    <div class="cols" style="width: 20%;">
                        姓名
                    </div>
                    <div class="cols" style="width: 5%;">
                        性别
                    </div>
                    <div class="cols" style="width: 15%;">
                        国籍
                    </div>
                    <div class="cols" style="width: 10%;">
                        单位
                    </div>
                    <div class="cols" style="width: 20%;">
                        护照号码
                    </div>
                    <div class="cols" style="width: 10%;">
                        身份
                    </div>
                    <div class="cols" style="width: 5%;">
                        查看
                    </div>
                </div>
            </div>
            <div id="row_list">
            <c:forEach items="${foreign_list}" var="foreign" >
            <div class="row">
                <div class="content">
                    <div class="cols" style="width: 5%;">
                        <input type="checkbox" value="${foreign.id}">
                    </div>
                    <div class="cols" style="width: 20%;">
                        ${foreign.name}
                    </div>
                    <div class="cols" style="width: 5%;">
                        <c:if test="${foreign.sex == 1}">男</c:if>
                        <c:if test="${foreign.sex == 0}">女</c:if>
                    </div>
                    <div class="cols" style="width: 15%;" title="country">
                        ${foreign.country }
                    </div>
                    <div class="cols" style="width: 10%;" title="companyDepartment">
                        ${foreign.companyDepartment }
                    </div>
                    <div class="cols" style="width: 20%;">
                        ${foreign.passportId }
                    </div>
                    <div class="cols" style="width: 10%;">
                    <c:if test='${foreign.role == null}'>（无对应信息）</c:if>
                       <c:if test='${foreign.role ==1}'>专家</c:if>
                    	<c:if test='${foreign.role == 2}'>配偶</c:if>
                    </div>
                    <div class="cols" style="width: 5%;" >
                        <div style="width: 100%;text-align: center;" class='show'>
                            show
                            <input type="hidden" value="${foreign.id}"/> 
                        </div>
                    </div>
                </div>
            </div>
            </c:forEach>
            </div>
            <div class="row" style="float:left;margin-left:2%;">
                <button id="extension_write">
                   签证延期资料填写
                </button>
            </div>
            <DIV style="float: right;width: 80%;text-align: right;padding-right:5%;" >
	                 <ul id="pagination-clean" >
						<li class="previous-off">总记录数：<i></i></li>
						<li class="previous-off">总页数：<i></i></li>
						<li class="previous-off" style="padding:0 0 5px 6px;">每页显示数：<b><input type="text" id="countpage" value="" size="1" style="margin:0; padding:0;border:solid 1px #DEDEDE;"></b></li>
						<li class="previous-off">«Previous</li>
						<li><a title="转到第1页" href="javascript:;">1</a></li>
						<li class="active" title="当前页"><a href="javascript:;">2</a></li>
						<li><a title="转到第3页" href="javascript:;">3</a></li>
						<li><a title="转到第4页" href="javascript:;">4</a></li>
						<li><a title="转到第5页" href="javascript:;">5</a></li>
						<li><a title="转到第6页" href="javascript:;">6</a></li>
						<li><a title="转到第7页" href="javascript:;">7</a></li>
						<li><a title="转到第8页" href="javascript:;">8</a></li>
						<li class="next"><a href="?page=2">Next »</a></li>
					</ul>
            </DIV>
        </div>
        <div id="message" style="display:none;">
        </div>
        <div class="form" id="formshow" style="display:none;" title="外籍人员信息">
        <form id="form1" name="form1" method="post"
			action="foreign/foreign_inoutUpdata.html">
                <div class="row1">
					<div class="cols1_">
						姓 名(英文)
					</div>
					<div class="cols2_">
						<label id="name_"></label>
					</div>
				</div>
				<div class="row1">
					<div class="cols1_">性 别</div>
					<div class="cols2_">
						<label id="sex_"></label>
					</div>
				</div>
				<div class="row1">
					<div class="cols1_">
						出生日期
					</div>
					<div class="cols2_">
						<label id="birthDay_"></label>
					</div>
				</div>
				<div class="row1">
					<div class="cols1_">
						国 籍
					</div>
					<div class="cols2_">
						<label id="country_"></label>
					</div>
				</div>
				<div class="row1" style="width: 100%">
					<div class="cols1_" style="width: 15%;">单 位</div>
					<div class="cols2_" style="width: 85%;text-align: center;">
						<label id="companyDepartment_"></label>
					</div>
				</div>
				<div class="row1">
					<div class="cols1_">
						护 照
					</div>
					<div class="cols2_">
						<label id="passportId_"></label>
					</div>
				</div>
				<div class="row1">
					<div class="cols1_">护照有效期至</div>
					<div class="cols2_">
						<label id="passportExpDate_"></label>
					</div>
				</div>
				<div class="row1" style="width: 100%">
					<div class="cols1_">护照扫描件</div>
					<div class="cols2_">
						<label id="fkPpAttachmentId_"></label>
					</div>
				</div>
				<div class="row1">
					<div class="cols1_">身 份</div>
					<div class="cols2_">
						<label id="role"></label>
					</div>
				</div>
				<div class="row1">
					<div class="cols1_">职  位</div>
					<div class="cols2_">
						<label id="post_"></label>
					</div>
				</div>
				<div class="row1" id="upload_ee" style="width: 100%">
					<div class="cols1_">专家证扫描件</div>
					<div class="cols2_">
						<label id="fkEeAttachmentId_"></label>
					</div>
				</div>
				<div class="row1">
					<div class="cols1_">外国专家证</div>
					<div class="cols2_">
						<label id="expertEvidence_"></label>
					</div>
				</div>
				<div class="row1">
							<div class="cols1_">签证类型</div>
							<div class="cols2_">
								<label id="fkRpPermitKind_"></label>
							</div>
							
				</div>
					<div class="row1">
								<div class="cols1_">签证有限期至</div>
								<div class="cols2_">
									<label id="rpExpEnddate_"></label>
								</div>
					</div>
					<div class="row1">
						<div class="cols1_">居留地址</div>
						<div class="cols2_">
							<label id="rpAddress_"></label>
						</div>
					</div>
				</form>
        </div>
        <div id="extension" title="出入境登记">
        <form:form method="post" id="extensionform">
            <div class="row">
                <div class="cols11">
                    签证延期人员:
                </div>
                <div class="cols22">
                    <label id="extension_pp"></label>
                    <input type="hidden" id="extension_id" name="extension_id"/>
                </div>
            </div>
            <div class="row">
                <div class="cols11">
                   签证类型
                </div>
                <div class="cols22">
                    <select id="residencePermitKind" name="residencePermitKind">
									<option value="0"></option>
									<!-- 
									<option value="1">居留许可</option>
									<option value="2">F签证</option>
									<option value="3">Z签证</option>
									 -->
					</select>
                </div>
            </div>
            <div class="row">
                <div class="cols11">
                    签证有效期至
                </div>
                <div class="cols22">
                    <input class="Wdate" id="rpExpEnddate" type="text" name="rpExpEnddate"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" />
                </div>
            </div>
            <div class="row">
                <div class="cols11">
         居留地址
                </div>
                <div class="cols22">
                   <select id="_Address" name="_Address">
								<option value="0"></option>
								<!-- 
								<option value="1">专家1村</option>
								<option value="2">专家2村</option>
								 -->
				   </select>小区<input type="text" id="address"> <input type="hidden"
								id="rpAddress" name="rpAddress" />
                </div>
            </div>
            </form:form>
        </div>
	</div>
	<jsp:include page="/index/bottom.jsp" />
</body>
</html>
