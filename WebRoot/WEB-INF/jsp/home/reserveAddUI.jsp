<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <script type="text/javascript" src="${basePath }js/datepicker/WdatePicker.js"></script>
    <title>我要预约</title>
</head>
<body>
<form id="form" name="form" action="" method="post" enctype="multipart/form-data">
    <div class="vp_d_1">
        <div style="width=1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div class="vp_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>工作主页</strong>&nbsp;-&nbsp;我要预约</div></div>
    <div class="tableH2">我要预约</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td class="tdBg" width="300px">预约事项：</td>
            <s:checkboxlist list=""></s:checkboxlist>
        </tr>
        <tr>
            <td class="tdBg">预约时间：
            </td>
            <td>
<input type="text" class="Wdate" name="reserve.reserveTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '%y-%M-%d %H:%m:%s', maxDate: '2020-12-31 23:59:59' })"/>            
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="300px">预约地点：</td>
            <td>
            	<s:textarea name="reserve.reserveAddress" style="width:500px;height:100px"></s:textarea>
            </td>
        </tr>
        <tr>
            <td class="tdBg">预约说明：</td>
            <td>
            	<s:textfield name="reserve.reserveDecl" style="width:500px"></s:textfield>
            </td>
        </tr>
        <tr>
            <td class="tdBg">预约人：</td>
            <td>
            	<s:property value="#session.SYS_USER.name"/>
            </td>
        </tr>
       
    </table>

    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="window.close()" class="btnB2" value="关闭" />
    </div>
    </div></div>
    <div style="width=1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </div>
</form>
</body>
</html>