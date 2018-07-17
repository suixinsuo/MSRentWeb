<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/skin_/main.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.dialog.css" />
<link rel="stylesheet" type="text/css" href="css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="css/skin_/nav.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<link rel="stylesheet" type="text/css" href="css/skin_/form.css" />
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
            <h2 class="subfild">
            	<span>新增用户</span>
            </h2>
            <div class="subfild-content base-info">
            	
            	<div class="kv-item ue-clear">
                	<label>用户类型</label>
                	<div class="kv-item-content">
                    	<select id="type">
                        	<option value="0">游客</option>
                        	<option value="1" selected="selected" >经纪人</option>
                        	<option value="2">信息员</option>
                        	<option value="3">代理公司</option>
                        	<option value="4">物业服务站</option>
                        	<option value="5">合伙人</option>
                        	<option value="6">管理员</option>
                        </select>
                    </div>
<!--                     <span class="kv-item-tip success">成功信息</span> -->
                </div>
                
            	<div class="kv-item ue-clear">
                	<label><span class="impInfo">*</span>用户id</label>
                	<div class="kv-item-content">
                    	<input type="text" placeholder="用户手机号" id="mobile"/>
                    </div>
<!--                     <span class="kv-item-tip">标题字数限制在35个字符</span> -->
                </div>
                <div class="kv-item ue-clear time">
                	<label><span class="impInfo">*</span>用户密码</label>
                	<div class="kv-item-content">
                    	<input type="text" placeholder="用户密码" id="password"/>
                    </div>
                </div>
                
                <div class="kv-item ue-clear">
                	<label><span class="impInfo"></span>用户姓名</label>
                	<div class="kv-item-content">
                    	<input type="text" placeholder="用户姓名" id="name" />
                    </div>
                </div>
                
                <div class="kv-item ue-clear">
                	<label><span class="impInfo"></span>推荐生成码</label>
                	<div class="kv-item-content">
                    	<input type="text" placeholder="4位推荐生成码"  disabled value="${requestScope.linkCode}"/>
                    	<input type="hidden" id="linkCode" value="${requestScope.linkCode}">
                    </div>
                    <span class="kv-item-tip success">由系统生成无法修改</span>
                </div>
                
                <div class="kv-1-3-item ue-clear">
                	<label>房源区域</label>
                	<div class="kv-middle-item-content">
                    	<select id="areaId">
                    		<option value="-1" selected="selected">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="kv-1-3-item ue-clear">
                	<label>商圈</label>
                	<div class="kv-middle-item-content">
                    	<select id="tradeId">
                    		<option value="-1" selected="selected">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="kv-1-3-item ue-clear">
                	<label>小区名称</label>
                	<div class="kv-middle-item-content">
                    	<select id="cooId">
                    		<option value="-1">请选择</option>
                        </select>
                    </div>
                </div>
                
                 <div class="kv-item ue-clear">
                	<label><span class="impInfo"></span>小区名称</label>
                	<div class="kv-item-content">
                    	<input type="text" placeholder="若下拉列表中未选择小区，则为新增小区" id="cooName"/>
                    </div>
                </div>
                
                <div class="kv-item ue-clear">
                	<label><span class="impInfo"></span>小区详细地址</label>
                	<div class="kv-item-content">
                    	<input type="text" placeholder="小区详细地址" id="cooAddress"/>
                    </div>
                </div>
            </div>
            <div class="subfild-content remarkes-info">
            	<div class="kv-item ue-clear">
                	<label><span class="impInfo">*</span>备注说明</label>
                	<div class="kv-item-content">
                    	<textarea placeholder="备注说明（最多100个汉子长度）" id="remark" style="width:800px;height:240px;"></textarea>
                    </div>
                </div>
            </div>
            
            <div class="buttons">
                <input class="button" type="button" value="确认新增" id="addManager"/>
            </div>
        </div>
    </div>
</div>
<!-- <script type="text/javascript" src="js/jquery.js"></script> -->
<script type="text/javascript" src="utilJs/md5.js"></script>
<script type="text/javascript" src="utilJs/common.js"></script>

