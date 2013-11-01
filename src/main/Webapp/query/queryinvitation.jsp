<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@include file="../index/include.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
		    + request.getServerName() + ":" + request.getServerPort()
		    + path + "/";
%>
<jsp:useBean id="now" class="java.util.Date"/>

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
<script src="<%=basePath%>script/ui/jquery.ui.menu.js" type="text/javascript"></script>
<script src="<%=basePath%>script/ui/jquery.ui.autocomplete.js" type="text/javascript"></script>

<link href="<%=basePath%>style/new.css" Rel="stylesheet" Type="text/css">
<link href="<%=basePath%>style/page.css" Rel="stylesheet" Type="text/css">
<link rel="stylesheet" href="<%=basePath%>style/jquery-ui/base/jquery-ui.css" />

<script src="<%=basePath%>script/My97DatePicker/WdatePicker.js" type="text/javascript"></script>


<style>
body{
        		text-align:center;
				padding:0;
				margin:0;
        	}
			.body{
				width:100%;
			}
			.rows{
			width: 100%;
			}
			.rows div{
				margin-top:20px;
				position:relative;
				float:left;
			}
			.colsx1{
				width:13%;
				text-align:right;
			}
			.colsx2{
				width:20%;
				text-align:center;
			}
			.body form{
				margin-left:10%;
				width:900px;
			}
			.button{
				width:60%;
				text-align:right;
			}
			.title {
                width: 100%;
                float: left;
            }
			.row_{
				position: relative;
                float: left;
				width:100%;
                line-height: 25px;
            }
            .cols {
                position: relative;
                float: left;
                width: 12.5%;
            }
			.table_{
				width:100%;
				float:left;
				margin-top:20px;
			}
			.h1{
				text-align:left;
			}
			.list{
				margin-top:5px;
			}
            .row {
	width: 85%;
	position: relative;
	float: left;
	line-height: 35px;
	overflow: hidden;
	padding-left: 15%;
	text-align: left;
}

.rowh1 {
	text-align: center;
	font-size: 36px;
}

.cols1 {
	width: 25%;
	float: left;
	text-align: left;
}

