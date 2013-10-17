<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html >  
<html >
<head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>left.html</title>
        <link href="<%=basePath%>style/new.css" Rel="stylesheet" Type="text/css">
<meta Http-equiv="Content-type" content="text/html; Charset=UTF-8">
<link rel="stylesheet"
	href="<%=basePath%>style/jquery-ui/base/jquery.ui.all.css">
<script src="<%=basePath%>script/jquery-1.9.1.min.js"></script>
<script src="<%=basePath%>script/ui/jquery.ui.core.js"></script>
<script src="<%=basePath%>script/ui/jquery.ui.widget.js"></script>
<script src="<%=basePath%>script/ui/jquery.ui.accordion.js"></script>
<style type="text/css">
            body {
                font-size: 9pt;
                text-align: left;
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function(){
                $("h3 span").attr("class", "ui-accordion-header-icon ui-icon ui-icon-triangle-1-s");
                $("a").attr("target", "main");
                //初始化列表块
                $("#accordion1").attr("class", "accordion ui-accordion ui-widget ui-helper-reset");
                //初始化样式title
                $("#accordion1 h3").attr("class", "ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-corner-all");
                //初始化样式是否存在下拉框标记
                $("#accordion1 h3 span").attr("class", "ui-accordion-header-icon ui-icon ui-icon-triangle-1-e");
                //列表框样式
                $("#accordion1 ul").attr("class", "accordionContent ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active");
                //隐藏列表框
                $("#accordion1 ul").attr("style", "list-style: none outside none; display: none;");
                //鼠标上移事件.
                $("#accordion1 h3").mouseover(function(e){
                    var temp = $(this).attr("class");
                    if (temp != "ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-accordion-header-active ui-state-active ui-corner-top") {
                        $(this).attr("class", "ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-corner-all ui-state-hover");
                    }
                });
                //鼠标移开事件 如果已经点开.则不变.如果为点开修改
                $("#accordion1 h3").mouseout(function(e){
                    var temp = $(this).attr("class");
                    if (temp != "ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-accordion-header-active ui-state-active ui-corner-top") {
                        $(this).attr("class", "ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-corner-all");
                    }
                });
                //title点击事件 修改title 下拉框标记 以及显示列表框
                $("#accordion1 h3").click(function(e){
                    var temp = $(this).attr("class");
                    //收起
                    if (temp == "ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-accordion-header-active ui-state-active ui-corner-top") {
                        if ($(this).find("span").attr("class")) {
                            $(this).attr("class", "ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-corner-all");
                            $(this).find("span").attr("class", "ui-accordion-header-icon ui-icon ui-icon-triangle-1-e");
                            $(this).next().attr("style", "list-style: none outside none; display: none;");
                        }
                        //显示
                    }
                    else {
                        if ($(this).find("span").attr("class")) {
                            _parent = $(this).parent("div");
                            _parent.children("ul").attr("style", "list-style: none outside none; display: none;");
                            _parent.children("h3").find("span").attr("class", "ui-accordion-header-icon ui-icon ui-icon-triangle-1-e");
                            _parent.children("h3").attr("class", "ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-corner-all");
                            $(this).attr("class", "ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-accordion-header-active ui-state-active ui-corner-top");
                            $(this).find("span").attr("class", "ui-accordion-header-icon ui-icon ui-icon-triangle-1-s");
                            $(this).next().removeAttr("style");
                        }
                    }
                });
            });
        </script>
    </head>
    <body style="overflow: scroll;overflow-x:hidden;">
        <div style="width:98%;">
            <h3>&nbsp;&nbsp;专家请进</h3>
            <div id="accordion1" style="width:98%;">
                <h3><span ></span>信息录入</h3>
                <ul style="list-style: none;">
                    <li>
                        <a href="<%=basePath%>foreign/foreign_info.html" target="main">专家信息录入</a>
                    </li>
                    <li>
                        <a href="<%=basePath%>invitation/invitation_info.html" target="main">邀请函信息录入</a>
                    </li>
                </ul>
                <h3><span ></span>信息维护</h3>
                <ul style="list-style: none;">
                    <li>
                        <a href="<%=basePath%>foreign/foreign_edit.html" target="main">专家信息维护修改</a>
                    </li>
                    <li>
                        <a href="<%=basePath%>invitation/invitation_edit.html" target="main">邀请函信息维护修改</a>
                    </li>
                    <li>
                        <a href="<%=basePath%>foreign/foreign_inout.html" target="main">专家出入境信息填写</a>
                    </li>
                    <li>
                        <a href="/Demon/Is_here.html" target="main">是否在连维护</a>
                    </li>
                    <li>
                        <a href="#" target="main">邀请函启用操作</a>
                    </li>
                    <li>
                        <a href="/Demon/Visa_extension.html" target="main">签证延期</a>
                    </li>
                </ul>
                <h3><span ></span>信息查询</h3>
                <ul style="list-style: none;">
                    <li>
                        <a href="/Demon/foreign_Q.html" target="main">专家信息查询</a>
                    </li>
                    <li>
                        <a href="/Demon/invitation_Q.html" target="main">邀请函信息查询</a>
                    </li>
					<li>
                        <a href="/Demon/inout_Q.html" target="main">专家出入境信息查询</a>
                    </li>
                </ul>
				
                <h3><span ></span>信息统计</h3>
                <ul style="list-style: none;">
                    <li>
                        <a href="#" target="main">专家引进情况统计</a>
                    </li>
                </ul>
                <h3>证件失效人员名单</h3>
            </div>
            <div style="height: 27px;width: 97%;margin-top: 2px;text-align: center;" class="ui-accordion-header ui-helper-reset ui-state-default ui-corner-all ui-accordion-icons">
                <a href="Logout" style="vertical-align: middle;"><!-- <img src="images/quit.gif" alt="" border="0" height="20px;" /> -->
                    <font size="4">
                        <b>退出</b>
                    </font>
                </a>
            </div>
        </div>
    </body>
</html>