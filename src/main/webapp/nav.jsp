<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath(); 
	String servletPath=request.getServletPath().substring(1); 
%>
	<div class = "siderbarBox">
    	<div class="sidebar">
            <h2><a href="javascript:;"><i class="h2-icon" title="切换到树型结构"></i><span>安全管理</span></a></h2>
            <ul class="nav">
                <li class="nav-li current">
                	<a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text">经纪人管理</span></a>
                	<ul class="subnav">
                    	<li <%="".equals(servletPath)?" class=\"subnav-li current\"":"class=\"subnav-li\""%>  href="index.html" data-id="1"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">首页</span></a></li>
                        <li  <%="manageAgent.jsp".equals(servletPath)?" class=\"subnav-li current\"":"class=\"subnav-li\""%> ><a href="web_manager.action" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">经纪人管理</span></a></li>
                        <li  <%="addAgentBefore.jsp".equals(servletPath)?" class=\"subnav-li current\"":"class=\"subnav-li\""%>  ><a href="web_addAgentBefore.action" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">新增经纪人</span></a></li>
<!--                         <li class="subnav-li" data-id="4"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">自定义设置2</span></a></li> -->
                    </ul>
                </li>
                <li class="nav-li current">
                	<a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text">房源管理</span></a>
                    <ul class="subnav">
                    	<li class="subnav-li" data-id="5"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">房源管理</span></a></li>
                        <li class="subnav-li" data-id="6"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">录入房源</span></a></li>
                    </ul>
                </li>
               
            </ul>
            <div class="tree-list outwindow">
            	<div class="tree ztree"></div>
            </div>
        </div>
        </div>
<script type="text/javascript">

</script>