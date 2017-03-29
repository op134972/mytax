<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <script type="text/javascript" src="${basePath }js/datepicker/WdatePicker.js"></script>
    
    <title>投诉受理管理</title>
    
</head>
 	<script>
 	function doDeal(compId){
 		document.forms[0].action = "${basePath}nsfw/complain_dealUI.action?complain.compId="+compId;
 		document.forms[0].submit();
 	}
 	function doAnnualStatistic(){
 		document.forms[0].action = "${basePath}nsfw/complain_annualStatisticChartUI.action";
 		document.forms[0].submit();
 	}
    </script>
<body class="rightBody">
<form name="form1" action="" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>投诉受理管理</strong></div> </div>
                <div class="search_art">
                    <li>
                       	投诉标题：<s:textfield name="complain.compTitle" cssClass="s_text"  cssStyle="width:160px;"/>
                    </li>
                    <li>
                       	投诉时间：<s:textfield id="startTime" name="startTime" cssClass="s_text"  cssStyle="width:160px;"
                       	onfocus=
            "WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm',maxDate:'%y-%M-%d',minDate:'1900-01-01'})"/>
                              - 
                             <s:textfield id="endTime" name="endTime" cssClass="s_text"  cssStyle="width:160px;"
                        onfocus=
            "WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm',maxDate:'%y-%M-%d',minDate:'1900-01-01'})"/>
                    </li>
                    <li>
                    <!--  headerKey="" headerValue=" -->
                       	状态：<s:select name="complain.state" list="#complainStateMap" headerKey="" headerValue="请选择"/>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    <li style="float:right;">
                    	<input type="button" value="统计" class="s_button" onclick="doAnnualStatistic()"/>&nbsp;
                    </li>

                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td align="center">投诉标题</td>
                            <td width="120" align="center">被投诉部门</td>
                            <td width="120" align="center">被投诉人</td>
                            <td width="140" align="center">投诉时间</td>
                            <td width="100" align="center">受理状态</td>
                            <td width="100" align="center">操作</td>
                        </tr>
                       <s:iterator value="pageResult.items" status="st">
                            <tr <s:if test="#st.odd"> bgcolor="f8f8f8" </s:if> >
                                <td align="center"><s:property value="compTitle"/></td>
                                <td align="center"><s:property value="toCompDept"/></td>
                                <td align="center"><s:property value="toCompName"/></td>
                                <td align="center"><s:date name="compTime" format="yyyy-MM-dd HH:mm"/></td>
                                <td align="center"><s:property value="#complainStateMap[state]"/></td>
                                <td align="center">
                                    <a href="javascript:doDeal('<s:property value='compId'/>')">受理</a>
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
	var url_list = "${basePath}nsfw/complain_listUI.action";
</script>

</body>
</html>