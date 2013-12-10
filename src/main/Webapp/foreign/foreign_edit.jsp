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
<script src="<%=basePath%>script/jquery.form.js" type="text/javascript"></script>
<!-- dialog -->
<script src="<%=basePath%>script/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.mouse.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.button.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.draggable.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.position.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.resizable.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.dialog.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath%>script/edit.js"></script>

<link href="<%=basePath%>style/new.css" Rel="stylesheet" Type="text/css">
<link rel="stylesheet" href="<%=basePath%>style/jquery-ui/base/jquery-ui.css" />
<link href="<%=basePath%>style/page.css" Rel="stylesheet" Type="text/css">

<script src="<%=basePath%>script/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath%>script/Untils.js""></script>
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
				line-height: 20px;
				float: left;
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
            .buttons{
            	float: center;
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
        </style>
        <script>
        $(document).ready(function(){
        	var permit_kind=null;
        	var company_kind=null;
        	var country_kind=null;
        	
        	var options = {
                 	dataType:  'json', 
                 	success : function(data){
                 		alert(data.message);
                 		window.location.href=window.location.href;
                 	},
                 	beforeSubmit:checksubmit
                 };
        	$("#form1").ajaxForm(options);
        	
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
    			$("#rp_div").css("display","none");
            	$("#upload_ee").css("display","none");
            	$("#ee_url").attr("href","javascript:;");
    			$("#ee_url").html("");
            }
            $(".form").dialog({
                autoOpen: false,
                modal: true,
                width: 700,
                buttons: {
                    确定: function(){
                    	$("#form1").submit();
                        $(this).dialog("close");
                    },
                    取消: function(){
                        $(this).dialog("close");
                    }
                }
            });
            
            $("#row_list").on("click",".edit",function(e){
            	var e=$(e.target);
            	var id=$(e).find("input[type='hidden']").val();
            	
            	$.getJSON("<%=basePath%>foreign/AjaxQuery_detail.html?foreign_id="+id,function(data){
            		if(data){
            			var a=data[0];
            			clearform();
            			$("#name").val(a.name);
            			$("#id").val(a.id);
            			$("input[name='sex'][value='"+a.sex+"']").prop("checked",true);
            			$("input[name='birthday']").val(a.birthday);
            			$("#country").val(a.country);
            			$("#company_department").val(a.company_department);
            			$("#passport_id").val(a.passport_id);
            			$("input[name='passport_exp_date']").val(a.passport_exp_date);
            			$("#post").val(a.post);
            			$("#role").val(a.role);
            			$("#fk_pp_attachment_id").val(a.fk_pp);
            			$("#pp_url").attr("href",a.fk_pp_url);
            			$("#pp_url").html("点击此处查看 护照扫描件");
            			if(a.expert_evidence == 1){
                			$("input[name='expert_evidence'][value="+a.expert_evidence+"]").prop("checked",true);
                			$("#upload_ee").css("display","");
                			$("#fk_ee_attachment_id").val(a.fk_ee);
                			$("#ee_url").attr("href",a.fk_ee_url);
                			$("#ee_url").html("点击此处查看 专家证 ");
            			}
            			if(a.rp_kind){
                			$("#residence_permit_kind").val(a.rp_kind);
                			$("#fk_rp_permit_id").val(a.fk_permit);
                			$("#rp_div").css("display","");
                			$("input[name='rp_exp_endDate']").val(a.rp_exp_enddate);
                			if(a.rp_address){
                				var address=a.rp_address.split(',');
                				if(address.length > 1){
                				$("#_Address").find("option:contains('"+address[0]+"')").prop("selected",true);
                				$("#address").val(address[1]);
                				}else if(address.length > 0){
                				$("#_Address").find("option:contains('"+address[0]+"')").prop("selected",true);
                				}
                				$("#rp_Address").val(a.rp_address);
                			}
            			}
            		}
            	});
            	$(".form").dialog("open");
            });
            
            var doptions={
					dataType:  'json',
	          		success : function(datas){
						if(datas){
							alert(datas.message);
							window.location.href=window.location.href;
						}
					}
				};
			$("#foreign_del").ajaxForm(doptions);
            $("#forei_del").focus(function(){
   				var temp=$("input[type='checkbox']:checked");
   				var id="";
   				if(temp){
   					if(temp.length > 0){
   					if(temp.length > 1){
   						$.each(temp,function(x,v){
   							if($(v).val() != "0"){
   		   						id=id+$(v).val()+",";
   							}
   		   				});
						}else if(temp.length > 0){
   						if($(temp).val() != "0"){
   							if($(temp).val() != "0"){
   		   						id=id+$(temp).val()+",";
   							}
   						}
						}
   						if(id != ""){
   		   					$("#delete_id").val(id);
   		   				}
   					}else{
   						alert("请勾选要删除的人员后再尝试！");
   					}
   				}
   			});
            $("#forei_del").click(function(){
            	var temp=$("input[type='checkbox']:checked");
            	if(temp.length > 0 ){
            		if(confirm("确认删除选中的外籍人员信息？")){
					$("#foreign_del").attr("action","<%=basePath%>foreign/foreign_del.html");
					$("#foreign_del").submit();
            		}
				}
            });
            $("#allcheckbox").click(function(){ 
            	$("input:checkbox").prop('checked',this.checked) 
            });
           
           $("#query").click(function(){
        	   var relurl= getUrl("query");
        	   $("#query_form").attr("action",relurl);
        	   $("#query_form").submit();
           });
           $("#clear").click(function(){
        	   $("#query_form").clearForm();
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
						$("#country").children().last().after("<option value='"+id+"'>"+relname+"("+name+")</option>")
						$("#contry_q").children().last().after("<option value='"+id+"'>"+relname+"("+name+")</option>")
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
						$("#residence_permit_kind").children().last().after("<option value='"+id+"'>"+name+"</option>")
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
					
					$("#company_department").children().last().after("<option value='"+id+"'>"+name+"</option>");
					$("#post_q").children().last().after("<option value='"+id+"'>"+name+"</option>");
				}
				j_value=j_value+"]";
				company_kind=eval("("+j_value+")");
				setCompanyDepartment(company_kind);
			}
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
			function getUrl(v){
				var page_url=$("#pageurl").val();
				var url=page_url.split("?");
				var pageurl=url[0].replace("foreign_edit","search_list");
				var attr=url[1].split("&");
				var s=0;
				for(var i=0;i<attr.length;i++){
					if(v=="page"){
						if(attr[i].match("pagesize") != null){
							continue;
						}else if(attr[i].match("nowpage") != null){
							continue;
						}else{
							if(s==0){
								pageurl=pageurl+"?"+attr[i];
							}else if(s > 0){
								pageurl=pageurl+"&"+attr[i];
							}
							s++;
						}
					}else if(v=="query"){
						if(attr[i].match("kind") != null){
							pageurl=pageurl+"?"+attr[i];
						}
					}
				}
				return pageurl;
			}
			function check_submit(e){
				var e=e.target;
				var name=e.nodeName;
				var now_index="";
				if(name == "A"){
					now_index=$(e).attr("name");
				}
				var page_size=$("#pagesize").val();
				var allcount=$("#allcount").val();
				var page_url=$("#pageurl").val();
				var attr=page_url.split("&");
				var pageurl= getUrl("page");
				var allpagesize= getUrl("allpagesize");
				$("#nowpage").val(now_index);
				if(parseInt(page_size) > parseInt(allcount)){
					alert("每页显示数不能超出总数据量！请重新填写每页显示数");
				}else if(parseInt(allpagesize) > parseInt(now_index)){
					alert("已经是最后一页!");
				}else{
					$("#page").attr("action",pageurl);
					$("#page").submit();
				}
			}
			$("#pagination-clean").on("click","a",function(e){
				check_submit(e);
			});
			$("#pagination-clean").on("blur","input",function(e){
				check_submit(e);
			});
        });
        </script>
