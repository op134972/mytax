<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>角色管理</title>
    <script type="text/javascript">
    //编辑
    function doEdit(roleId){
    	document.forms[0].action = "${basePath}nsfw/role_editUI.action?role.roleId="+roleId;
    	document.forms[0].submit();
    }
    //单个删除
    function doDelete(roleId){
    	document.forms[0].action = "${basePath}nsfw/role_delete.action?role.roleId="+roleId;
    	document.forms[0].submit();
    }
    //批量删除   数组已经传入到action中
    function doDeleteAll(){
    	document.forms[0].action = "${basePath}nsfw/role_deleteSelect.action";
    	document.forms[0].submit();
    }
    //添加
    function doAdd(){
    	document.forms[0].action = "${basePath}nsfw/role_addUI.action";
    	document.forms[0].submit();
    }
    
  	//全选、全反选
	function doSelectAll(){
		$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
	}
    </script>
</head>
<body class="rightBody">
<form name="form1" action="" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>角色管理 </strong></div> </div>
                <div class="search_art">
                    <li>
                        角色名称：<s:textfield name="role.name" cssClass="s_text" id="roleName"  cssStyle="width:160px;"/>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    <li style="float:right;">
                        <input type="button" value="新增" class="s_button" onclick="doAdd()"/>&nbsp;
                        <input type="button" value="删除" class="s_button" onclick="doDeleteAll()"/>&nbsp;
                    </li>
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                            <td width="120" align="center">角色名称</td>
                            <td align="center">权限</td>
                            <td width="80" align="center">状态</td>
                            <td width="120" align="center">操作</td>
                        </tr>
                       		<s:iterator value="pageResult.items" status="st">
                            <tr  bgcolor="f8f8f8"  >
                                <td align="center"><input type="checkbox" name="selectedRow" value='<s:property value="roleId"/>'/></td>
                                <td align="center"><s:property value="name"/></td>
                                <td align="center">
                                		<s:iterator value="rolePrivileges">
                                		<!-- privilegeMap已经在域对象中了，找到code对应的中文值并显示 -->
                                			<s:property value="#privilegeMap[id.code]"/>
                                		</s:iterator>
                                </td>
                                <td align="center"><s:property value="state==1?'有效':'无效'"/></td>
                                <td align="center">
                                    <a href="javascript:doEdit('<s:property value="roleId"/>')">编辑</a>
                                    <a href="javascript:doDelete('<s:property value="roleId"/>')">删除</a>
                                </td>
                            </tr>
                           </s:iterator>
                    </table>
                </div>
            </div>
        <jsp:include page="/common/pageNavigator.jsp"></jsp:include>
        </div>
    </div>
</form>
<script type="text/javascript">
	var url_list = "${basePath}nsfw/role_listUI.action";
</script>

</body>
</html>