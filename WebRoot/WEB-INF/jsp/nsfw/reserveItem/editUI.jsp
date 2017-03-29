<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>预约服务管理</title>

    <script>
    	$(document).ready(function(){
    		getEmp();
    	});
    	function getEmp(){
    		var dept = $("#itemDept").val();
    		$.ajax({
    			url:"${basePath}nsfw/reserveItem_getEmp.action?itemDept="+dept,
    			type:"post",
    			data:{},
    			dataType:"json",
    			success:function(data){
    				$("#itemEmp").empty();
    				$.each(data.empList,function(i,n){
    					$("#itemEmp").append("<option value="+n.name+">"+n.name+"</option>");
    				});
		    		var a = "${item.itemEmp}";
		    		$("#itemEmp").val(a);
    			},
    			error:function(){
    				alert("获取部门人员失败！");
    			}
    		});
    	}
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath}nsfw/reserveItem_edit.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>预约事项</strong>&nbsp;-&nbsp;修改事项</div></div>
    <div class="tableH2">修改事项</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
       	<tr>
            <td class="tdBg" width="200px">事项编号：</td>
            <td><s:select name="item.itemNo" list="{'编号1','编号2','编号3','编号4'}"/></td>
            <td class="tdBg" width="200px">事项名称：</td>
            <td><s:textfield name="item.itemName"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">处理部门：</td>
            <td colspan="3"><s:select id="itemDept" name="item.itemDept" onchange="getEmp()" list="#{'':'请选择','部门A':'部门A','部门B':'部门B'}"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">处理人：</td>
            <td colspan="3"><select id="itemEmp" name="item.itemEmp"></select></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td><!-- 有效无效 -->
            <td colspan="3"><s:radio name="item.itemState" list="#{'1':'有效','0':'无效'}"/></td>
        </tr>
        
    </table>
    <s:hidden name="pageNo"/>
    <s:hidden name="pageSize"/>
    <s:hidden name="item.itemId"/>
    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>