<script type="text/javascript">
var path="/MSRentWeb";
// var path="";
$(function(){
	//加载区域和商圈数据
	
	$.ajax({
            type: "POST",
            url: path+"/getArea.action",
            data: {},
            dataType: "json",
            success: function(data){
    			var modelList = data.data.areaResponse;
            	if(modelList && modelList.length != 0){
    				for(var i=0; i<modelList.length; i++){
    					var option="<option value=\""+modelList[i].areaId+"\"";
    					option += ">"+modelList[i].areaName+"</option>";  //动态添加数据
    					$("select[id=areaId]").append(option);
    				}
            	}
            },
             error: function(error){
            	 alert('系统错误');
             }
     });
		
	// 加载商圈select
	 $("#areaId").blur(function(){
		 $('#tradeId option').not(":first").remove(); 
		 var areaId = $("#areaId ").val();
		 if (areaId=='-1'){
			 return;
		 }
		 $.ajax({
            type: "POST",
            url: path+"/getTradeArea.action",
            data: {areaId:areaId},
            dataType: "json",
            success: function(data){
    			var modelList = data.data.tradeAreaResponse;
            	if(modelList && modelList.length != 0){
    				for(var i=0; i<modelList.length; i++){
    					var option="<option value=\""+modelList[i].tradeId+"\"";
    					option += ">"+modelList[i].tradeName+"</option>";  //动态添加数据
    					$("select[id=tradeId]").append(option);
    				}
            	}
            },
             error: function(error){
            	 alert('系统错误');
             }
	     });
	 });
	
	// 加载小区id
	 $("#tradeId").blur(function(){
		 $('#cooId option').not(":first").remove(); 
		 var tradeId = $("#tradeId").val();
		 if (areaId=='-1'){
			 return;
		 }
		 $.ajax({
            type: "POST",
            url: path+"/getCooperation.action",
            data: {tradeId:tradeId},
            dataType: "json",
            success: function(data){
    			var modelList = data.data.cooperation;
            	if(modelList && modelList.length != 0){
    				for(var i=0; i<modelList.length; i++){
    					var option="<option value=\""+modelList[i].cooId+"\"";
    					option += ">"+modelList[i].cooName+"</option>";  //动态添加数据
    					$("select[id=cooId]").append(option);
    				}
            	}
            },
             error: function(error){
            	 alert('系统错误');
             }
	     });
	 });
});

	// 新增经纪人
	$("#addManager").click(function(){
		//  
		var mobile = $("#mobile").val();
		var password = hex_md5($('#password').val().trim());
		var name = $("#name").val();
		var type = $("#type").val();     
		var linkCode = $("#linkCode").val();
		var tradeId = $("#tradeId").val();
		var cooId = $("#cooId").val();
		var cooName = $("#cooName").val();
		var cooAddress = $("#cooAddress").val();
		var remark = $("#remark").val();
		if (isBlank(mobile)){
			alert('手机号不能为空');
			return;
		}
		if (isBlank(password)){
			alert('密码不能为空');
			return;
		}
		
		if (type!='0' && type!='2' && type!='6') {
			if (isBlank(tradeId)|| tradeId=='-1'){
				alert('商圈不能为空');
				return;
			}
			// 是否选择新增小区
			if (isBlank(cooId) || cooId=='-1'){
				// cooid空则为新增小区，则小区名称不能为空
				if (isBlank(cooName)){
					alert('小区名称不能为空');
					return;
				}
				if (isBlank(cooAddress)){
					alert('小区详细地址不能为空');
					return;
				}
			}
		}
	
		$.ajax({
            type: "POST",
            url: path + "/web_addManager.action",
            data: 
            {mobile:mobile, password:password,name:name,type:type,linkCode:linkCode,tradeId:tradeId,
            	cooId:cooId, cooName:cooName,cooAddress:cooAddress,remark:remark
            },
            dataType: "json",
            success: function(data) {
            	if (data=='0') {
            		$("#mobile").val('');
            		$('#password').val('');
            		$("#name").val('');
            		$("#linkCode").val('');
            		$("#cooName").val('');
            		$("#cooAddress").val('');
            		$("#tradeId").val('');
            		$("#cooId").val('');
            		alert('添加成功');
            		
                } else if (data=='1') {
                    alert('用户手机号已经注册');	 
                } else {
                	alert('添加失败');
                }
             },
             error: function(error){
            	 alert('系统错误');
             }
       		 });
	});
	

</script>
</body>
</html>