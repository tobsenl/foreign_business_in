<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@include file="../index/include.jsp"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'foreign_ishere.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script src="<%=basePath%>script/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>script/jquery.form.js" type="text/javascript"></script>
	<script src="<%=basePath%>script/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="<%=basePath%>script/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script src="<%=basePath%>script/ui/jquery.ui.mouse.js" type="text/javascript"></script>
	<script src="<%=basePath%>script/ui/jquery.ui.button.js" type="text/javascript"></script>
	<script src="<%=basePath%>script/ui/jquery.ui.draggable.js" type="text/javascript"></script>
	<script src="<%=basePath%>script/ui/jquery.ui.position.js" type="text/javascript"></script>
	<script src="<%=basePath%>script/ui/jquery.ui.resizable.js" type="text/javascript"></script>
	<script src="<%=basePath%>script/ui/jquery.ui.dialog.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="<%=basePath%>style/jquery-ui/base/jquery-ui.css" />
	<link href="<%=basePath%>style/page.css" Rel="stylesheet" Type="text/css">
	<link href="<%=basePath%>style/new.css" Rel="stylesheet" Type="text/css">
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
                float: left;
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
		
		.row1 {
		    float: left;
		    position: relative;
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
		.rowb{
            	width: 100%;
            	 position: relative;
                float: left;
            }
            .titleb{
             width: 100%;
              position: relative;
                float: left;
             }
            .contentb{
            width: 100%;
             position: relative;
                float: left;
            }
            .colsb{
             position: relative;
                float: left;
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
			  $("#query").click(function(){
	         	   var relurl= getUrl("query");
	         	   $("#queryform").attr("action",relurl);
	         	   $("#queryform").submit();
	            });
	            $("#clear").click(function(){
	         	   $("#queryform").clearForm();
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

     			$("#form1").find("label").html("");
            	$("#form1").find("a").html("");
            	$("#form1").find("a").attr("href","");
     			$(".rowb").remove();
     			$("#rpExpEnddate_,#rpAddress_,#upload_ee").css("display","none");
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
             var show_list_detail=function(data){
            	 if(data){
            		 //添加详细行
            	 }
             }
             $("#row_list").on("dblclick",".content",function(e){
            	 var e=$(e.target).parent(".content");
            	 var id=$(e).find("input[type='hidden']#id").val();
              	$.getJSON("<%=basePath%>query/query_invitation_list.html?foreign_id="+id,show_list_detail);
             });
             function getdate(t){
            	 return new Date(t.time);
             }
             function addrow(this_,value){
               	 var content="";
               	 content=content+"";
               	 content=content+"<div class='contentb' style='width:100%;'>";
               	 content=content+"<div class='colsb' style='width:20%;'>";
               	 if(value.type == 1){
               	 content=content+"入境";
               	 }else if(value.type == 0){
               	 content=content+"出境";
               	 }else if(value.type == 2){
               	 content=content+"签证延期";
               	 }
               	 content=content+"</div>";
               	 content=content+"<div class='colsb' style='width:40%;'>";
               	 if(value.type != 2){
               		 //alert(value.beginTime);
               		 var date=getdate(value.beginTime);
               		 content=content+date.getFullYear()+"-"+(date.getMonth() + 1)+"-"+(date.getDate());
               	 }else{
               		 var deferdate=getdate(value.endTime);
               		 content=content+"签证延期至"+deferdate.getFullYear()+"-"+(deferdate.getMonth() + 1)+"-"+(deferdate.getDate());
               	 }
               	 content=content+"</div>";
               	 content=content+"<div class='colsb' style='width:30%;'>";
               	 if(value.type == 1){
               		 content=content+value.content;
               	 }
               	 content=content+"</div>";
               	 content=content+"</div>";
               	 $(this_).next().append(content);
                }
                function title(this_,value){
               	 var content="";
               	 content=content+"<div class='rowb' style='width:100%;'>";
               	 content=content+"<div class='titleb' style='width:100%;'>";
               	 content=content+"<div class='colsb' style='width:20%;'>";
               	 content=content+"出入境类型";
               	 content=content+"</div>";
               	 content=content+"<div class='colsb' style='width:40%;'>";
               	 content=content+"时间";
               	 content=content+"</div>";
               	 content=content+"<div class='colsb' style='width:30%;'>";
               	 content=content+"备注";
               	 content=content+"</div>";
               	 content=content+"</div>";
               	 content=content+"</div>";
               	 $(this_).after(content);
               	 addrow(this_,value);
                }
             $("#row_list").on("click",".show",function(e){
             	var e=$(e.target);
             	var id=$(e).find("input[type='hidden']").val();
             	
             	$.getJSON("<%=basePath%>foreign/AjaxQuery_inout.html?foreign_id="+id,function(data){
             		if(data){
             			var a=data[0].foreign;
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
             			if(a.post==""){
            				$("#post_").html("无");
            			}else{
            				$("#post_").html(a.post);
            			}
             			if(a.role == 1){
            				$("#role").html("专家");
            			}else if(a.role == 2){
            				$("#role").html("配偶");
            			}else{
            				$("#role").html("无");
            			}
             			$("#fkPpAttachmentId_").attr("href",a.fk_pp_url);
            			$("#fkPpAttachmentId_").html("请点击此处查看图片");
             			if(a.expert_evidence ==1){
             				$("#expertEvidence_").html("有");
	                			$("#upload_ee").css("display","");
	                			$("#fkEeAttachmentId_").attr("href",a.fk_ee_url);
	                			$("#fkEeAttachmentId_").html("请点击此处查看图片");
             			}else{
             				$("#expertEvidence_").html("无");
             			}
             			$("#exp,#add").css("display","none");
             			if(a.residence_permit_kind){
             				$("#fkRpPermitKind_").html(getmatch(permit_kind,a.residence_permit_kind));
	                			$("#rpExpEnddate_,#rpAddress_").css("display","");
	                			$("#rpExpEnddate_").html(a.rp_exp_endDate);
	                			if(a.rp_Address != ""){
	                				$("#exp,#add").css("display","");
	                				var address=a.rp_Address.split(',');
	                				if(address.length > 1){
	                					$("#rpAddress_").html(address[0]+address[1]);
	                				}else if(address.length > 0){
	                					$("#rpAddress_").html(address[0]);
	                				}
	                			}
             			}else{
             				$("#fkRpPermitKind_").html("无对应签证！");
             			}
             			
             			$.each(data[0].inout_list,function(_dex,_value){
             				/*
             				+","+
             				_value.begintime//出入境时间
             				_value.type//出境入境
             				_value.content//来华任务。只有入境有
             				_value.fk_invitation_id//关联的邀请函
             				*/
             				$("#inout_detail").after("<div>"+_value.begintime+","+_value.type+","+_value.content+","+_value.fk_invitation_id+"</div>");
             				//$("#inout_detail").after("");
             			});
             			
             			var io=$(data[0].inout_list);
             			if(io.length > 0){
             				$("#inoutlist").show();
             				var position_=$("#inoutall");
             				for(var k=0;k<io.length;k++){
             					var j=io[k];
             					if(k==0){
             						title(position_,j);
             					}else{
             						addrow(position_,j);
             					}
             				};
             			}
             		}
             	});
             	$(".form").dialog("open");
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
						$("#contry_q").children().last().after("<option value='"+id+"'>"+relname+"("+name+")</option>")
					}
					j_value=j_value+"]";
					country_kind=eval("("+j_value+")");
					setCountry(country_kind);
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
						$("#post_q").children().last().after("<option value='"+id+"'>"+name+"</option>")
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
				var pageurl=url[0].replace("foreign_foreigninoutquery","search_list");
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
						if(attr[i].match("foreign_name") != null){
							continue;
						}else if(attr[i].match("passport_id_q") != null){
							continue;
						}else if(attr[i].match("contry_q") != null){
							continue;
						}else if(attr[i].match("post_q") != null){
							continue;
						}else if(attr[i].match("invitation_numb") != null){
							continue;
						}else if(attr[i].match("is_here_") != null){
							continue;
						}else{
							if(s==0){
								pageurl=pageurl+"?"+attr[i];
							}else if(s > 0){
								pageurl=pageurl+"&"+attr[i];
							}
							s++;
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
               专家出入境信息查询
            </center>
            <br/>
            <div class="top">
             <form id="queryform" method="post">
                <div class="query_row">
                    <div class="cols1">
                        
                    </div>
                    <div class="cols1">
                        姓名
                    </div>
                    <div class="cols2">
                        <input type="text" id="foreign_name" name="foreign_name">
                    </div>
                    </div>
                <div class="query_row">
                    <div class="cols1">
                        护照
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
                        邀请函
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
                <div class="query_row">
                   <div class="cols1" >
                        是否在连
                    </div>
                    <div class="cols2">
                        <select id="is_here_" name="is_here_">
                            <option value=""></option>
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                    </div>
                    </div>
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
        </div><br />
		<div class="body">
            <center>
                 专家出入境信息查询列表
            </center>
            <br/>
            <div class="row">
                <div class="title ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-corner-all">
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
                    <div class="cols" style="width: 10%;">
                        是否在连
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
                    
                    <div class="cols" style="width: 20%;">
                        ${foreign.name}<input type='hidden' id="id" value="${foreign.id}">
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
                    <div class="cols" style="width: 10%;">
                    <c:if test='${foreign.isHere == null}'>（无对应信息）</c:if>
                        <c:if test='${foreign.isHere == 1}'>在连</c:if>
                        <c:if test='${foreign.isHere == 0}'>不在连</c:if>
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
            <DIV style="float: right;width: 80%;text-align: right;  padding-right:5%"" >
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
        <div class="form" id="formshow" style="display:none;" title="外籍人员信息">
        <form id="form1" method="post"
			action="#">
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
				<div class="row1">
					<label id=""></label>
				</div>
				<div class="row1" style="width: 100%">
					<div class="cols1_">护照扫描件</div>
					<div class="cols2_">
						<a id="fkPpAttachmentId_" href="javascript:;" target="_blank"></a>
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
				<div class="row1">
					<div class="cols1_">外国专家证</div>
					<div class="cols2_">
						<label id="expertEvidence_"></label>
					</div>
				</div>
				<div class="row1" id="upload_ee" style="width: 100%">
					<div class="cols1_">专家证扫描件</div>
					<div class="cols2_">
						<a id="fkEeAttachmentId_" href="javascript:;" target="_blank"></a>
					</div>
				</div>
				<div class="row1">
					<div class="cols1_">签证类型</div>
					<div class="cols2_">
						<label id="fkRpPermitKind_"></label>
					</div>
				</div>
					<div class="row1" id="exp">
								<div class="cols1_">签证有限期至</div>
								<div class="cols2_">
									<label id="rpExpEnddate_"></label>
								</div>
					</div>
					<div class="row1" id="add">
						<div class="cols1_">居留地址</div>
						<div class="cols2_">
							<label id="rpAddress_"></label>
						</div>
					</div>
					<div class="row2" style="width: 100%"  id="inoutlist">
				<div class="cols1_" style="background-color:#BCD2EE;width: 100%;text-align: center;" id="inoutall">出入境信息</div>		
			</div>
				</form>
        </div>
	</div>
	<jsp:include page="/index/bottom.jsp" />
  </body>
</html>
