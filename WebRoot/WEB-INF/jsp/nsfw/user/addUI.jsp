<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>用户管理</title>
    <script type="text/javascript" src="${basePath }js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${basePath }js/jquery/ajaxupload.3.6.js"></script>
    <script type="text/javascript">
    	
    	
    	var state = false;//如果为true，ajax可能失效
    	function verifyAccount(asyncFlag){
    		if(asyncFlag != false)asyncFlag = true;
    		//获取账号
    		var account = $("#account").val();
    		if(account.length != 0){
	    		//ajax实现
	    		$.ajax({
	    			type:"get",
	    			url:"${basePath}nsfw/user_verifyAccount.action",
	    			data:{"user.account":account},
	    			asycn:asyncFlag,//是否异步请求
	    			success:function(msg){//返回true表示存在账户
	    				if("true" == msg){//账号存在
	    					alert("用户名已存在");
	    					$("#account").focus();
	    					state = false;
	    				}else {
	    					state = true;
	    				}
	    			}
	    		});
    		}
    	}
    	function doSubmit(){
    		//用户名不为空
    		var $name = $("#name").val();
    		if($name == null||$name ==""){
    			alert("用户名不能为空");
    			$("#name").focus();
    			return false;
    		}
    		//账户不为空
    		var $account = $("#account").val();
    		if($account == null||$account ==""){
    			alert("账户名不能为空");
    			$("#account").focus();
    			return false;
    		}
    		//密码不为空
    		var $password = $("#password").val();
    		if($password == null||$password ==""){
    			alert("密码不能为空");
    			$("#password").focus();
    			return false;
    		}
    		//唯一性校验
    		verifyAccount(false);
    		if(state){
				document.forms[0].submit();    			
    		}
    	}
    	
    	//利用AjaxUpload组件实现异步上传头像并回显
    	$(document).ready(function(){
 	  		var button = $("#imgButton");
 	  		new AjaxUpload(button,{
 	  			action:"${basePath}nsfw/user_ajaxUpload.action",
 	  			name:"headImg",
 	  			onSubmit: function(file, ext) {  
		            if (!(ext && /^(jpg|JPG|png|PNG|gif|GIF)$/.test(ext))) {  
	               		alert("您上传的图片格式不对，请重新选择！");  
	              		return false;  
           		 	}  
        		}, 
        		onComplete:function(file,response){//默认全成功，不再判断，直接设置img的src
        			var path = "${basePath}upload/";
        			
        			var reg = /<pre.+?>(.+)<\/pre>/g;  
					var result = response.match(reg);  
					response = RegExp.$1;//以上三行是为了去除response返回的pre标签内容
					
        			$("#headImg").attr("src",path+response);
        			
        			//由于已经异步设置了头像路径，注册时不再需要重新设置，因此直接隐藏域保存headImg属性
        			$("#imgHidden").val(response);
        		}
 	  		});
    	});
    	</script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basepath }nsfw/user_add.action" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;新增用户</div></div>
    <div class="tableH2">新增用户</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">所属部门：</td>
            <td><s:select name="user.dept" list="#{'部门a':'部门a','部门b':'部门b'}"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">头像：</td>
            <td>
            	<img id="headImg" src="${basePath }upload/<s:property value='user.headImg'/>" width="100" height="100"/>
		           	<input id="imgButton" type="button" name="headImg" value="上传头像" class="btn32"/>
 					<s:hidden name="user.headImg" id="imgHidden"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">用户名：</td>
            <td><s:textfield id="name" name="user.name"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
            <td><s:textfield id="account" name="user.account" onchange="verifyAccount()"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><s:textfield id="password" name="user.password"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">性别：</td>
            <td><s:radio list="#{'true':'男','false':'女'}" name="user.gender"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色：</td>
            <td>
           		 <!-- 自动遍历放置栈顶，属性名自动拿出 -->
            	<s:checkboxlist name="roleIds" list="#roleList" listKey="roleId" listValue="name"></s:checkboxlist>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电子邮箱：</td>
            <td><s:textfield name="user.email"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">手机号：</td>
            <td><s:textfield name="user.mobile"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">生日：</td>
            <td><s:textfield id="birthday" name="user.birthday" onfocus=
            "WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d',minDate:'1900-01-01'})"></s:textfield></td>
        </tr>
		<tr>
            <td class="tdBg" width="200px">状态：</td>
            <td><s:radio list="#{'1':'有效','0':'无效'}" name="user.state" value="1"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <td><s:textarea name="user.memo" cols="75" rows="3"/></td>
        </tr>
    </table>
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit()"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>