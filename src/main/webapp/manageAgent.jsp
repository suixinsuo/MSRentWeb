<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/skin_/main.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.dialog.css" />
<link rel="stylesheet" type="text/css" href="css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="css/skin_/nav.css" />
<link rel="stylesheet" type="text/css" href="css/skin_/table.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.grid.css" />
<link rel="stylesheet" type="text/css" href="css/skin_/form.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<link href="umeditor/themes/default/_css/umeditor.css" type="text/css" rel="stylesheet">
<title>365后台管理系统</title>
</head>
<body>
<!--新增经纪人页面 -->
<div id="container">
	<!--引用顶页  -->
	<%@ include file="top.jsp"%>

	<!--左边菜单栏 -->
	<%@ include file="nav.jsp"%>
	
	<div class="iframe-box">
    	<div id="main">
        	<div class="search-box ue-clear">
            	<div class="search-area">
                    
                    <div class="kv-find-item ue-clear">
                        <label>手机号:</label>
                        <div class="kv-small-content">
                        	<input type="text"/>
                        </div>
                    </div>
                    <div class="kv-find-item ue-clear">
                        <label>用户姓名:</label>
                        <div class="kv-small-content">
                        	<input type="text"/>
                        </div>
                    </div>
                    <div class="kv-find-item ue-clear">
                        <label>用户类型:</label>
                        <div class="kv-small-content">
                        	<select>
                                <option value="0">游客</option>
                                <option value="1" selected="selected">经纪人</option>
                                <option value="2">信息员</option>
                                <option value="3">代理公司</option>
                                <option value="4">物业服务站</option>
                                <option value="5">合伙人</option>
                            </select>
                        </div>
                    </div>
                    
                </div>
                <div class="search-button">
                	<input class="button" type="button" value="搜索一下" />
                </div>
             </div>
             
            <div class="table">
            	<div class="opt ue-clear">
                	<span class="optarea">
                        <a href="javascript:;" class="add">
                            <i class="icon"></i>
                            <span class="text">添加</span>
                        </a>
                        <a href="javascript:;" class="config">
                            <i class="icon"></i>
                            <span class="text">编辑</span>
                        </a>
                        <a href="javascript:;" class="delete">
                            <i class="icon"></i>
                            <span class="text">删除</span>
                        </a>
                    </span>
                </div>
                <div class="grid-table">
	                <table>
	                	<thead>
		                	<tr>
		                		<td width=60>选择</td>
		                		<td>手机号</td>
		                		<td>用户姓名</td>
		                		<td>用户类型</td>
		                		<td>注册日期</td>
		                		<td>状态</td>
		                	</tr>
	                	</thead>
						<tbody>
							<c:forEach items="${userList}" var="user">
								<tr>
									<td><input type="checkbox"></td>
									<td>${user.id}</td>
									<td>${user.name}</td>
									<td>
										<c:choose> 
											<c:when test="${user.type eq 0}">游客</c:when>
											<c:when test="${user.type eq 1}">经纪人</c:when>
											<c:when test="${user.type eq 2}">信息员</c:when>
											<c:when test="${user.type eq 3}">代理公司</c:when>
											<c:when test="${user.type eq 4}">物业服务站</c:when>
											<c:when test="${user.type eq 5}">合伙人</c:when>
											<c:when test="${user.type eq 6}">管理员</c:when>
											<c:otherwise>其他</c:otherwise>
										</c:choose>
									</td>
									<td>${user.createDate}</td>
									<td>
										<c:choose> 
											<c:when test="${user.status eq 0}">未审核</c:when>
											<c:when test="${user.status eq 1}">正常</c:when>
											<c:when test="${user.status eq 9}">注销</c:when>
											<c:otherwise>其他</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</tbody>
	                </table>
                </div>
                
                <div class="pagination"></div>
            </div>
    </div>
    </div>
</div>
<div class="mask-bg" style="display:none">
	<div class="mask-wall">
		<div class="mask-title">编辑</div>
		<div class="mask-close">X</div>
		<form>
           <div class="kv-mask-item ue-clear">
                <label>手机号:</label>
                <div class="kv-mask-content">
                	<input type="text"/>
                </div>
            </div>
            <div class="kv-mask-item ue-clear">
                <label>手机号:</label>
                <div class="kv-mask-content">
                	<input type="text"/>
                </div>
            </div>
             <div class="kv-mask-item ue-clear">
                <label>手机号:</label>
                <div class="kv-mask-content" >
                	<select>
                         <option value="0">游客</option>
                         <option value="1" selected="selected">经纪人</option>
                         <option value="2">信息员</option>
                         <option value="3">代理公司</option>
                         <option value="4">物业服务站</option>
                         <option value="5">合伙人</option>
                     </select>
                </div>
            </div>
            
            <div class="kv-mask-item ue-clear">
                <label>手机号:</label>
                <div class="kv-mask-content" >
                	<select>
                         <option value="0">游客</option>
                         <option value="1" selected="selected">经纪人</option>
                         <option value="2">信息员</option>
                         <option value="3">代理公司</option>
                         <option value="4">物业服务站</option>
                         <option value="5">合伙人</option>
                     </select>
                </div>
            </div>

		</form>
	</div>
</div>

<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/core.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<!-- <script type="text/javascript" src="js/jquery.grid.js"></script> -->
<script type="text/javascript" src="js/WdatePicker.js"></script>
<script type="text/javascript">
// 	$(".").click(function(){
// 		$(".mask-bg").show();
// 	})

</script>

</body>
</html>