<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>纳税咨询管理</title>
</head>
<body class="rightBody">
<form id="form" name="form" action="" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>纳税咨询管理</strong>&nbsp;-&nbsp;纳税咨询</div></div>
    <div class="tableH2">咨询详细信息<span style="color:red;">(已处理)</span></div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
    	<tr><td colspan="2" align="center">咨询人信息</td></tr>              
        <tr>
            <td class="tdBg">咨询人单位：</td>
            <td><s:property value=""/></td>
        </tr>
        <tr>
            <td class="tdBg">咨询人姓名：</td>
            <td><s:property value=""/></td>
        </tr>
        <tr>
            <td class="tdBg">咨询人手机：</td>
            <td>
            <s:property value=""/>
            </td>
        </tr>
        <tr><td colspan="2" align="center">咨询信息</td></tr>
        <tr>
            <td class="tdBg">咨询时间：</td>
            <td></td>
        </tr>      
        <tr>
            <td class="tdBg">咨询标题：</td>
            <td><s:property value=""/></td>
        </tr>
        <tr>
            <td class="tdBg">咨询内容：</td>
            <td><s:property value=""/></td>
        </tr>
        <tr><td colspan="2" align="center">处理信息</td></tr>
        
        <tr>
            <td class="tdBg">回复部门：</td>
            <td>
            xxx
            <s:hidden name="" value=""/>
            </td>
        </tr>
        <tr>
            <td class="tdBg">回复人：</td>
            <td>
            xxx
            
            </td>
        </tr>

        <tr>
            <td class="tdBg" width="200px">回复内容：</td>
            <td><s:textarea name="" cols="90" rows="8" /></td>
        </tr>
    </table>
    
    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>