</head>

<body>
	<div class="container">
		<jsp:include page="/index/top.jsp" />
		<div class="main">
            <center>
                专家信息维护修改
            </center>
            <br/>
            <div class="top">
            <form id="query_form" method="post">
                <div class="query_row">
                    <div class="cols1">
                        姓名
                    </div>
                    <div class="cols2">
                        <input type="text" id="foreign_name" name="foreign_name">
                    </div>
                    </div>
                <div class="query_row">
                    <div class="cols1">
                        护照号码
                    </div>
                    <div class="cols2">
                        <input type="text" id="passport_id_q" name="passport_id_q">
                    </div>
                </div>
                <div class="query_row">
                    <div class="cols1">
                        国籍
                    </div>
                    <div class="cols2">
                        <select id="contry_q" name="contry_q">
                            <option value=""></option>
                        </select>
                    </div>
                    </div>
                <div class="query_row">
                     <div class="cols1">
                        邀请函编号
                    </div>
                    <div class="cols2">
                        <input type="text" id="invitation_numb" name="invitation_numb">
                    </div>
                    
                </div>
                <div class="query_row">
                   <div class="cols1" >
                        单位
                    </div>
                    <div class="cols2">
                        <select id="post_q" name="post_q">
                            <option value=""></option>
                        </select>
                    </div>
                    </div>
                <div class="row_button">
                    <center>
		                <button id="query" type="button">
		                    查询
		                </button>
		                &nbsp; &nbsp; &nbsp;
		                <button id="clear" type="button">
		                    清空
		                </button>
		            </center>
                </div>
                </form>
            </div>
        </div>
		<div class="body">
		<br/>
            <center style="width:100%;float: left;">
                专家信息维护列表
            </center>
            <br/>
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
                        编辑
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
                    	<c:if test='${foreign.role == 2}'>家属</c:if>
                    </div>
                    <div class="cols" style="width: 5%;">
                        <div style="width: 100%;text-align: center;" class="edit">
                            edit
                            <input type="hidden" value="${foreign.id}">
                        </div>
                    </div>
                </div>
            </div>
            </c:forEach>
            </div>
            <div class="row" style="float: left;width: 10%; height:15px">
            	<form method="post" name="foreign_del" id="foreign_del">
                	<input type="hidden" id="delete_id" name="delete_id"/>
                </form>
                <button type="button" id="forei_del" name="forei_del">
                    批量删除
                </button>
                
            </div>
            <DIV style="float: right;width: 80%;text-align: right; padding-right:5%" >
            	<form id="page" method="post">
	                 <ul id="pagination-clean" >
						<li class="previous-off">总记录数：<i>${page.allcount }</i></li>
						<li class="previous-off">总页数：<i>${page.allpagesize }</i></li>
						<li class="previous-off" style="padding:0 0 5px 6px;">每页显示数：<b><input type="text" id="pagesize" name="pagesize" value="${page.pagesize }" size="1" style="margin:0; padding:0;border:solid 1px #DEDEDE;"></b></li>
						<c:if test="${page.nowpage == 1 }">
						<li class="previous-off">Previous</li>
						</c:if>
						<c:if test="${page.nowpage != 1 }">
						<li class="previous-off"><a href ="javascript: ;" name="1" title="首页">&lt;&lt; Previous</a></li>
						</c:if>
						
						<c:if test="${page.nowpage > 3 }">
							<c:forEach begin="${page.nowpage-2}" end="${page.allpagesize }" var="i">
							<c:if test="${i == page.nowpage}">
								<li class="active" title="当前页">${i}</li>  
							</c:if>
							<c:if test="${i != page.nowpage}">
								<li><a href ="javascript: ;" title="转到第${i}页" name="${i}">${i}</a></li> 
							</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${page.nowpage <= 3 }">
							<c:forEach begin="1" end="${page.allpagesize }" var="i">
								<c:if test="${i == page.nowpage}">
									<li class="active" title="当前页">${i}</li>  
								</c:if>
								<c:if test="${i != page.nowpage}">
									<li><a href ="javascript: ;" title="转到第${i}页" name="${i}">${i}</a></li> 
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${page.allpagesize > page.nowpage}">
						<li class="next"><a href ="javascript: ;" title="第${page.nowpage+1}页" name="${page.nowpage+1}">Next >></a></li>
						</c:if>
						<c:if test="${page.allpagesize <= page.nowpage}">
						<li class="previous-off"> Next </li>
						</c:if>
						<input type="hidden" id="nowpage" name="nowpage" value="${page.nowpage}"/>
					</ul>
				</form>
				<input type="hidden" id="pageurl" name="pageurl" value="${page.pageurl}"/>
				<input type="hidden" id="allcount" name="allcount" value="${page.allcount}"/>
				<input type="hidden" id="allpagesize" name="allpagesize" value="${page.allpagesize}"/>
            </DIV>
        </div>
        <div id="message" style="display:none;">
        </div>
        <div class="form" style="display:none;" title="外籍专家信息修改">
        <form id="form1" name="form1" method="post"
			action="foreign/foreign_updata.html" enctype="multipart/form-data">
                <div class="row_">
					<div class="cols1">
						姓 名(英文)<label style="color: red;">*</label>
					</div>
					<div class="cols2">
						<input type="text" id="name" name="name">
						<input type="hidden" id="id" name="id">
					</div>
				</div>
				<div class="row_">
					<div class="cols1">性 别<label style="color: red;">*</label></div>
					<div class="cols2">
						<input type="radio" name="sex" value="1">男&nbsp;<input type="radio"
							name="sex" value="0">女
					</div>
				</div>
				<div class="row_">
					<div class="cols1">
						出生日期<label style="color: red;">*</label>
					</div>
					<div class="cols2">
						<input class="Wdate" type="text" name="birthday" id="birthday"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" />
					</div>
				</div>
				<div class="row_">
					<div class="cols1">
						国 籍<label style="color: red;">*</label>
					</div>
					<div class="cols2">
						<select id="country" name="country">
							<option value="0"></option>
						</select>
					</div>
				</div>
				<div class="row_">
					<div class="cols1">单 位<label style="color: red;">*</label></div>
					<div class="cols2">
						<select id="company_department" name="company_department">
                            <option value=""></option>
                        </select>
					</div>
				</div>
				<div class="row_">
					<div class="cols1">
						护 照<label style="color: red;">*</label>
					</div>
					<div class="cols2">
						<input type="text" id="passport_id" name="passport_id">
					</div>
				</div>
				<div class="row_">
					<div class="cols1">护照有效期至<label style="color: red;">*</label></div>
					<div class="cols2">
						<input class="Wdate" id="passport_exp_date" type="text" name="passport_exp_date"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" />
					</div>
				</div>
				<div class="row_">
					<div class="cols1">职 务</div>
					<div class="cols2">
						<input type="text" id="post" name="post">
					</div>
				</div>
				<div class="row_">
					<div class="cols1">身 份</div>
					<div class="cols2">
						<select id="role" id="role" name="role">
							<option value=""></option>
							<option value="1">专家</option>
							<option value="2">家属</option>
						</select>
					</div>
				</div>
				<div class="row_">
					<div class="cols1">护照扫描件上传<label style="color: red;">*</label></div>
					<div class="cols2">
						<input type="file" id="pp_attachment" name="pp_attachment"> 
						<input type="hidden" id="fk_pp_attachment_id" name="fk_pp_attachment_id">
					</div>
					<a id="pp_url"  href="javascript:;" target="_blank"></a>
				</div>
				<div class="row_">
					<div class="cols1">外国专家证</div>
					<div class="cols2">
						<input type="radio" name="expert_evidence" value="1"/>有&nbsp;<input
							type="radio" name="expert_evidence" value="0" checked="checked">无
					</div>
				</div>
				<div class="row_" id="upload_ee">
					<div class="cols1">专家证扫描件上传<label style="color: red;">*</label></div>
					<div class="cols2">
						<input type="file" name="ee_attachment" id="ee_attachment">
						<input type="hidden" id="fk_ee_attachment_id" name="fk_ee_attachment_id">
					</div>
					<a id="ee_url"  href="javascript:;" target="_blank"></a>
				</div>
				<div class="row_">
							<div class="cols1">签证类型</div>
							<div class="cols2">
								<select id="residence_permit_kind" name="residence_permit_kind">
									<option value="0"></option>
								</select>
								<input type="hidden" id="fk_rp_permit_id" name="fk_rp_permit_id">
							</div>
							
				</div>
				<div id="rp_div" >
					<div class="row_">
								<div class="cols1">签证有限期至<label style="color: red;">*</label></div>
								<div class="cols2">
									<input class="Wdate" id="rp_exp_endDate" type="text" name="rp_exp_endDate"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" />
								</div>
					</div>
					<div class="row_">
						<div class="cols1">居留地址<label style="color: red;">*</label></div>
						<div class="cols2">
							<select id="_Address" name="_Address">
								<option value="0"></option>
							</select>小区<input type="text" id="address"> <input type="hidden"
								id="rp_Address" name="rp_Address" />
						</div>
					</div>
				</div>
				</form>
        </div>
	</div>
	<jsp:include page="/index/bottom.jsp" />
</body>
</html>
