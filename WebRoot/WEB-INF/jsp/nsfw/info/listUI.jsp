<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>信息发布管理</title>
    <script type="text/javascript">
        //全选、全反选
		function doSelectAll(){
			$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
		}
		
		//新增
		function doAdd(){
			document.forms[0].action = "${basepath}nsfw/info_addUI.action";
			document.forms[0].submit();
		}
		//编辑
		function doEdit(id){
			document.forms[0].action = "${basepath}nsfw/info_editUI.action?info.infoId="+id;
			document.forms[0].submit();
		}
		//删除
		function doDelete(id){
			document.forms[0].action = "${basepath}nsfw/info_delete.action?info.infoId="+id;
			document.forms[0].submit();
		}
		//批量删除（selectedRow的复选框）传入的是数组
		function doDeleteAll(){
			document.forms[0].action = "${basepath}nsfw/info_deleteSelect.action";
			document.forms[0].submit();
		}
		
		//ajax改变状态栏
		function changeState(infoId,state){
			var $stateColumn = $("#state_"+infoId);
			var $editColumn = $("#span_"+infoId);
			$.ajax({
				url:"${basePath}nsfw/info_changeState.action",
				type:"get",
				data:{"info.infoId":infoId,"info.state":state},/* datadatadatadatadatadatadatadatadatatdadatatdata */
				success:function(msg){
					if(msg == "更新成功"){
						if(state == 1){
							$stateColumn.text("发布");
							$editColumn.html("<a href=\"javascript:changeState('" + infoId + "', 0)\">停用</a>");
						}else{
							$stateColumn.text("停用");
							$editColumn.html("<a href=\"javascript:changeState('" + infoId + "', 1)\">发布</a>");
						}
					}else{
						alert("更新失败");
					}
				},
				error:function(){
					alert("发生错误，修改失败");
				}
			});
		}
		
		
		//配置搜索和分页的请求地址
		var url_list = "${basePath}nsfw/info_listUI.action";
		
    </script>
</head>
<body class="rightBody">
<form name="form1" action="" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>信息发布管理</strong></div> </div>
                <div class="search_art">
                    <li>
                        信息标题：<s:textfield name="info.title" cssClass="s_text" id="infoTitle"  cssStyle="width:160px;"/>
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
                            <td align="center">信息标题</td>
                            <td width="120" align="center">信息分类</td>
                            <td width="120" align="center">创建人</td>
                            <td width="140" align="center">创建时间</td>
                            <td width="80" align="center">状态</td>
                            <td width="120" align="center">操作</td>
                        </tr>
                        <s:iterator value="pageResult.items" status="st">
                            <tr <s:if test="#st.odd"> bgcolor="f8f8f8" </s:if> >
                                <td align="center"><input type="checkbox" name="selectedRow" value="<s:property value='infoId'/>"/></td>
                                <td align="center"><s:property value="title"/></td>
                                <td align="center">
                                	<s:property value="#infoTypeMap[type]"/>	
                                </td>
                                <td align="center"><s:property value="creator"/></td>
                                <td align="center"><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
                                <td align="center" id="state_<s:property value='infoId'/>"><s:property value="state==1?'发布':'停用'"/></td>
                                <td align="center">
                                	<span id="span_<s:property value='infoId'/>" >
                                		<s:if test="%{state == 1}">
                                			<a href="javascript:changeState('<s:property value='infoId'/>',0)">停用</a>
                                		</s:if>
                                		<s:else>
                                			<a href="javascript:changeState('<s:property value='infoId'/>',1)">发布</a>
                                		</s:else>
                                	</span>
                                    <a href="javascript:doEdit('<s:property value='infoId'/>')">编辑</a>
                                    <a href="javascript:doDelete('<s:property value='infoId'/>')">删除</a>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </div>
          <s:hidden name="pageResult.pageSize"/>
        <jsp:include page="/common/pageNavigator.jsp"></jsp:include>
        </div>
    </div>
</form>

</body>
</html>