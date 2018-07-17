<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/skin_/login.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.select.js"></script>
<title>365登录</title>
</head>
<body>
	<div id="container">
		<div id="bd">
			<div id="main">
				<form action="web_login.action" name="loginForm" id="loginForm" method="post">
					<div class="login-box">
						<div id="logo"></div>
						<h1></h1>
						<div class="form-wall">
							<div class="input username" id="username">
								<label for="userName">用户名</label> <span></span> 
								<input type="text" id="userName" name="userName"/>
								<span>${requestScope.userIdError}</span>
							</div>
							<div class="input psw" id="psw">
								<label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label> <span></span>
								<input type="password" id="password" name="password"/>
								<span>${requestScope.userPwdError}</span>
							</div>
							
		<!-- 					<div class="input validate" id="validate"> -->
		<!-- 						<label for="valiDate">验证码</label> <input type="text" id="valiDate" /> -->
		<!-- 						<div class="value">X3D5</div> -->
		<!-- 					</div> -->
							<div id="btn" class="loginButton">
								<input type="button"  value="登录"  onclick="autoSubmit();"/>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="utilJs/md5.js" ></script>

<script type="text/javascript">
	function autoSubmit() {
		if ($('#userName').val()=='') {
			alert('请输入用户名');
			return;
		}
		if ($('#password').val()=='') {
			alert('请输入密码');
			return;
		}
		var password = hex_md5($('#password').val().trim());
		console.log(password);
		$('#password').val(password);
		$('#loginForm').submit();
	}

	
	var height = $(window).height() > 445 ? $(window).height() : 445;
	$("#container").height(height);
	var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
	$('#bd').css('padding-top', bdheight);
	$(window).resize(function(e) {
        var height = $(window).height() > 445 ? $(window).height() : 445;
		$("#container").height(height);
		var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
		$('#bd').css('padding-top', bdheight);
    });
	$('select').select();
	
	/* $('.loginButton').click(function(e) {
        document.location.href = "main.html";
    }); */
</script>
</html>