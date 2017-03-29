<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>

	<script type="text/javascript">
		//搜索
		function doSearch(){
			$("#pageNo").val(1);
			document.forms[0].action = url_list;
			document.forms[0].submit();
		};
		//翻页
		function doGoPage(page){
			$("#pageNo").val(page);
			document.forms[0].action = url_list;
			document.forms[0].submit();
		}
	</script>
  <div class="c_pate" style="margin-top: 5px;">
        <s:if test="%{pageResult.totalCount>0}">
		<table width="100%" class="pageDown" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td align="right">
				    每页显示：<s:select list="#{3:3,5:5,7:7,10:10}" name="pageSize"/>
                 	总共<s:property value="pageResult.totalCount"/>条记录，当前第 <s:property value="pageResult.currPage"/> 页，
                 	共 <s:property value="pageResult.pageCount"/> 页 &nbsp;&nbsp;
                 	<s:if test="%{pageResult.currPage>1}">
                            <a href="javascript:doGoPage(<s:property value="pageResult.currPage"/>-1)">上一页</a>&nbsp;&nbsp;
                    </s:if>
                    <s:if test="%{pageResult.currPage<pageResult.pageCount}">
                            <a href="javascript:doGoPage(<s:property value="pageResult.currPage"/>+1)">下一页</a>
					</s:if>
					到&nbsp;<input id="pageNo" name="pageNo" type="text" style="width: 30px;" onkeypress="if(event.keyCode == 13){doGoPage(this.value);}" min="1"
					max="<s:property value="pageResult.pageCount"/>" value="<s:property value='pageResult.currPage'/>" /> &nbsp;&nbsp;
			    </td>
			</tr>
		</table>	
		</s:if>	
		<s:else>
            <tr>
        	<td>暂无数据。<a href="javascript:history.go(-1)">--点击返回--</a></td>
        	</tr>
        </s:else>
        </div>
</html>