.cols2 {
	width: 75%;
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
.info_row {
                line-height: 25px;
                 margin-left: 10%;
            }
            .info_title,.info_content {
                width: 90%;
                float: left;
            }
            .info_cols {
                position: relative;
                float: left;
                width: 14%;
            }
			.form{
				text-align:center;
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
        </style>
        <script>
        $(document).ready(function(){
        	function getdate(t){
           	 return new Date(t.time);
            }
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
        	function clearform(){
    			$("#invitationDetail").clearForm();
    			$("#showlist").html("");
			}
        	var showforeign=function(data){
        		if(data){
        			clearform();
        			var a=data.foreign;
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
         			$("#fkPpAttachmentId_").html(a.fk_pp_attachment_id);
         			if(a.expert_evidence){
         				$("#expertEvidence_").html("有");
                			$("#upload_ee").css("display","");
                			$("#fkEeAttachmentId_").html(a.fk_ee_attachment_id);
         			}else{
         				$("#expertEvidence_").html("无");
         			}
         			if(a.residence_permit_kind){
                			$("#fkRpPermitKind_").html(getmatch(permit_kind,a.residence_permit_kind));
                			$("#rpExpEnddate_,#rpAddress_").css("display","");
                			$("#rpExpEnddate_").html(a.rp_exp_endDate);
                			if(a.rp_Address != ""){
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
         			
         			$.each(data.inout_list,function(_dex,_value){
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
         			$("#foreign_detail").dialog("open");
        		}else{
        			alert("查询不到匹配数据");
        		}
        	}
        	$("#row_list").on("click","div[name='detail']",function(e){
           	 var e=$(e.target);
           	 var foreign_id=$(e).find(".foreign_id").val();
           	 var invitation_id=$(e).find(".invitation_id").val();
           	 $.getJSON("<%=basePath%>query/query_foreign_detail.html?forei_id="+foreign_id+"&invit_id="+invitation_id,showforeign);
            });
        	function addnext(this_,value){
           	 var content="";
           	 content=content+"";
           	content=content+"<div class='info_content'>";
           	content=content+"<div class='info_cols'>";
           	content=content+value.name+"";
           	content=content+"</div>";
           	content=content+"<div class='info_cols'>";
           	content=content+value.passportId+"";
           	content=content+"</div>";
           	content=content+"<div class='info_cols'>";
           	content=content+getmatch(country_kind,value.country)+"";
           	content=content+"</div>";
           	content=content+"<div class='info_cols'>";
           	
           	var v=getmatch(company_kind,value.companyDepartment+"");
			   if(v.length>9){
				   content=content+v.substring(0,9)+"……";
			   }else{
				   content=content+v;
			   }
           	content=content+"</div>";
           	content=content+"<div class='info_cols'>";
            if(value.role == 1 ){
            	content=content+"专家";
				}else if(value.role == 2){
					content=content+"配偶";
				   }else if(value.role == ""){
					   content=content+"（无对应信息）";
			   }
           	//content=content+(value.role==""?"(无对应信息)":value.role);
           	content=content+"</div>";
           	content=content+"<div class='info_cols'>";
           	content=content+(value.isHere=="1"?"是":"否");
           	content=content+"</div>";
           	content=content+"<div class='info_cols' name='detail'>";
           	content=content+"show";
           	content=content+"<input type='hidden' class='invitation_id' value='"+$(this_).find("input[type='hidden']#id").val()+"'>";
       	 	content=content+"<input type='hidden' class='foreign_id' value='"+value.id+"'>";
       	 	content=content+"</div>";
           	content=content+"</div>";
           	 $(this_).next().append(content);
            }
            function addtitle(this_,value){
           	 var content="";
           	content=content+"<div class='info_row'>";
           	content=content+"<div class='info_title ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-corner-all'>";
       	 	content=content+"<div class='info_cols'>";
           	content=content+"姓名";
           	content=content+"</div>";
           	content=content+"<div class='info_cols'>";
           	content=content+"护照号码";
           	content=content+"</div>";
           	content=content+"<div class='info_cols'>";
           	content=content+"国籍";
           	content=content+"</div>";
           	content=content+"<div class='info_cols'>";
           	content=content+"单位";
           	content=content+"</div>";
           	content=content+"<div class='info_cols'>";
           	content=content+"身份";
           	content=content+"</div>";
           	content=content+"<div class='info_cols'>";
           	content=content+"是否在连";
           	content=content+"</div>";
           	content=content+"<div class='info_cols'>";
           	content=content+"查看";
           	content=content+"</div>";
           	content=content+"</div>";
           	content=content+"</div>";
           	 $(this_).after(content);
           	 addnext(this_,value);
            }
        	var show_list_detail=function(data){
           	 if(data){
           		 //添加详细行
           		var date=data.foreignlist;
	       		 if(date.length > 0){
	       			$.each(date,function(k,b){
	       				if(k==0){
	       					addtitle(e_temp,b);
	       				}else{
	       					addnext(e_temp,b);
	       				}
	       			}); 
	       		 }
           	 }
            }
        	var e_temp=null;
            $("#row_list").on("dblclick",".content",function(e){
             e_temp=null;
             e_temp=$(e.target).parent(".content");
             var x=$(e_temp).next().find(".info_title");
             //alert(x);
             if(x.html()){
            	$(e_temp).next().remove();
             }else{
           	 	var id=$(e_temp).find("input[type='hidden']#id").val();
             	$.getJSON("<%=basePath%>query/query_foreign_list.html?invitation_id="+id,show_list_detail);
             }
            });
        	$("#row_list").on("click",".edit",function(e){
        		var e=$(e.target);
            	var id=$(e).find("input[type='hidden']").val();
            	$.getJSON("<%=basePath%>invitation/AjaxQuery_detail.html?invitation_id="+id,function(data){
            		if(data){
            			clearform();
            			var v=data[0];
            			$("#invitationId").html(v.invitationId);
             			
             			var times=v.stayTime.split(",");
             			if(times.length > 1){
             				$("#stayTime").html(times[0]+"月"+times[1]+"天");
             			}else if(times.length > 0){
             				$("#stayTime").html(times[0]+"月");
             			}
             			if(v.gobackTimes=="1"){
             				$("#gobackTimes").html("一次往返");
             			}else if(v.gobackTimes=="2"){
             				$("#gobackTimes").html("两次往返");
             			}else if(v.gobackTimes=="3"){
             				$("#gobackTimes").html("多次往返");         				
             			}
             			var arrivedDate=getdate(v.arrivedDate);
             			var leavingDate=getdate(v.leavingDate);
             			$("#arrivedDate").html(v.arrivedDate);
             			$("#leavingDate").html(v.leavingDate);
            			if(v.foreign_list !=null && v.foreign_list.length > 0){
            				$.each(eval(v.foreign_list),function(i,foreign){
            					$("#showlist").append("<div class='cols4'><input type='hidden' name='foreign_id"+(i+1)+"' value='"+foreign.id+"' /><div class='clos5'> 姓名: "+foreign.name+ " 护照号（ "+foreign.passportId+" ）</div><div class='clos6'>X</div></div>");
            				})
		   					$("#numb").val(v.foreign_list.length);
            			}
            		}
            	});
        		//加载dialog的内容
				$(".form").dialog("open");
			});
        	
			$(".form").dialog({
				autoOpen: false,
                modal: true,
                width: 700,
                buttons: {
					关闭: function(){
                        $(this).dialog("close");
                    }
                }
			});
			$("#foreign_detail").dialog({
				autoOpen: false,
                modal: true,
                width: 700,
                buttons: {
					关闭: function(){
                        $(this).dialog("close");
                    }
                }
			});
			$(".button").on("click","#query",function(){
				var relurl= getUrl("query");
				//alert(relurl);
	        	$("#queryform").attr("action",relurl);
				$("#queryform").submit();
			});
			$(".button").on("click","#clear",function(){
				$("#foreign_id_q").val("");
				$("#foreign_name_q").val("");
				$("#invitation_id_q").val("");
				$("#indate_q").val("");
				$("input[name='is_use_q']").removeAttr("checked");
				var relurl= getUrl("query");
	        	$("#queryform").attr("action",relurl);
				$("#queryform").submit();
			});
			
			
			function split( val ) {
				return val.split( /,\s*/ );
			}
			function extractLast( term ) {
				return split(term).pop();
			}
			var catchepoor=new Array();
			$("#foreign_name_q").keydown(function (event){
				if ( event.keyCode === $.ui.keyCode.TAB &&
						$( this ).data( "ui-autocomplete" ).menu.active ) {
					event.preventDefault();
				}
			}).autocomplete({
				source: function( request, response ) {
					$.getJSON( "<%=basePath%>foreign/AjaxQuery.html", {
						entryValue: extractLast(request.term) 
					}, function(date){
						if(date){
							response(
							data=$.map(date,function(obj){
								return {
									value: request.term ,
									label: obj.name ,
									id:obj.id
								}
							})
							)
						}
					} );
				},
				select: function( event, ui ) {
					var terms = split( this.value );
					var foreign_id_q=split($("#foreign_id_q").val());
					var flag= 0 ;
					if(foreign_id_q[0] != ""){
						$.each(foreign_id_q,function(a,b){
							if(b != ui.item.id){
								flag+=0;
							}else{
								flag+=1;
							}
						});
					}else{
							flag+=0;
					}
					if(flag <= 0){
						terms.pop();
						foreign_id_q.pop();
						terms.push( ui.item.label );
						foreign_id_q.push( ui.item.id );
						terms.push( "" );
						foreign_id_q.push( "" );
						this.value = terms.join( ", " );
						$("#foreign_id_q").val(foreign_id_q.join( ", " ));
						catchepoor[catchepoor.length]={id:ui.item.id,name:ui.item.label};
					}else{
						alert("请不要选择已拥有的相同数据！");
					}
					//catchepoor[catchepoor.length]={id:ui.item.id,name:ui.item.label};
					return false;
				}
			});
			
			$(".button").on("focus","#query",function(e){
			//$(".button").on("focus","#clear",function(){
				var _now=$("#foreign_name_q").val();
				var terms=split(_now);
				var temp="";
				$.each(terms,function(a,b){
					var flag="";
					console.log(b);
					if(b != ""){
						$.each(catchepoor,function(_index,_obj){
							//console.log(b == _obj.name);
							if(b == _obj.name){
								flag=_obj.id;
							}
						});
					}if(flag != ""){
						temp=temp+flag+",";
					}
				});
				$("#foreign_id_q").val(temp);
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
					var attr=page_url.split("&");
					var pageurl="";
					for(var i=0;i<attr.length;i++){
						if(v=="page"){
							if(attr[i].match("pagesize") != null){
								continue;
							}else if(attr[i].match("nowpage") != null){
								continue;
							}else{
								if(i==0){
									pageurl=pageurl+attr[i].replace("invitation_toquery","search_list");
								}else{
									pageurl=pageurl+"&"+attr[i];
								}
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
							}else{
								if(i==0){
									pageurl=pageurl+attr[i].replace("invitation_toquery","search_list");
									
								}else{
									pageurl=pageurl+"&"+attr[i];
								}
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
					$("#nowpage").val(now_index);
					if(parseInt(page_size) > parseInt(allcount)){
						alert("每页显示数不能超出总数据量！请重新填写每页显示数");
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
		<div class="body">
        	<form id="queryform" name="queryform" method="post">
        	<center>邀请函信息查询</center>
            <div class="rows">
			<div class="colsx1">邀请函ID</div><div class="colsx2"><input type="text" id="invitation_id_q" name="invitation_id_q"></div>
			<div class="colsx1">是否使用</div><div class="colsx2"><input type="radio" name="is_use_q">是<input type="radio" name="is_use_q">否</div>
            <div class="colsx1">拟入境日期</div><div class="colsx2"><input type="text" name="indate_q" id="indate_q" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></div>
			</div>
			<div class="rows" style="float: left;">
			<div class="colsx1" style="width: 13%;">所含外籍人员</div>
			<div class="colsx2" style="width: 85%;">
			<input type="text" id="foreign_name_q" name="foreign_name_q" size="100"/>
			<input type="hidden" id="foreign_id_q" name="foreign_id_q"></div>
			</div>
			<div class="button" style="float: left;position: relative;"><input type="button" id="query" name="query" value="查询">&nbsp; &nbsp; &nbsp;<input id="clear" name="clear" type="button" value="清空"></div>
			</form>
			
			<div class="table_">
				<center>邀请函信息查询列表</center>
				<div class="list">
					<div class="row_">
						<div class="title ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-corner-all">
							<div class="cols">邀请函号码</div>
							<div class="cols">申请签证有效期</div>
							<div class="cols">使用状态</div>
							<div class="cols">往返次数</div>
							<div class="cols">拟入境日期</div>
							<div class="cols">查看</div>
						</div>
					</div>
					<div id="row_list">
					<c:forEach items="${invitation_list}" var="inv">
					<div class="row_">
						<div class="content">
							<div class="cols">${inv.invitationId }<input type="hidden" id="id" value="${inv.id}"></div>
							<div class="cols">
							<c:if test='${fn:substringBefore(inv.stayTime,",") != ""}'>
							${fn:substringBefore(inv.stayTime,",")}个月</c:if>
							<c:if test='${fn:substringAfter(inv.stayTime,",") != ""}'>
							${fn:substringAfter(inv.stayTime,",")}天
							</c:if>
							</div>
							<div class="cols">
							<c:if test='${inv.isUse == 1}'>使用中</c:if>
							<c:if test='${inv.isUse == 0}'>未使用</c:if>
							<c:if test='${inv.isUse == 2}'>已失效</c:if>
							<c:if test='${inv.isUse == null}'>数据缺失</c:if>
							</div>
							<div class="cols">
							<c:if test='${inv.gobackTimes == 1}'>一次往返</c:if>
							<c:if test='${inv.gobackTimes == 2}'>两次往返</c:if>
							<c:if test='${inv.gobackTimes == 3}'>多次往返</c:if>
							</div>
							<div class="cols">
							<fmt:formatDate value="${inv.arrivedDate}" type="date" pattern="yyyy-MM-dd"/>
							</div>
							<div class="cols" style="width: 12.5%;">
							<div style="width: 100%;text-align: center;" class="edit">
                            show
                            <input type="hidden" value="${inv.id}">
                        </div>
                        </div>
						</div>
					</div>
					</c:forEach>
					</div>
            <DIV style="float: right;width: 80%;text-align: right; padding-right:10%;" >
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
						<c:if test="${page.allpagesize <=1 }">
						<li class="previous-off">Next</li>
						</c:if>
						<c:if test="${page.allpagesize > 1 }">
						<li class="next"><a href ="javascript: ;" title="第${page.nowpage+1}页" name="${page.nowpage+1}">Next >></a></li>
						</c:if>
						<input type="hidden" id="nowpage" name="nowpage" value="${page.nowpage}"/>
					</ul>
				</form>
				<input type="hidden" id="pageurl" name="pageurl" value="${page.pageurl}"/>
				<input type="hidden" id="allcount" name="allcount" value="${page.allcount}"/>
            </DIV>
				</div>
			</div>
        </div>
        <div class="form" style="display:none;" title="邀请函信息">
		<form id="invitationDetail" >
		<div class="table">
			<br />
			<div class="row">
				<div class="cols1">邀请函号</div>
				<div class="cols2">
					<label id="invitationId"></label>
				</div>
			</div>
			<div class="row">
				<div class="cols1">邀请函上传</div>
				<div class="cols2">
					<label id="fkattachmentid"></label>
				</div>
			</div>
			<div class="row">
				<div class="cols1">申请签证有效期</div>
				<div class="cols2">
					<label id="stayTime"></label>
				</div>
			</div>
			<div class="row">
				<div class="cols1">来往次数</div>
				<div class="cols2">
					<label id="gobackTimes"></label>
				</div>
			</div>
			<div class="row">
				<div class="cols1">拟入境日期</div>
				<div class="cols2">
					<label id="arrivedDate"></label>
				</div>
			</div>
			<div class="row">
				<div class="cols1">拟离境日期</div>
				<div class="cols2">
					<label id="leavingDate"></label>
				</div>
			</div>
			<div class="row">
				<div class="cols3" id="showlist">所含外籍人员</div>
			</div>
		</div>
		</form>
		</div>
		<div id="foreign_detail" style="display:none;" title="对应外籍人员信息">
        <form id="foreign_form" 
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
					<div class="row1" style="width: 100%">
						<div class="cols1_" style="background-color:#BCD2EE;width: 100%;text-align: center;">出入境信息</div>
						<div class="cols2_">
							<label id="inout_detail"></label>
						</div>
					</div>
				</form>
        	</div>
	</div>
	<jsp:include page="/index/bottom.jsp" />
	
	
</body>
</html>
