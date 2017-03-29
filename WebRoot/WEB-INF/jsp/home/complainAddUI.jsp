<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
<html>
<head>
    <%@include file="/common/header.jsp"%>
 	<script type="text/javascript" charset="utf-8" src="${basePath}js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}js/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}js/ueditor/lang/zh-cn/zh-cn.js"></script>    <title>我要投诉</title>
</head>
<script type="text/javascript">
	window.UEDITOR_HOME_URL = "${basePath}js/ueditor/";
	var ue = UE.getEditor('editor');
	function getEmp(){
		var dept = $("#toCompDept option:selected").val();
		$.ajax({
			url:"${basePath}sys/home_getEmp2.action",
			type:"post",
			data:{"dept":dept},
			dataType:"json",
			success:function(data){
				if(data!=null&&data != undefined){
					if(data.msg == "success"){
						var compName = $("#toCompName");
						compName.empty();
						$.each(data.list,function(i,n){
							$("#toCompName").append("<option value="+n.name+">"+n.name+"</option>");
						});
					}else{
						alert("获取数据失败");
					}
				}
			},
			error:function(data){
				alert("数据获取失败");
			}
		});
	}
	//ajax实现
	function doSave(){
		$.ajax({
			url:"${basePath}sys/home_save.action",
			type:"post",
			data:$("#form").serialize(),
			success:function(data){
				if(data == "success"){
					alert("投诉成功");
					window.opener.parent.location.reload(true);
					window.close();
				}else{
					alert("投诉失败");
				}
			},
			error:function(){
				alert("投诉失败！");
			}
		});
	}
</script>
<body>
<form id="form" name="form" action="" method="post" enctype="multipart/form-data">
    <div class="vp_d_1">
        <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div class="vp_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>工作主页</strong>&nbsp;-&nbsp;我要投诉</div></div>
    <div class="tableH2">我要投诉</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="250px">投诉标题：</td>
            <td><s:textfield name="comp.compTitle"/></td>
        </tr>
        <tr>
            <td class="tdBg">被投诉人部门：</td>
            <td>
            	<s:select id="toCompDept" name="comp.toCompDept" list="#{'':'请选择','部门a':'部门a','部门b':'部门b'}"
            	 onchange="javascript:getEmp()"></s:select>
            </td>
        </tr>
        <tr>
            <td class="tdBg">被投诉人姓名：</td>
            <td>
            	<select name="comp.toCompName" id="toCompName" style="width:65px"></select>
        	</td>
        </tr>
        <tr>
            <td class="tdBg">投诉内容：</td>
            <td><s:textarea id="editor" name="comp.compComtent" cssStyle="width:90%;height:160px;" /></td>
        </tr>
        <tr>
            <td class="tdBg">是否匿名投诉：</td>
            <td><s:radio name="comp.isAnony" list="#{'false':'非匿名投诉','true':'匿名投诉' }"/></td>
        </tr>
        <s:hidden name="comp.compName" value="%{#session.SYS_USER.name}"></s:hidden>
        <s:hidden name="comp.compDept" value="%{#session.SYS_USER.dept}"></s:hidden>
        <s:hidden name="comp.compPhone" value="%{#session.SYS_USER.mobile}"></s:hidden>
        <s:hidden name="comp.state" value="0"></s:hidden>
       
    </table>

    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="javascript:doSave()"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:window.close()" class="btnB2" value="关闭" />
    </div>
    </div></div>
    <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </div>
</form>
</body>
</html>